package edu.kit.informatik.ui;

import edu.kit.informatik.GameMechanics.PlayingField;

public final class Holder {
    private Holder(){
        throw new IllegalStateException("Should not be initiated");
    }

    public static final Player PLAYER1 = new Player(0,"P1");

    public static final Player PLAYER2 = new Player(1,"P2");

    public static final PlayingField PLAYINGFIELD = new PlayingField();
}
