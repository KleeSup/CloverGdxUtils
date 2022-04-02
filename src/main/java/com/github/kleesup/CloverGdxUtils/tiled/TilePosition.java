package com.github.kleesup.CloverGdxUtils.tiled;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
/**
 * @author KleeSup
 * @version 1.0
 * Class created on 02.04.2022
 */
public class TilePosition {

    private final TiledMapTileLayer layer;
    private final int x,y;
    public TilePosition(final TiledMapTileLayer layer, final int x, final int y){
        this.layer = layer;
        this.x = x;
        this.y = y;
    }

    public TiledMapTileLayer.Cell getCell(){
        return layer.getCell(x,y);
    }

    public TiledMapTileLayer getLayer() {
        return layer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
