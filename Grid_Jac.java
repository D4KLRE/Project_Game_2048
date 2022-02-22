package edu.wcc.cs240;

/**
 * the grid class
 * contains methods to move tiles around, spawn new tiles, and use consumables
 */

import static edu.wcc.cs240.Game.grid;
import static edu.wcc.cs240.Game.gridSize;

/** \　　/
 * 【 ﾟ∀ﾟ】< Cancan, Jacqueline, Jialei, this is where you should write your code, please don't touch code in Game.java
 * except removing '//' in front of the method you write in this java file
 */
public class Grid
{
    int[][] tileArray;

    int border = 0;

    private int score = 0;

    public Grid(int gridSize)
    {
        tileArray = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
        {
            for (int j = 0; j < gridSize; j++)
            {
                tileArray[i][j] = 0;
            }
        }
    }

    public void spawn()
    {
        boolean spawn = false;
        do
        {
            int x = (int)(Math.random()*Game.gridSize);
            int y = (int)(Math.random()*Game.gridSize);
            if (tileArray[x][y] == 0)
            {
                tileArray[x][y] = 2;
                spawn = true;
            }
        } while (!spawn);

    }

    public void up()
    {
        int col;
        int row;
        for (col = 0; col < gridSize; col++)
        {
            border = 0;
            for (row = 0; row < gridSize; row++)
            {
                if (tileArray[row][col] == 0)
                {
//                    System.out.println("Row " + col + " tile " + row + " is clear");
                }
                if (tileArray[row][col] != 0 && row != border)
                {
//                    System.out.println("Tile value is " + tileArray[row][col] +
//                            " and is at [" + row + "] [" + col + "]");
                    tileArray[row][0] = tileArray[row][col];
//                    System.out.println("Tile is moved into: [" + row + "] [" + col + "]");
                    //empty the original tile position after tile is moved
                    tileArray[row][col] = 0;
                    //if (border <= row) {
                       // tileArray[row][col] = 0;
                    //}
                }
            }
            border++;
        }
    }

    public void down(){  //use count to move the tiles to the top one by one
        int col;
        int row;
        for (col = gridSize; col > 0; col--) { //start from first row, filter each column
            border = 0; // parameter
            for (row = 0; row < gridSize; row++) { //filter each column in the row
                if (tileArray[row][border] == 0) {
                    System.out.println("Row " + border + " tile " + row + " is clear");
                }
                if (tileArray[row][border] != 0) {
                    System.out.println("Tile value is " + tileArray[row][border] +
                            " and is at [" + row + "] [" + border + "]");
                    tileArray[row][gridSize-1] = tileArray[row][border];
                    System.out.println("Tile is moved into: [" + row + "] [" + border + "]");
                    //empty the original tile position after tile is moved
                    tileArray[row][border] = 0;
                    //if (border <= row) {
                    // tileArray[row][col] = 0;
                    //}
                }
            }
            border++;
        }
    }

    public void left(){  //use count to move the tiles to the top one by one
        int col;
        int row;
        for (col = 0; col < gridSize; col++) { //start from first row, filter each column
            border = 0; // parameter
            for (row = 0; row < gridSize; row++) { //filter each column in the row
                if (tileArray[row][col] == 0) {
                    System.out.println("Row " + col + " tile " + row + " is clear");
                }
                if (tileArray[row][col] != 0 && row != border) {
                    System.out.println("Tile value is " + tileArray[row][col] +
                            " and is at [" + row + "] [" + col + "]");
                    tileArray[0][col] = tileArray[row][col];
                    System.out.println("Tile is moved into: [" + row + "] [" + col + "]");
                    //empty the original tile position after tile is moved
                    tileArray[row][col] = 0;
                    //if (border <= row) {
                    // tileArray[row][col] = 0;
                    //}
                }
            }
            border++;
        }
    }

    public void right(){
        int col;
        int row;
        for (col = 0; col < gridSize; col++) { //start from first row, filter each column
            border = 0; // parameter
            for (row = gridSize-1; row >= 0; row--) { //filter each column in the row
                if (tileArray[row][col] == 0) {
                    System.out.println("Row " + col + " tile " + row + " is clear");
                }
                if (tileArray[row][col] != 0) {
                    System.out.println("Tile value is " + tileArray[row][col] +
                            " and is at [" + row + "] [" + col + "]");
                    tileArray[gridSize-1][col] = tileArray[row][col];
                    System.out.println("Tile is moved into: [" + row + "] [" + col + "]");
                    //empty the original tile position after tile is moved
                    tileArray[row][col] = 0;
                    //if (border <= row) {
                    // tileArray[row][col] = 0;
                    //}
                }
            }
            border++;
        }
    }
}
