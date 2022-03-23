package edu.wcc.cs240;

/**
 * implemented special save stack
 * @author Ting Gao
 */
class SaveStack
{
    private SaveNode top;

    SaveStack(SaveNode newTop)
    {
        top = newTop;
    }

    void push(SaveNode newNode)
    {
        newNode.next = top;
        top = newNode;
    }

    SaveNode pull()
    {
        SaveNode pulledNode = top;
        top = top.next;
        return pulledNode;
    }

    boolean isEmpty()
    {
        return top == null;
    }
}
