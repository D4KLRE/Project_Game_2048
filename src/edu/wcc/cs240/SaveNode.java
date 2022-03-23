package edu.wcc.cs240;

import java.util.Arrays;

/**
 * implemented special save node
 * @author Ting Gao
 */
class SaveNode
{
    int[][] data;
    SaveNode next;

    SaveNode(int[][] newData)
    {
        data = new int[newData.length][];

        for (int i = 0; i < newData.length; i++)
        {
            data[i] = Arrays.copyOf(newData[i], newData[i].length);
        }
    }
}
