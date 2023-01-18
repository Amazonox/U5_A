package edu.kit.informatik.GameMechanics;

import java.util.Stack;

public interface Tile {



    static Tile tileFromSymbol(char symbol) {
        try {
            return TileNumbers.tileFromSymbol(symbol);
        }catch (final IllegalArgumentException e){
            return TileOperations.tileFromSymbol(symbol);
        }
    }

    char getSymbol();
    void calculateValue(Stack<Integer> stack);
}
