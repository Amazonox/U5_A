package edu.kit.informatik.ui;

import edu.kit.informatik.GameMechanics.Tile;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Tile> tiles;
    private final int index;
    private final String specifier;

    public Player(final int index, final String specifier){
        this.index = index;
        this.specifier = specifier;
        this.tiles = new ArrayList<>();
    }


    public void addTiles(final List<Tile> tiles){
        this.tiles.addAll(tiles);
    }
    public void addTile(final Tile tile){
        this.tiles.add(tile);
    }

    public int getIndex() {
        return this.index;
    }

    public String getSpecifier() {
        return this.specifier;
    }

    public boolean posseses(List<Tile> tiles){
        List<Tile> tilesClone = new ArrayList<>();
        for (Tile tile : this.tiles){
            tilesClone.add(tile);
        }
        for (Tile tile : tiles){
            if(tilesClone.contains(tile)){
                tilesClone.remove(tile);
            }else return false;
        }
        return true;
    }

    public String tilesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Tile tile : this.tiles){
            stringBuilder.append(tile.getSymbol());
        }
        return stringBuilder.toString();
    }

    public void takeAwayTiles(final List<Tile> tiles){
        for (final Tile tile : tiles){
            this.tiles.remove(tile);
        }
    }
}
