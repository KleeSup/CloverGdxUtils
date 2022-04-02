package com.github.kleesup.CloverGdxUtils.tiled;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.github.kleesup.CloverGdxUtils.CloverHelper;

import java.util.Objects;

/**
 * @author KleeSup
 * @version 1.0
 * Class created on 02.04.2022
 *
 * A helper class for finding neighbours of tiles within a tile map.
 * The enum values represent all possible neighbours.
 */
public enum TileNeighbours {
    ABOVE(0, 1),
    RIGHT(1,0),
    UNDER(0,-1),
    LEFT(-1,0),

    UPPER_RIGHT(1,1),
    UPPER_LEFT(-1,1),
    LOWER_RIGHT(-1,1),
    LOWER_LEFT(-1,-1),
    ;

    /**
     * Returns a neighbour cell based on the tiles position and the wanted neighbour.
     * @param tilePosition The position of the tile to retrieve the neighbour for.
     * @param neighbour The wanted neighbour.
     * @return If set, the wanted neighbour cell, {@code null} otherwise
     */
    public static TiledMapTileLayer.Cell getNeighbour(final TilePosition tilePosition, final TileNeighbours neighbour){
        Objects.requireNonNull(tilePosition, "Position cannot be null!");
        Objects.requireNonNull(neighbour, "Neighbour cannot be null!");
        return hasNeighbour(tilePosition, neighbour) ? tilePosition.getLayer().getCell(tilePosition.getX() + neighbour.xOffset,
                tilePosition.getY() + neighbour.yOffset) : null;
    }

    /**
     * Checks whether a specific tile has specified neighbours.
     * @param position The position of the tile to check for the neighbour.
     * @param neighbours The neighbours to check for.
     * @return {@code true} if the tile as all specified neighbours, {@code false} otherwise.
     */
    public static boolean hasNeighbour(final TilePosition position, final TileNeighbours... neighbours){
        Objects.requireNonNull(position, "Position cannot be null!");
        int count = 0;
        for(TileNeighbours neighbour : neighbours){
            //Objects.requireNonNull(neighbour, "Neighbour cannot be null!");
            if(neighbour == null){
                count++;
                continue;
            }
            int ix = position.getX() + neighbour.xOffset;
            if(!CloverHelper.inBounds(0,position.getLayer().getWidth(), ix))return false;
            int iy = position.getY() + neighbour.yOffset;
            if(!CloverHelper.inBounds(0,position.getLayer().getHeight(), iy))return false;
            if(position.getLayer().getCell(position.getX() + neighbour.xOffset, position.getY() + neighbour.yOffset) != null)count++;
        }
        return count == neighbours.length;
    }

    /**
     * Checks whether a location is a neighbour of the specified tile position.
     * @param position The position of the tile.
     * @param neighbourX The neighbours x-coordinate.
     * @param neighbourY The neighbours y-coordinate.
     * @return {@code true} if the given coordinates are a valid neighbour tile, {@code false} otherwise.
     */
    public static boolean isNeighbour(final TilePosition position, final int neighbourX, final int neighbourY){
        Objects.requireNonNull(position, "Position cannot be null!");
        int diffX = Math.abs(neighbourX - position.getX());
        int diffY = Math.abs(neighbourY - position.getY());
        return diffX <= 1 && diffY <= 1;
    }

    /**
     * Checks whether a location is a neighbour of the specified tile position.
     * @param position The position of the tile.
     * @param neighbourPosition The neighbours x-coordinate.
     * @param ignoreLayer Decides whether the layer object of the neighbour position gets checked with the one of the tile position.
     * @return {@code true} if the given coordinates are a valid neighbour tile, {@code false} otherwise.
     */
    public static boolean isNeighbour(final TilePosition position, final TilePosition neighbourPosition, final boolean ignoreLayer){
        if(!ignoreLayer && !position.getLayer().equals(neighbourPosition.getLayer()))return false;
        return isNeighbour(position, neighbourPosition.getX(),neighbourPosition.getY());
    }

    /**
     * Checks whether a location is a neighbour of the specified tile position.
     * @param position The position of the tile.
     * @param neighbourPosition The neighbours x-coordinate.
     * @return {@code true} if the given coordinates are a valid neighbour tile, {@code false} otherwise.
     */
    public static boolean isNeighbour(final TilePosition position, final TilePosition neighbourPosition){
        return isNeighbour(position, neighbourPosition, true);
    }


    public final int xOffset, yOffset;
    TileNeighbours(final int xOffset, final int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }




}
