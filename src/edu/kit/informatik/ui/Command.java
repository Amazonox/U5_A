package edu.kit.informatik.ui;

import edu.kit.informatik.ui.Exceptions.InvalidArgumentException;

public interface Command {
    public String commandSetup(String line) throws InvalidArgumentException;
}
