package edu.kit.informatik.ui.Commands;

import edu.kit.informatik.GameMechanics.Direction;
import edu.kit.informatik.GameMechanics.Tile;
import edu.kit.informatik.Util.Vector2d;
import edu.kit.informatik.ui.Command;
import edu.kit.informatik.ui.Exceptions.InvalidArgumentException;
import edu.kit.informatik.ui.PlaceHolders;
import edu.kit.informatik.ui.Player;
import edu.kit.informatik.ui.Holder;

import java.util.ArrayList;
import java.util.List;

public class PlaceCommand implements Command {

    private static Player activePlayer = Holder.PLAYER1;
    @Override
    public String commandSetup(String line) throws InvalidArgumentException {
        if(!line.matches(PlaceHolders.TILES.getExpression() + ";"+PlaceHolders.COLUMN.getExpression()+";"+PlaceHolders.ROW.getExpression()+";"+PlaceHolders.DIRECTION.getExpression())){
            return "ERROR: need tiles, column, row and direction";
        }
        final String[] lineParts = line.split(";");
        final List<Tile> tiles = new ArrayList<>();
        for(final char tile: lineParts[0].toCharArray()){
            tiles.add(Tile.tileFromSymbol(tile));
        }
        if(!activePlayer.posseses(tiles)) return "ERROR: Player dosn't have all the tiles";

        Holder.PLAYINGFIELD.placeStones(tiles,new Vector2d(Integer.parseInt(lineParts[1]),Integer.parseInt(lineParts[2])),Direction.getDirecetion(lineParts[3].toCharArray()[0]),activePlayer);
        activePlayer.takeAwayTiles(tiles);
        activePlayer = activePlayer == Holder.PLAYER1 ? Holder.PLAYER2 : Holder.PLAYER1;
        return "OK";
    }
}
