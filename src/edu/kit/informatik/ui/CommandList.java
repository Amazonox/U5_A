package edu.kit.informatik.ui;

import edu.kit.informatik.ui.Commands.PlaceCommand;
import edu.kit.informatik.ui.Commands.ScoreCommand;
import edu.kit.informatik.ui.Commands.ShowFieldCommand;
import edu.kit.informatik.ui.Commands.ShowTilesCommand;
import edu.kit.informatik.ui.Exceptions.InvalidArgumentException;

public enum CommandList {
    PLACE("place.*", PlaceCommand.class),
    SCORE("score.*", ScoreCommand.class),
    SHOW_TILES("bag.*",ShowTilesCommand.class),
    SHOW_FIELD("print.*", ShowFieldCommand.class);


    private String expression;
    private Class<? extends Command> commandClass;

    CommandList(final String expression, final Class<? extends Command> placeCommandClass) {
        this.expression = expression;
        this.commandClass = placeCommandClass;
    }

    public String getExpression() {
        return this.expression;
    }

    public Class<? extends Command> getCommandClass() {
        return this.commandClass;
    }

    public static CommandList commandFromExprecion(String line) throws InvalidArgumentException {
        for (final CommandList command: values()) {
            if(line.matches(command.getExpression())) return command;
        }
        throw new InvalidArgumentException("not a known command");
    }
}
