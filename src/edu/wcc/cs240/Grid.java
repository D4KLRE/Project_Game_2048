package edu.wcc.cs240;

/** \　　/
 * 【 ﾟ∀ﾟ】< Cancan, Jacqueline, this is where you should write your code, please don't touch code in Game.java
 * except removing '//' in front of the method you write in this java file
 */

/**
 * the grid class
 * contains methods to move/merge tiles, spawn new tiles, and use consumables
 */
class Grid
{
    int[][] tileArray;

    /**
     * construct a grid with given size
     * @param gridSize size of new grid
     * @author Ting Gao
     */
    Grid(int gridSize)
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

    /**
     * spawn one or more "2" tile (number of tile spawned = (int) size of grid / 4) at random empty position, nonempty
     * position won't be selected twice
     * @author Ting Gao
     */
    void spawn()
    {
        //Create an array list to store each position in grid. Delete the randomly selected position if it's not empty,
        //thus it won't be randomly selected again, preventing resource loss. In addition, there won't be an infinite
        // loop trying to find an empty position if all tiles are not 0.
        IntArrayList posArrayList = new IntArrayList(Game.gridSize);

        int spawned = 0;
        do
        {
            if (posArrayList.getSize() == 0)
            {
                System.out.println("All tiles are not empty, spawn method aborted");
                break;
            }
                //randomly select a position from the array containing all position excepts confirmed nonempty ones
                int pos = (int)(Math.random()* posArrayList.getSize());

                //Convert one-dimensional position to two-dimensional position
                int x = posArrayList.get(pos) % Game.gridSize;
                int y = posArrayList.get(pos) / Game.gridSize;

                if (tileArray[x][y] == 0)
                {
                    tileArray[x][y] = 2;
                    System.out.println("'2' tile spawned at " + x + ", " + y);
                    spawned++;
                }
                else
                {
                    //remove confirmed nonempty position from the array, so it won't be randomly selected again
                    posArrayList.remove(pos);
//                  System.out.println(posArrList.get(pos) + " at index " + pos + " removed.");
//                  System.out.println("x: " + x + " y: " + y);
//                  System.out.println("Size of posArrList remained: " + posArrList.size());

                }
            }while (spawned < Game.numTileSpawn);
    }

    /**
     * trying to move tiles up
     * @author Jacqueline Tan
     */
    void up(){
        for (int col = 0; col < Game.gridSize; col++) { //start from first col, filter each column
            int emptyPosPointer = 0; // parameter of empty tile
            for (int row = 0; row < Game.gridSize; row++) { //filter each row in the column
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

    /**
     * trying to move tiles down
     * @author Jacqueline Tan
     */
    void down(){  //start from first col, filter each column
        for (int col = 0; col < Game.gridSize; col++) {
            int emptyPosPointer = Game.gridSize-1; // parameter of empty tile
            for (int row = Game.gridSize-1; row >= 0; row--) { //filter each row in the column
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

    /**
     * trying to move tiles left
     * @author Jacqueline Tan
     */
    void left(){
        for (int row = 0; row < Game.gridSize; row++) { //start from first row, filter each row
            int emptyPosPointer = 0; // parameter of empty tile
            for (int col = 0; col < Game.gridSize; col++) { //filter each tile in row
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

    /**
     * trying to move tiles right
     * @author Jacqueline Tan
     */
    void right(){
        for (int row = 0; row < Game.gridSize; row++) { //start from first row, filter each row
            int emptyPosPointer = Game.gridSize-1; // parameter of empty tile
            for (int col = Game.gridSize-1; col >= 0; col--) { //filter each tile in row
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

    void upMerge() {
        for (int row = 1; row < Game.gridSize; row++) {
            for (int col = 0; col < Game.gridSize; col++) {
                if (tileArray[col][row - 1] == tileArray[col][row] && tileArray[col][row] < 2048) {
                    if (tileArray[col][row - 1]!=0)
                    Game.Score=Game.Score+tileArray[col][row - 1]*2;
                    tileArray[col][row - 1] *= 2;
                    tileArray[col][row] = 0;
                }
            }
        }
    }

    void downMerge() {
        for (int row = Game.gridSize - 2; row >= 0; row--) {
            for (int col = 0; col < Game.gridSize; col++) {
                if (tileArray[col][row + 1] == tileArray[col][row] && tileArray[col][row] < 2048) {
                    if (tileArray[col][row + 1]!=0){
                        Game.Score=Game.Score+ tileArray[col][row + 1] *2;
                    }
                    tileArray[col][row + 1] *= 2;
                    tileArray[col][row] = 0;
                }
            }
        }
    }

    void leftMerge() {
        for (int row = 0; row < Game.gridSize; row++) {
            for (int col = 1; col < Game.gridSize; col++) {
                if (tileArray[col - 1][row] == tileArray[col][row] && tileArray[col][row] < 2048) {
                    if (tileArray[col - 1][row]!=0){
                        Game.Score=Game.Score+tileArray[col - 1][row]*2;
                    }
                    tileArray[col - 1][row] *= 2;
                    tileArray[col][row] = 0;
                }
            }
        }
    }

    void rightMerge() {
        for (int row = 0; row < Game.gridSize; row++) {
            for (int col = Game.gridSize - 2; col >= 0; col--) {
                if (tileArray[col + 1][row] == tileArray[col][row] && tileArray[col][row] < 2048) {
                    if (tileArray[col + 1][row]!=0){
                        Game.Score=Game.Score+tileArray[col + 1][row]*2;
                    }
                    tileArray[col + 1][row] *= 2;
                    tileArray[col][row] = 0;
                }
            }
        }
    }

    /**
     * @author Cancan Huang
     */
    void destroy(int x, int y) {
        tileArray[x][y] = 0;
    }

    /**
     * @author Ting Gao
     */
    void doubleTile(int x, int y)
    {
        tileArray[x][y] *= 2;
    }

    /**
     * @author Cancan Huang
     */
    void spawn2Tile(int x, int y)
    {
        tileArray[x][y] = 2;
    }
}