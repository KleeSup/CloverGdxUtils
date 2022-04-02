package com.github.kleesup.CloverGdxUtils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.github.kleesup.CloverGdxUtils.tiled.*;

/**
 * @author KleeSup
 * @version 1.0
 * Class created on 02.04.2022
 */
public class AutoTilingTest implements Screen {

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final TiledMapTileChangeLayer layer;

    public AutoTilingTest(){
        this.map = new TiledMap();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.layer = new TiledMapTileChangeLayer(2,1,64,64);

        final TilePosition position = new TilePosition(layer,0,0);
        final TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        layer.setCell(position, cell);

    }


    @Override
    public void render(float delta) {
        renderer.render();
    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
