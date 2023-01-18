package edu.kit.informatik.GameMechanics;

import edu.kit.informatik.ui.Player;

public class Box {
    private Word horizontal;
    private Word vertical;

    private Tile tile;

    private Player player;

    public Box(Tile tile,Player player) {
        this.tile = tile;
        this.player = player;
    }

    /**
     * clones do not contain the word
     * @param box
     */
    public Box(Box box){
        this.tile = box.tile;
        this.player = box.player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Word getHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(final Word horizontal) {
        this.horizontal = horizontal;
    }

    public Word getVertical() {
        return this.vertical;
    }

    public void setVertical(final Word vertical) {
        this.vertical = vertical;
    }

    public Tile getTile() {
        return this.tile;
    }


}
