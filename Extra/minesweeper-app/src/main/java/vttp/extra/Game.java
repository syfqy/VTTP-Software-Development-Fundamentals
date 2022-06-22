package vttp.extra;

import java.io.Console;

public class Game {

    public static int[] getCellToDig(Console cons) {
        while(true) {
            try {
                // get input and convert to arr of ints
                String[] inputCoords = cons.readLine("> Enter row and col of cell to dig (e.g. 0, 0): ").split(",\\s*");
                System.out.println(inputCoords[0]);
                int row = Integer.parseInt(inputCoords[0]);
                int col = Integer.parseInt(inputCoords[1]);
                int[] coords = { row, col };
                return coords;
            } catch (NumberFormatException e) {
                System.out.println("Cannot process input, please try again");
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Input entered in incorrect format, try again");
                e.printStackTrace();
            }
        }
    }

    public static void start() {

        // set up minefield
        Minefield mf = new Minefield();
        mf.initField();
        mf.plantMines();
        mf.setCellNeighbours();
        mf.displayField();

        Console cons = System.console();
        System.out.println("Welcome to Minesweeper!");

        // winning and losing conditions
        boolean gameOver = false;
        boolean gameWin = false;

        while (true) {

            // get input coords of cell to dig
            int[] coords = getCellToDig(cons);
            int row = coords[0];
            int col = coords[1];

            // dig and reveal cells
            mf.revealCell(row, col);
            mf.displayField();

            // end game if all non-mine cells revealed OR a mine revealed
            gameWin = mf.isAllCellsRevealed();
            gameOver = mf.isMineFound();

            if (gameOver) {
                System.out.println("MINE EXPLODED! GAME OVER");
                break;
            }

            if (gameWin) {
                System.out.println("MINEFIELD CLEARED!");
                break;
            }

        }

        System.out.println("Thank you for playing!");

    }

}
