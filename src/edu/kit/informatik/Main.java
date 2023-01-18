package edu.kit.informatik;

import edu.kit.informatik.ui.Holder;
import edu.kit.informatik.ui.Input;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        if(args.length != 2) {
            System.out.println("Error: there need to be 2 command line elements, no player received stones");
            return;
        }
        if(!args[0].matches("^[0-9*+-]+$") || !args[1].matches("^[0-9*+-]+$")) {
            System.out.println("Error: the command line arguments need to consist of [0-9*+-], no player received stones");
            return;
        }
        Input.addTilesToPlayer(args);
        final Scanner scanner = new Scanner(System.in);
        String currentCommand = scanner.nextLine();
        while (!currentCommand.equals("quit")) {
            System.out.println(Input.result(currentCommand));
            currentCommand = scanner.nextLine();
        }
        final int scoreP1 = Holder.PLAYINGFIELD.calculateScore(Holder.PLAYER1);
        final int scoreP2 = Holder.PLAYINGFIELD.calculateScore(Holder.PLAYER2);
        System.out.println(scoreP1 + System.lineSeparator() + scoreP2);
        if(scoreP1 > scoreP2) System.out.print(Holder.PLAYER1.getSpecifier() + " wins");
        else if (scoreP2 > scoreP1) System.out.print(Holder.PLAYER2.getSpecifier() + " wins");
        else System.out.print("draw");
    }
}
