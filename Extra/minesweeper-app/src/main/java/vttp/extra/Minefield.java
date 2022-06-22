package vttp.extra;

public class Minefield {

    // class vars
    private static int size = 10;
    private static int nMines = (int) (5 + (Math.random() *  (10-5))); // between 5 to 10 mines
    private static int nSafeCells = size * size - nMines;

    // instance vars
    public boolean mineFound = false;
    public boolean isAllCellsRevealed = false;
    public int numOfCellsRevealed = 0;
    public Cell[][] visibleField;
    
    public int getNumOfCellsRevealed() {
        return numOfCellsRevealed;
    }

    public boolean isMineFound() {
        return mineFound;
    }

    public boolean isAllCellsRevealed() {
        return isAllCellsRevealed;
    }

    public void initField() {
        // initialize m x m matrix of empty Cells
        visibleField = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                visibleField[i][j] = new Cell(i, j);
            }
        }
    }

    public void plantMines() {
        // plant mines randomly across matrix
        int i = 0;
        while (i < nMines) {
            // pick random cell to plant
            int r = (int) (Math.random() * size);
            int c = (int) (Math.random() * size);
            Cell cell = visibleField[r][c];

            // plant mine if cell not already a mine
            if (cell.getHiddenVal() != "*")
                cell.setHiddenVal("*");
            cell.setMine(true);
            i++;
        }
    }

    public void setCellNeighbours() {
        // add adjacent cells to each cell's neighbours

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                Cell currCell = visibleField[i][j];
                int currRow = i;
                int currCol = j;

                for (int r = Math.max(0, currRow - 1); r <= Math.min(size - 1, currRow + 1); r++) { // max and min to
                                                                                                    // prevent out of
                                                                                                    // bounds
                    for (int c = Math.max(0, currCol - 1); c <= Math.min(size - 1, currCol + 1); c++) {
                        // add each adjacent cell (max: 8) to neighbour list
                        if (!(currCell.getRow() == r && currCell.getCol() == c)) { // ignore cell itself
                            Cell neighbourCell = visibleField[r][c];
                            currCell.addNeighbour(neighbourCell);
                        }
                        // update current cell value if does not contain mine
                        if (!currCell.isMine)
                            currCell.updateHiddenVal();

                    }
                }
            }
        }
    }

    public void displayField() {

        // prints minefield

        System.out.print("\t ");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "\t| ");
            for (int j = 0; j < 10; j++) {
                System.out.print(visibleField[i][j]);
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    public boolean revealCell(int row, int col) {

        // reveal cells recursively until cell is mine or neighbour is mine
        Cell currCell = visibleField[row][col];

        // ignore already revealed cells
        if (!currCell.isRevealed()) {
            currCell.reveal();

            // increment revealed count
            this.numOfCellsRevealed++;
            this.isAllCellsRevealed = (this.numOfCellsRevealed == nSafeCells);

            // if cell contains mine, stop revealing
            if (currCell.isMine()) {
                this.mineFound = true;
                return true;
            }

            // if cell has mine as neighbour, stop revealing
            if (!currCell.getHiddenVal().equals("0")) {
                return true;
            }

            // if no neighbouring mines, continue to reveal neighbours
            for (Cell neighbour : currCell.getNeighbourCells()) {
                int r = neighbour.getRow();
                int c = neighbour.getCol();
                if (revealCell(r, c))
                    break;
            }
        } 

        return false;
    }

}
