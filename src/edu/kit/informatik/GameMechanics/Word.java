package edu.kit.informatik.GameMechanics;

import edu.kit.informatik.ui.Exceptions.InvalidArgumentException;
import edu.kit.informatik.ui.Player;
import edu.kit.informatik.ui.Holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Word {
    private final List<Box> boxes;
    private final Direction direction;

    public Word(final List<Box> boxes,final Direction direction) throws InvalidArgumentException {
        this.boxes = boxes;
        this.direction = direction;
        if(!this.determinViable()) throw new InvalidArgumentException("tryed to instatiate invalid word");
        for (final Box box : boxes){
            if(direction == Direction.VERTICAL){
                box.setVertical(this);
            }else box.setHorizontal(this);
        }
    }

    public Word(Word word){
        this.boxes = new ArrayList<>();
        this.direction = word.direction;
        for (final Box box:word.boxes){
            this.boxes.add(new Box(box));
        }
    }

    public int calculateValue() {
        final Stack<Integer> stack = new Stack<>();
        for(final Box box : this.boxes) {
            box.getTile().calculateValue(stack);
        }
        return stack.pop();
    }

    private void addFirst(final Box box){
        this.boxes.add(0,box);
    }

    private void addLast(final Box box){
        this.boxes.add(box);
    }

    private void addLast(final List<Box> boxes){
        for (final Box box:boxes) {
            this.addLast(box);
        }
    }
    private void addFirst(final List<Box> boxes){
        for (int i = boxes.size()-1; i > 0; i--) {
            this.addFirst(boxes.get(i));
        }
    }


    public void addFirstOut(final List<Box> boxes, final boolean hypothetical) throws InvalidArgumentException {
        if(!this.addHypotheticalFirst(boxes)) throw new InvalidArgumentException("No Valid Word");
        if(hypothetical) return;
        this.addFirst(boxes);
        if(this.direction == Direction.VERTICAL){
            for(Box box : boxes) {
                box.setVertical(this);
            }
        }else {
            for (Box box : boxes) {
                box.setHorizontal(this);
            }
        }
    }

    public void addLastOut(final List<Box> boxes, final boolean hypothetical) throws InvalidArgumentException {
        if(!this.addHypotheticalLast(boxes)) throw new InvalidArgumentException("No Valid Word");
        if(hypothetical) return;
        this.addLast(boxes);
        if(this.direction == Direction.VERTICAL){
            for(Box box : boxes) {
                box.setVertical(this);
            }
        }else {
            for (Box box : boxes) {
                box.setHorizontal(this);
            }
        }
    }





    private boolean addHypotheticalFirst(Box box){
        final Word clone = new Word(this);
        clone.addFirst(box);
        return clone.determinViable();
    }
    private boolean addHypotheticalLast(Box box){
        final Word clone = new Word(this);
        clone.addLast(box);
        return clone.determinViable();
    }

    private boolean addHypotheticalLast(List<Box> boxes){
        final Word clone = new Word(this);
        clone.addLast(boxes);
        return clone.determinViable();
    }
    private boolean addHypotheticalFirst(List<Box> boxes){
        final Word clone = new Word(this);
        clone.addFirst(boxes);
        return clone.determinViable();
    }

    /**
     * only for this package, only for Hypothetical tests
     * @return
     */
    List<Box> getBoxes(){
        return this.boxes;
    }



    private boolean determinViable(){
        int viableCounter = 0;
        for (final Box box:this.boxes) {
            if(box.getTile() instanceof TileNumbers){
                viableCounter++;
            }else {
                viableCounter--;
            }
            if(viableCounter < 1) return false;
        }
        return viableCounter == 1;
    }

    public Player getPlayer() {
        int player1Count = 0;
        int player2Count = 0;
        for (final Box box : this.boxes) {
            if (box.getPlayer() == Holder.PLAYER1) {
                player1Count++;
            } else if (box.getPlayer() == Holder.PLAYER2) {
                player2Count++;
            }
        }
        if(player1Count == player2Count) return null;
        return player1Count > player2Count ? Holder.PLAYER1 : Holder.PLAYER2;
    }
}
