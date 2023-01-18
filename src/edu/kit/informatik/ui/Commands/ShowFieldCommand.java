package edu.kit.informatik.ui.Commands;

import edu.kit.informatik.GameMechanics.PlayingField;
import edu.kit.informatik.ui.Command;
import edu.kit.informatik.ui.Holder;

public class ShowFieldCommand implements Command {
    @Override
    public String commandSetup(final String line) {
        return Holder.PLAYINGFIELD.toString();
    }
}
