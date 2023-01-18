package edu.kit.informatik.ui;

import edu.kit.informatik.GameMechanics.Direction;

public enum PlaceHolders {
    TILES("[0-9*+-]+"),
    COLUMN("\\d"),
    ROW("\\d"),
    DIRECTION("("+ Direction.VERTICAL.getSymbol() + "|" + Direction.HORIZONTAL.getSymbol() + ")"),
    PLAYER("(" + Holder.PLAYER1.getSpecifier()+"|"+ Holder.PLAYER2.getSpecifier() + ")");

    private final String expression;

    PlaceHolders(final String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }
}
