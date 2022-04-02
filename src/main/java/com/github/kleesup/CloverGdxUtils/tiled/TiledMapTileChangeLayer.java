package com.github.kleesup.CloverGdxUtils.tiled;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author KleeSup
 * @version 1.0
 * Class created on 02.04.2022
 *
 * An implementation of {@link TiledMapTileLayer} for implementing cell change events.
 */
public class TiledMapTileChangeLayer extends TiledMapTileLayer {

    private final Collection<TileChangeListener> changeListeners;

    /**
     * Creates AutoTiledMapTileLayer layer.
     * @param width      layer width in tiles
     * @param height     layer height in tiles
     * @param tileWidth  tile width in pixels
     * @param tileHeight tile height in pixels
     */
    public TiledMapTileChangeLayer(int width, int height, int tileWidth, int tileHeight) {
        super(width, height, tileWidth, tileHeight);
        this.changeListeners = new HashSet<>();
    }

    /**
     * Registers a listener.
     * @param listener The listener to register.
     */
    public void addListener(final TileChangeListener listener){
        Objects.requireNonNull(listener, "Listener cannot be null!");
        changeListeners.add(listener);
    }

    /**
     * Unregisters a listener.
     * @param listener The listener to unregister.
     */
    public void removeListener(final TileChangeListener listener){
        Objects.requireNonNull(listener, "Listener cannot be null!");
        changeListeners.remove(listener);
    }

    @Override
    public void setCell(int x, int y, Cell cell) {
        final Cell oldCell = getCell(x,y);
        final AutoTiledCell autoTiledCell;
        if(cell == null){
            autoTiledCell = null;
        }else{
            autoTiledCell = new AutoTiledCell(this,x,y,cell);
        }
        super.setCell(x, y, autoTiledCell);
        for(TileChangeListener listener : changeListeners){
            listener.onCellChange(this,x,y,cell,oldCell);
        }
    }

    public void setCell(TilePosition position, Cell cell, boolean ignoreLayer) {
        Objects.requireNonNull(position, "Position cannot be null!");
        if(!ignoreLayer && !position.getLayer().equals(this))
            throw new IllegalArgumentException("Layer of tile position needs to be the same the one setCell is called from!");
        setCell(position.getX(), position.getY(), cell);
    }

    public void setCell(TilePosition position, Cell cell) {
        setCell(position,cell,true);
    }

    @Override
    public Cell getCell(int x, int y) {
        final Cell cell = super.getCell(x,y);
        if(cell instanceof AutoTiledCell)return ((AutoTiledCell) cell).actualCell;
        return cell;
    }

    //cell-wrapper
    private static final class AutoTiledCell extends Cell{

        private final int x,y;
        private final TiledMapTileChangeLayer layer;
        public final Cell actualCell;
        private AutoTiledCell(final TiledMapTileChangeLayer layer, final int x, final int y, final Cell actualCell){
            this.x = x;
            this.y = y;
            this.layer = layer;
            this.actualCell = actualCell;
        }

        @Override
        public Cell setTile(TiledMapTile tile) {
            final TiledMapTile oldTile = getTile();
            super.setTile(tile);
            for(TileChangeListener listener : layer.changeListeners){
                listener.onTileChange(layer,x,y,this,oldTile);
            }
            return this;
        }

        //retrieve from in-class super

        @Override
        public TiledMapTile getTile() {
            return actualCell.getTile();
        }

        @Override
        public boolean getFlipHorizontally() {
            return actualCell.getFlipHorizontally();
        }

        @Override
        public Cell setFlipHorizontally(boolean flipHorizontally) {
            return actualCell.setFlipHorizontally(flipHorizontally);
        }

        @Override
        public boolean getFlipVertically() {
            return actualCell.getFlipVertically();
        }

        @Override
        public Cell setFlipVertically(boolean flipVertically) {
            return actualCell.setFlipVertically(flipVertically);
        }

        @Override
        public int getRotation() {
            return actualCell.getRotation();
        }

        @Override
        public Cell setRotation(int rotation) {
            return actualCell.setRotation(rotation);
        }
    }

}
