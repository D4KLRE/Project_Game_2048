package edu.wcc.cs240;

/**
 * the grid class
 * contains methods to move tiles around, spawn new tiles, and use consumables
 */

import java.util.ArrayList;
import java.util.Enumeration;

/** \　　/
 * 【 ﾟ∀ﾟ】< Cancan, Jacqueline, Jialei, this is where you should write your code, please don't touch code in Game.java
 * except removing '//' in front of the method you write in this java file
 */
public class Grid
{
    int[][] tileArray;

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
        //Create an array list to store each position in grid. Delete the randomly selected position if it's not empty,
        //thus it won't be randomly selected again, preventing resource loss. In addition, there won't be an infinite
        // loop trying to find an empty position if all tiles are not 0.
        ArrayList<Integer> posArrList = new ArrayList<>();
        for (int i = 0; i < Math.pow(Game.gridSize, 2); i++)
            posArrList.add(i);

        int spawned = 0;
        do
        {
            if (posArrList.size() == 0)
            {
                System.out.println("All tiles are not empty, spawn method aborted");
                break;
            }

                int pos = (int)(Math.random()*posArrList.size());
                //Convert one-dimensional position to two-dimensional position
                int x = posArrList.get(pos) % Game.gridSize;
                int y = posArrList.get(pos) / Game.gridSize;

                if (tileArray[x][y] == 0)
                {
                    //int value = (int)Math.pow(2, (int)(Math.random()*11));
                    //System.out.println(value);
                    tileArray[x][y] = 2;
                    System.out.println("'2' tile spawned at " + x + ", " + y);
                    spawned++;
                }
                else
                {
//                System.out.println(posArrList.get(pos) + " at index " + pos + " removed.");
//                System.out.println("x: " + x + " y: " + y);
                    posArrList.remove(pos);
//                System.out.println("Size of posArrList remained: " + posArrList.size());

                }
            }while (spawned < Game.numTileSpawn);
    }


    public void left() {
        for (int i = 0; i < tileArray.length; i++) {
            for (int x = i; x >= 1; x--) {
                for (int y = 0; y < tileArray.length; y++) {
                    if (tileArray[x - 1][y] == tileArray[x][y] && tileArray[x][y] != 0) {
                        tileArray[x - 1][y] *= 2;
                        tileArray[x][y] = 0;
                    }
                    if (tileArray[x - 1][y] == 0) {
                        tileArray[x - 1][y] = tileArray[x][y];
                        tileArray[x][y] = 0;
                    }
                }
            }
        }
    }

    public void up() {
        for (int i = 0; i < tileArray.length; i++) {
            for (int y = i; y >= 1; y--) {
                for (int x = 0; x < tileArray.length; x++) {
                    if (tileArray[x][y - 1] == tileArray[x][y] && tileArray[x][y] != 0) {
                        tileArray[x][y - 1] *= 2;
                        tileArray[x][y] = 0;
                    }
                    if (tileArray[x][y - 1] == 0) {
                        tileArray[x][y - 1] = tileArray[x][y];
                        tileArray[x][y] = 0;
                    }
                }
            }
        }
    }

    public void down() {
        for (int i = tileArray.length - 2; i >= 0; i--) {
            for (int y = i; y < tileArray.length - 1; y++) {
                for (int x = 0; x < tileArray.length; x++) {
                    if (tileArray[x][y] == tileArray[x][y + 1] && tileArray[x][y + 1] != 0) {
                        tileArray[x][y + 1] *= 2;
                        tileArray[x][y] = 0;
                    }
                    if (tileArray[x][y + 1] == 0) {
                        tileArray[x][y + 1] = tileArray[x][y];
                        tileArray[x][y] = 0;
                    }
                }
            }
        }
    }

    public void right() {
        for (int i = tileArray.length - 2; i >= 0; i--) {
            for (int x = i; x < tileArray.length - 1; x++) {
                for (int y = 0; y < tileArray.length; y++) {
                    if (tileArray[x + 1][y] == tileArray[x][y] && tileArray[x + 1][y] != 0) {
                        tileArray[x + 1][y] *= 2;
                        tileArray[x][y] = 0;
                    }
                    if (tileArray[x + 1][y] == 0) {
                        tileArray[x + 1][y] = tileArray[x][y];
                        tileArray[x][y] = 0;
                    }
                }
            }
        }
    }

    //    public void up(){
//        for (int col = 0; col < Game.gridSize; col++) { //start from first col, filter each column
//            int emptyPosPointer = 0; // parameter of empty tile
//            for (int row = 0; row < Game.gridSize; row++) { //filter each row in the column
//                if (tileArray[col][emptyPosPointer] != 0){
//                    emptyPosPointer++;
//                }
//                else if (tileArray[col][row] != 0) {
//                    tileArray[col][emptyPosPointer] = tileArray[col][row];
//                    //empty the original tile position after tile is moved
//                    tileArray[col][row] = 0;
//                    emptyPosPointer++;
//                }
//            }
//        }
//    }


//    public void down(){  //start from first col, filter each column
//        for (int col = 0; col < Game.gridSize; col++) {
//            int emptyPosPointer = Game.gridSize-1; // parameter of empty tile
//            for (int row = Game.gridSize-1; row >= 0; row--) { //filter each row in the column
//                if (tileArray[col][emptyPosPointer] != 0){
//                    emptyPosPointer--;
//                }
//                else if (tileArray[col][row] != 0) {
//                    tileArray[col][emptyPosPointer] = tileArray[col][row];
//                    //empty the original tile position after tile is moved
//                    tileArray[col][row] = 0;
//                    emptyPosPointer--;
//                }
//            }
//        }
//    }

//    public void left(){
//        for (int row = 0; row < Game.gridSize; row++) { //start from first row, filter each row
//            int emptyPosPointer = 0; // parameter of empty tile
//            for (int col = 0; col < Game.gridSize; col++) { //filter each tile in row
//                if (tileArray[emptyPosPointer][row] != 0){
//                    emptyPosPointer++;
//                }
//                else if (tileArray[col][row] != 0) {
//                    tileArray[emptyPosPointer][row] = tileArray[col][row];
//                    //empty the original tile position after tile is moved
//                    tileArray[col][row] = 0;
//                    emptyPosPointer++;
//                }
//            }
//        }
//    }

//    public void right(){
//        for (int row = 0; row < Game.gridSize; row++) { //start from first row, filter each row
//            int emptyPosPointer = Game.gridSize-1; // parameter of empty tile
//            for (int col = Game.gridSize-1; col >= 0; col--) { //filter each tile in row
//                if (tileArray[emptyPosPointer][row] != 0){
//                    emptyPosPointer--;
//                }
//                else if (tileArray[col][row] != 0) {
//                    tileArray[emptyPosPointer][row] = tileArray[col][row];
//                    //empty the original tile position after tile is moved
//                    tileArray[col][row] = 0;
//                    emptyPosPointer--;
//                }
//            }
//        }
//    }

    public void destroy(int x, int y) {
        tileArray[x][y] = 0;
    }

    public void doubleTile(int x, int y)
    {
        tileArray[x][y] *= 2;
    }

    public void spawn2Tile(int x, int y)
    {
        tileArray[x][y] = 2;
    }
}
