import java.util.Arrays;

/**
   A single linked priority queue specifically for 8-puzzle.
   @author Minwoo Soh
*/
public final class LinkedPriorityQueue
{
  private Node firstNode;
  private Node lastNode;
  private int numberOfEntries;

  /** Creates an empty LinkedPriorityQueue object with no nodes. */
  public LinkedPriorityQueue()
  {
    firstNode = null;
    lastNode = null;
    numberOfEntries = 0;
  } // end constructor

  /** Adds a new entry to this priority queue.
      @param newEntry  An object to be added. */
  public void add(Node newNode)
  {
    if(isEmpty())
    {
      firstNode = newNode;
      lastNode = newNode;
      numberOfEntries++;
    }
    else
    {
      // The lower value has the higher priority.
      if(newNode.compareTo(firstNode) <= 0) // If entry is lower than or equal to firstNode. First in Queue.
      {
        newNode.setNext(firstNode);
        firstNode = newNode;
        numberOfEntries++;
      }
      else if(newNode.compareTo(lastNode) > 0) // If entry is greater than lastNode. Last in Queue.
      {
        lastNode.setNext(newNode);
        lastNode = newNode;
        numberOfEntries++;
      }
      else // Check entries after firstNode and before lastNode.
      {
        Node currentNode = firstNode;
        boolean inserted = false;
        while( (currentNode.getNext() != null) && (inserted == false))
        {
          if(newNode.compareTo(currentNode.getNext()) <= 0)
          {
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
            inserted = true;
          } // end if
          currentNode = currentNode.getNext();
        } // end while
      } // end if
      numberOfEntries++;
    } // end if
  } // end add

  public void replaceWithBetter(Node node)
  {
    if(Arrays.equals(firstNode.state(), node.state()))
    {
      if(firstNode.getFValue() > node.getFValue())
      {
        node.setNext(firstNode.getNext());
        firstNode = node;
        if(getSize() == 1)
        {
          lastNode = node;
        }
      }
    }
    else if(Arrays.equals(lastNode.state(), node.state()))
    {
      if(lastNode.getFValue() > node.getFValue())
      {
        Node temp = firstNode;
        while(temp.getNext() != lastNode)
        {
          temp = temp.getNext();
        }
        temp.setNext(node);
        lastNode = node;
      }
    }
    else
    {
      Node current = firstNode;
      while(current != null)
      {
        if(Arrays.equals(current.state(), node.state())) // same state
        {
          if(current.getFValue() >= node.getFValue())
          {
            node.setNext(current.getNext());
            Node temp = firstNode;
            while(temp.getNext() != current)
            {
              temp = temp.getNext();
            }
            temp.setNext(node);
          }
          break;
        }
        current = current.getNext();
      }
    }
  } // end replaceWithBetter

  /** Removes and returns the entry having the highest priority.
      @return  Either the object having the highest priority or,
               if the priority queue is empty before the operation, null. */
  public Node remove()
  {
    Node result = null;
    if(isEmpty())
    {
      return result;
    }
    else if(numberOfEntries == 1)
    {
      result = firstNode;
      firstNode = null;
      lastNode = null;
      numberOfEntries--;
    }
    else
    {
      result = firstNode;
      firstNode = firstNode.getNext();
      numberOfEntries--;
    }
    return result;
  } // end remove

  /** Retrieves the entry having the highest priority.
      @return  Either the object having the highest priority or,
               if the priority queue is empty, null. */
  public Node peek()
  {
    Node result = null;
    if(isEmpty())
    {
      return result;
    }
    else
    {
      result = firstNode;
      return result;
    } // end if
  } // end peek

  /** Detects whether this priority queue is empty.
      @return  True if the priority queue is empty, or false otherwise. */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  } // end isEmpty

  /** Gets the size of this priority queue.
      @return  The number of entries currently in the priority queue. */
  public int getSize()
  {
    return numberOfEntries;
  } // end getSize

  /** Removes all entries from this priority queue. */
  public void clear()
  {
    firstNode = null;
    lastNode = null;
    numberOfEntries = 0;
  } // end clear

  public boolean checkFor(Node node)
  {
    if(isEmpty())
    {
      return false;
    }
    Node current = firstNode;
    while(current != null)
    {
      if(Arrays.equals(current.state(), node.state()))
      {
        return true;
      }
      current = current.getNext();
    }
    return false;
  } // end checkFor

} // end of LinkedPriorityQueue
