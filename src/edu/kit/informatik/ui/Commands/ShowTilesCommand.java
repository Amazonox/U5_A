package edu.kit.informatik.ui.Commands;

import edu.kit.informatik.ui.Command;
import edu.kit.informatik.ui.PlaceHolders;
import edu.kit.informatik.ui.Holder;

public class ShowTilesCommand implements Command {
    @Override
    public String commandSetup(String line) {
        if(!line.matches(PlaceHolders.PLAYER.getExpression())){
            return "ERROR: Need eiter "+ Holder.PLAYER1.getSpecifier() + " or " + Holder.PLAYER2.getSpecifier()+ " as args";
        }
        if(line.matches(Holder.PLAYER1.getSpecifier())){
            return Holder.PLAYER1.tilesToString();
        }else return Holder.PLAYER2.tilesToString();
    }
}
