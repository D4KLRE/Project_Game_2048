package edu.wcc.cs240;

/**
 * the grid class
 * contains methods to move tiles around, spawn new tiles, and use consumables
 */

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
}
