package edu.kit.informatik.GameMechanics;

import java.util.Stack;

public enum TileOperations implements Tile{
    ADDITION('+'),
    SUBTRATION('-'),
    MULTIPLICATION('*');

    private final char symbol;

    TileOperations(final char symbol) {
        this.symbol = symbol;
    }

    public int calculate(final int left, final int right){
        if(this == ADDITION){
            return left + right;
        }
        if(this == SUBTRATION){
            return left - right;
        }
        return left * right;
    }

    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public void calculateValue(final Stack<Integer> stack) {
        final int right = stack.pop();
        final int left = stack.pop();
        stack.push(this.calculate(left,right));
    }

    public static Tile tileFromSymbol(final char symbol) {
        for (final TileOperations tileOperation : values()){
            if(tileOperation.getSymbol() == symbol)return tileOperation;
        }
        throw new IllegalArgumentException("not a tile symbol");
    }
}
