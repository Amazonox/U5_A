package edu.kit.informatik.GameMechanics;

import edu.kit.informatik.Util.Vector2d;
import edu.kit.informatik.Util.Vector4d;
import edu.kit.informatik.ui.Exceptions.InvalidArgumentException;
import edu.kit.informatik.ui.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayingField {
    private Box[][] boxes;
    private List<Word> wordlist;

    public PlayingField() {
        this.boxes = new Box[10][10];
        this.wordlist = new ArrayList<>();
    }


    public void placeStones(final List<Tile> tiles, final Vector2d start, final Direction direction, Player player)
            throws InvalidArgumentException {
        if(tiles.size() < 1 || tiles.size() > 3) throw new InvalidArgumentException("1-3 tiles at once");
        if(tiles.size() == 1 && this.adjacent(start).count() == 0){
            throw new InvalidArgumentException("Isolated Stone");
        }
        for(int i = 0; i<tiles.size(); i++){
            if(direction == Direction.HORIZONTAL && this.boxes[start.row()][start.column()+i] != null
                    || direction == Direction.VERTICAL && this.boxes[start.row()+i][start.column()] != null){
                throw new InvalidArgumentException("Overplacing tile");
            }
        }

        final List<Box> boxList = new ArrayList<>();
        for (final Tile tile:tiles){
            boxList.add(new Box(tile,player));
        }

        this.placeSinglesInWord(boxList,start,direction,true);

        final Box front = direction == Direction.VERTICAL ? this.adjacent(start).getUp() : this.adjacent(start).getLeft();
        final Box back = direction == Direction.VERTICAL
                ? this.adjacent(new Vector2d(start.row()+tiles.size()-1,start.column())).getDown()
                : this.adjacent(new Vector2d(start.row(),start.column()+tiles.size()-1)).getRight();

        this.preparePlaceInWord(boxList,front,back,direction,false);
        this.placeSinglesInWord(boxList,start,direction,false);

        for (int i = 0; i < boxList.size(); i++){
            if(direction == Direction.VERTICAL){
                this.boxes[start.row() + i][start.column()] = boxList.get(i);
            }else {
                this.boxes[start.row()][start.column() + i] = boxList.get(i);
            }
        }
    }

    /**
     * places the single boxes in the fitting words
     */
    private void placeSinglesInWord(List<Box> boxList,Vector2d start,Direction direction, boolean hypothetical) throws InvalidArgumentException {
        if(direction == Direction.VERTICAL) {
            //checking horizontal words for invalids
            for (int i = 0; i < boxList.size(); i++) {
                final List<Box> temp = new ArrayList<>();
                temp.add(boxList.get(i));
                final Vector4d<Box> adjacent = this.adjacent(new Vector2d(start.row() + i,start.column()));
                this.preparePlaceInWord(temp,adjacent.getLeft(),adjacent.getRight(),Direction.HORIZONTAL,hypothetical);
            }
        }
        if(direction == Direction.HORIZONTAL) {
            //checking vertical words for invalids
            for (int i = 0; i < boxList.size(); i++) {
                final List<Box> temp = new ArrayList<>();
                temp.add(boxList.get(i));
                final Vector4d<Box> adjacent = this.adjacent(new Vector2d(start.row(),start.column() + i));
                this.preparePlaceInWord(temp,adjacent.getUp(),adjacent.getDown(),Direction.VERTICAL,hypothetical);
            }
        }
    }


    private void preparePlaceInWord(List<Box> boxList, Box front, Box back,Direction direction, boolean hypothetical) throws InvalidArgumentException {
        if(direction == Direction.HORIZONTAL) {
            if (front != null && back != null)
                this.placeInWord(boxList, front, back,
                        front.getHorizontal(), back.getHorizontal(),Direction.HORIZONTAL, hypothetical);
            else if (front != null)
                this.placeInWord(boxList, front, front.getHorizontal(), Direction.BACK, direction,hypothetical);
            else if (back != null)
                this.placeInWord(boxList, back, back.getHorizontal(), Direction.FRONT,direction,hypothetical);
            else if (boxList.size() > 1){
                final Word word = new Word(boxList,direction);
                if(hypothetical) return;
                this.wordlist.add(word);
            }
        }
        if(direction == Direction.VERTICAL) {
            if (front != null && back != null)
                this.placeInWord(boxList, front, back,
                        front.getVertical(), back.getVertical(),Direction.HORIZONTAL, hypothetical);
            else if (front != null)
                this.placeInWord(boxList, front, front.getVertical(), Direction.BACK, direction,hypothetical);
            else if (back != null)
                this.placeInWord(boxList, back, back.getVertical(), Direction.FRONT,direction,hypothetical);
            else if (boxList.size() > 1){
                final Word word = new Word(boxList,direction);
                if(hypothetical) return;
                this.wordlist.add(word);
            }
        }
    }



    private void placeInWord(List<Box> boxes, Box frontBox,Box backBox,Word frontWord,Word backWord,Direction direction,boolean hypothetical) throws InvalidArgumentException{
        final List<Box> combinedBoxes = new ArrayList<>();
        if(frontWord == null && backWord == null){
            combinedBoxes.add(frontBox);
            combinedBoxes.addAll(boxes);
            combinedBoxes.add(backBox);
            final Word word = new Word(combinedBoxes,direction);
            if(hypothetical) return;
            this.wordlist.add(word);
            return;
        }
        if(frontWord == null){
            combinedBoxes.add(frontBox);
            combinedBoxes.addAll(boxes);
            backWord.addFirstOut(combinedBoxes,hypothetical);
            return;
        }
        if(backWord == null){
            combinedBoxes.addAll(boxes);
            combinedBoxes.add(backBox);
            frontWord.addLastOut(combinedBoxes,hypothetical);
            return;
        }
        // if(frontWord != null && backWord != null){
            combinedBoxes.addAll(boxes);
            combinedBoxes.addAll(backWord.getBoxes());
            frontWord.addLastOut(combinedBoxes,hypothetical);
            if(hypothetical) return;
            this.wordlist.remove(backWord);
    }


    private void placeInWord(final List<Box> boxes, final Box otherBox, final Word word, final Direction positionRelativeToOther, final Direction direction, boolean hypothetical)
            throws InvalidArgumentException{
        if(word == null) {
            final List<Box> combinedBoxes = new ArrayList<>();
            if (positionRelativeToOther == Direction.FRONT) {
                combinedBoxes.addAll(boxes);
                combinedBoxes.add(otherBox);
            } else {
                combinedBoxes.add(otherBox);
                combinedBoxes.addAll(boxes);
            }
            final Word word1 = new Word(combinedBoxes,direction);
            if(hypothetical) return;
            this.wordlist.add(word1);
            return;
        }
        if(positionRelativeToOther == Direction.FRONT){
            word.addFirstOut(boxes,hypothetical);
        }
        if(positionRelativeToOther == Direction.BACK){
            word.addLastOut(boxes,hypothetical);
        }
        throw new IllegalArgumentException("Need front or back direction");
    }

    private Vector4d<Box> adjacent(final Vector2d place){
        final Vector4d<Box> adjacent= new Vector4d<>();
        if(place.row() != 0) adjacent.setUp(this.boxes[place.row()-1][place.column()]);
        if(place.row() != this.boxes.length) adjacent.setDown(this.boxes[place.row()+1][place.column()]);
        if(place.column() != 0) adjacent.setLeft(this.boxes[place.row()][place.column()-1]);
        if(place.column() != this.boxes.length) adjacent.setRight(this.boxes[place.row()][place.column()+1]);
        return adjacent;
    }

    public int calculateScore(Player player){
        int score = 0;
        for (Word word: this.wordlist) {
            if(word.getPlayer() == player){
                score += word.calculateValue();
            }
        }
        return score;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i< this.boxes.length; i++){
            for (int j = 0; j < this.boxes.length; j++) {
                stringBuilder.append('[');
                if(boxes[i][j] == null) stringBuilder.append(' ');
                else stringBuilder.append(boxes[i][j].getTile().getSymbol());
                stringBuilder.append(']').append(' ');
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.delete(stringBuilder.length()-System.lineSeparator().length(),stringBuilder.length());
    return stringBuilder.toString();
    }


}
