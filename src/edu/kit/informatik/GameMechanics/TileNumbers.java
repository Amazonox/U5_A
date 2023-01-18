package edu.kit.informatik.GameMechanics;

import java.util.Stack;

public enum TileNumbers implements Tile{
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private final int number;

    TileNumbers(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
    public char getSymbol() {
        return Character.forDigit(this.number,10);
    }

    @Override
    public void calculateValue(Stack<Integer> stack) {
        stack.push(this.getNumber());
    }


    public static Tile tileFromSymbol(char symbol) {
        for (TileNumbers tileNumbers : values()){
            if(tileNumbers.getSymbol() == symbol)return tileNumbers;
        }
        throw new IllegalArgumentException("not a tile symbol");
    }
}
