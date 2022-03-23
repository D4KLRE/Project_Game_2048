package edu.wcc.cs240;

/**
 * implemented special int array list
 * @author Ting Gao, Yushi Yao
 */
class IntArrayList
{
    private int[] intArr;
    private int size;

    /**
     * create an array of length equals to number of tiles and put index number into each position
     * @param gridSize size of current gird
     */
    IntArrayList(int gridSize)
    {
        int length = (int)Math.pow(gridSize, 2);

        intArr = new int[length];
        size = length;

        for (int i = 0; i < intArr.length; i++)
        {
            intArr[i] = i;
        }
    }

    int getSize()
    {
        return size;
    }

    /**
     * return the element at specified index
     * @param index the specified index of element to be returned
     */
    int get(int index)
    {
        return intArr[index];
    }

    /**
     * remove the element at specified index, move subsequent elements forward, size minus one
     * @param index the specified index of element to be removed
     */
    void remove(int index)
    {
        for (int i = index; i < size - 1; i++)
        {
            intArr[i] = intArr[i + 1];
        }

        size--;
    }
}
