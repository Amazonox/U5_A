package edu.kit.informatik.GameMechanics;

public enum Direction {
    VERTICAL('V'),
    HORIZONTAL('H'),
    FRONT('F'),
    BACK('B');

    private final char symbol;

    Direction(final char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public static Direction getDirecetion(final char symbol){
        for (final Direction direction : values()){
            if(symbol == direction.getSymbol()) return direction;
        }
        throw new IllegalArgumentException("no real symbol");
    }
}
