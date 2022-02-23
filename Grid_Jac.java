package edu.wcc.cs240;

/**
 * the grid class
 * contains methods to move tiles around, spawn new tiles, and use consumables
 */

import static edu.wcc.cs240.Game.grid;
import static edu.wcc.cs240.Game.Game.gridSize;

/** \　　/
 * 【 ﾟ∀ﾟ】< Cancan, Jacqueline, Jialei, this is where you should write your code, please don't touch code in Game.java
 * except removing '//' in front of the method you write in this java file
 */
public class Grid
{
    int[][] tileArray;

//    int emptyPosPointer = 0;

    private int score = 0;

    public Grid(int Game.gridSize)
    {
        tileArray = new int[Game.gridSize][Game.gridSize];
        for (int i = 0; i < Game.gridSize; i++)
        {
            for (int j = 0; j < Game.gridSize; j++)
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
            int x = (int)(Math.random()*Game.Game.gridSize);
            int y = (int)(Math.random()*Game.Game.gridSize);
            if (tileArray[x][y] == 0)
            {
                //int value = (int)Math.pow(2, (int)(Math.random()*11));
                //System.out.println(value);
                tileArray[x][y] = 2;
                spawn = true;
            }
        }while (!spawn);
    }

    public void destroy(int x, int y) {
        tileArray[x-1][y-1] = 0;
    }

    public void doubleTile(int x, int y)
    {
        tileArray[x-1][y-1] *= 2;
    }

    public void up(){
        for (int col = 0; col < Game.gridSize; col++) { //start from first row, filter each column
            int emptyPosPointer = 0; // parameter of empty tile
            for (int row = 0; row < Game.gridSize; row++) { //filter each col in the column
                if (tileArray[col][emptyPosPointer] != 0){
                    emptyPosPointer++;
                }
                else if (tileArray[col][row] != 0) {
                    tileArray[col][emptyPosPointer] = tileArray[col][row];
                    //empty the original tile position after tile is moved
                    tileArray[col][row] = 0;
                    emptyPosPointer++;
                }
            }
        }
    }

    public void down(){  //start from first row, filter each column
        for (int col = 0; col < Game.gridSize; col++) {
            int emptyPosPointer = Game.gridSize-1; // parameter of empty tile
            for (int row = Game.gridSize-1; row >= 0; row--) { //filter each col in the column
                if (tileArray[col][emptyPosPointer] != 0){
                    emptyPosPointer--;
                }
                else if (tileArray[col][row] != 0) {
                    tileArray[col][emptyPosPointer] = tileArray[col][row];
                    //empty the original tile position after tile is moved
                    tileArray[col][row] = 0;
                    emptyPosPointer--;
                }
            }
        }
    }

    public void left(){  //use count to move the tiles to the top one by one
        for (int row = 0; row < Game.gridSize; row++) { //start from first col, filter each column
            int emptyPosPointer = 0; // parameter of empty tile
            for (int col = 0; col < Game.gridSize; col++) {
                if (tileArray[emptyPosPointer][row] != 0){
                    emptyPosPointer++;
                }
                else if (tileArray[col][row] != 0) {
                    tileArray[emptyPosPointer][row] = tileArray[col][row];
                    //empty the original tile position after tile is moved
                    tileArray[col][row] = 0;
                    emptyPosPointer++;
                }
            }
        }
    }

    public void right(){  //use count to move the tiles to the top one by one
        for (int row = 0; row < Game.gridSize; row++) {
            int emptyPosPointer = Game.gridSize-1; // parameter
            for (int col = Game.gridSize-1; col >= 0; col--) { //filter each tile in col
                if (tileArray[emptyPosPointer][row] != 0){
                    emptyPosPointer--;
                }
                else if (tileArray[col][row] != 0) {
                    tileArray[emptyPosPointer][row] = tileArray[col][row];
                    //empty the original tile position after tile is moved
                    tileArray[col][row] = 0;
                    emptyPosPointer--;
                }
            }
        }
    }
}
