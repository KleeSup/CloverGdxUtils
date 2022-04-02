package com.github.kleesup.CloverGdxUtils.tiled;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * @author KleeSup
 * @version 1.0
 * Class created on 02.04.2022
 *
 * A listener class which is used in {@link TiledMapTileChangeLayer} to listen to cell/tile changes.
 */
public interface TileChangeListener {

    default void onCellChange(final TilePosition position, final TiledMapTileLayer.Cell cell, final TiledMapTileLayer.Cell oldCell){
        onCellChange(position.getLayer(),position.getX(),position.getY(),cell,oldCell);
    }
    void onCellChange(final TiledMapTileLayer layer, int x, int y, final TiledMapTileLayer.Cell cell, final TiledMapTileLayer.Cell oldCell);

    default void onTileChange(final TilePosition position, final TiledMapTileLayer.Cell cell, final TiledMapTile oldTile){
        onTileChange(position.getLayer(), position.getX(), position.getY(), cell, oldTile);
    }
    void onTileChange(final TiledMapTileLayer layer, int x, int y, final TiledMapTileLayer.Cell cell, final TiledMapTile oldTile);

}
