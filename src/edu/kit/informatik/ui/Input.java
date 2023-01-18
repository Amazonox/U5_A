package edu.kit.informatik.ui;

import edu.kit.informatik.GameMechanics.Tile;
import edu.kit.informatik.ui.Exceptions.InvalidArgumentException;

import java.lang.reflect.InvocationTargetException;

public class Input {
    public static String result(String line) {
        final CommandList commandList;
        try {
            commandList = CommandList.commandFromExprecion(line);
        } catch (final InvalidArgumentException e) {
            return "ERROR: " + e.getMessage();
        }
        final Command command;
        try {
            command = commandList.getCommandClass().getConstructor().newInstance();

        } catch (final InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
        try {
            if(commandList == CommandList.SHOW_FIELD) {
                line = line.split(" ")[0];
            }
            else line = line.split(" ")[1];
        }catch (final ArrayIndexOutOfBoundsException e){
            return "ERROR: Print has no argument, all other commands have n";
        }
        try {
            return command.commandSetup(line);
        }catch (InvalidArgumentException e){
            return "ERROR: " + e.getMessage();
        }


    }

    public static void addTilesToPlayer(final String[] tiles){
            for(final char symbol : tiles[Holder.PLAYER1.getIndex()].toCharArray())
                Holder.PLAYER1.addTile(Tile.tileFromSymbol(symbol));
            for(final char symbol : tiles[Holder.PLAYER2.getIndex()].toCharArray())
                Holder.PLAYER2.addTile(Tile.tileFromSymbol(symbol));
        }

}
