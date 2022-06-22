package vttp.extra;

import java.util.LinkedList;

public class Cell {

    public String hiddenVal; 
    public String displayVal;
    public LinkedList<Cell> neighbourCells = new LinkedList<>();
    
    public boolean isRevealed = false;
    public boolean isMine;
    public int row;
    public int col;
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.displayVal = " ";
    }
    
    public boolean isRevealed() {
        return isRevealed;
    }
    
    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed; 
    }
    
    public boolean isMine() {
        return isMine;
    }
    
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public LinkedList<Cell> getNeighbourCells() {
        return neighbourCells;
    }

    public void setNeighbourCells(LinkedList<Cell> neighbourCells) {
        this.neighbourCells = neighbourCells;
    }
    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getHiddenVal() {
        return hiddenVal;
    }

    public void setHiddenVal(String hiddenVal) {
        this.hiddenVal = hiddenVal;
    }

    public String getDisplayVal() {
        return displayVal;
    }

    public void setDisplayVal(String displayVal) {
        this.displayVal = displayVal;
    }

    public void addNeighbour(Cell neighbourCell) {
        this.neighbourCells.add(neighbourCell);
    }

    public void updateHiddenVal() {
        // count number of neighbouring mines
        int numOfNeighbourMines = 0;
        for (Cell c : this.neighbourCells)
            if (c.isMine())
                numOfNeighbourMines +=1;

        // update cell's hidden val
        this.setHiddenVal(String.valueOf(numOfNeighbourMines));

    }

    public void reveal() {
        this.isRevealed = true;
        this.displayVal = this.hiddenVal;
    }

    @Override
    public String toString() {
        return this.displayVal;
    }
}