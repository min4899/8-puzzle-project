// Node class specifically for the 8-puzzle project.
public class Node
{
  private int pathCost;
  private int fValue;
  private int blankIndex;
  private String action;
  private Node parent;
  private int[] puzzle;
  private Node next; // For setting in Frontier or Explored queue.

  // Constructor for the Initial Node.
  public Node(int[] puzzle, int blankIndex)
  {
    parent = null;
    pathCost = 0;
    fValue = 0;
    next = null;
    action = "Start";
    this.blankIndex = blankIndex;
    this.puzzle = puzzle;
  } // end constructor

  // Used for generating puzzles at certain depth. Used with generatePuzzleAtDepth.
  public Node(Node expand, int swapIndex)
  {
    pathCost = expand.pathCost + 1;
    fValue = 0;
    next = null;
    action = "";
    parent = expand;
    // Assign new puzzle array
    blankIndex = expand.blankIndex;
    puzzle = expand.puzzle.clone();
    // Moving blanks
    int temp = puzzle[blankIndex];
    puzzle[blankIndex] = puzzle[swapIndex];
    puzzle[swapIndex] = temp;
    blankIndex = swapIndex;
  } // end constructor

  /** Creates a node using the parent node and then connecting to parent node.
      @param parent  The parent node. */
  public Node(Node parent, int swapIndex, String action, int hOption)
  {
    this.parent = parent;
    pathCost = parent.pathCost + 1;

    // Assign new puzzle array
    blankIndex = parent.blankIndex;
    puzzle = parent.puzzle.clone();
    // Moving blanks
    int temp = puzzle[blankIndex];
    puzzle[blankIndex] = puzzle[swapIndex];
    puzzle[swapIndex] = temp;

    this.action = action;
    if(hOption == 1)
    {
      fValue = pathCost + getH1Value();
    }
    else if(hOption == 2)
    {
      fValue = pathCost + getH2Value();
    }
    else // no heuristic selection, use default search
    {
      fValue = pathCost;
    }
    next = null;
    blankIndex = swapIndex;
  } // end constructor

  public void setNext(Node next)
  {
    this.next = next;
  }

  public int getBlankIndex()
  {
    return blankIndex;
  }

  public String getAction()
  {
    return action;
  }

  public Node getNext()
  {
    return next;
  }

  public int getFValue()
  {
    return fValue;
  }

  public int getPathCost()
  {
    return pathCost;
  }

  public int[] state()
  {
    return puzzle;
  }

  public int getH1Value()
  {
    int count = 0;
    for(int i = 0; i < 9; i++)
    {
      if(i != puzzle[i])
        count++;
    }
    return count;
  }

  public int getH2Value()
  {
    int total = 0;
    for(int i = 0; i < 9; i++)
    {
      int index = i;
      int block = puzzle[i];
      int horizontal = Math.abs(block%3 - index%3);
      int vertical = Math.abs((block-block%3)-(index-index%3))/3;
      int cost = horizontal + vertical;
      total += cost;
    }
    return total;
  } // end getH2Value

  public boolean checkGoal()
  {
    for(int i = 0; i < 9; i++)
    {
      if(puzzle[i] != i)
      {
        return false;
      }
    }
    return true;
  }

  public int[] getAdjacent()
  {
    switch(blankIndex)
    {
      case 0:
        return new int[] {1, 3};
      case 1:
        return new int[] {0, 2, 4};
      case 2:
        return new int[] {1, 5};
      case 3:
        return new int[] {0, 4, 6};
      case 4:
        return new int[] {1, 3, 5, 7};
      case 5:
        return new int[] {2, 4, 8};
      case 6:
        return new int[] {3, 7};
      case 7:
        return new int[] {4, 6, 8};
      case 8:
        return new int[] {5, 7};
      default:
        return new int[] {};
    }
  } // end getAdjacent

  public String[] getAdjacentActions()
  {
    switch(blankIndex)
    {
      case 0:
        return new String[] {"Right", "Down"};
      case 1:
        return new String[] {"Left", "Right", "Down"};
      case 2:
        return new String[] {"Left", "Down"};
      case 3:
        return new String[] {"Up", "Right", "Down"};
      case 4:
        return new String[] {"Up", "Left", "Right", "Down"};
      case 5:
        return new String[] {"Up", "Left", "Down"};
      case 6:
        return new String[] {"Up", "Right"};
      case 7:
        return new String[] {"Up", "Left", "Right"};
      case 8:
        return new String[] {"Up", "Left"};
      default:
        return new String[] {};
    }
  } // end getAdjacentActions

  public String getSolution()
  {
    String output = "";
    Node current = this;
    while(current != null)
    {
      output = current.getAction() + " " + output;
      current = current.parent;
    }
    return output;
  } // end getSolution

  /** Compares two datas to see which has priority.
      @param other  The node that will be compared with.
      @return  0 if equal, less than 0 if other is greater, greater than 0 if other is lower.*/
  public int compareTo(Node other)
  {
    if(fValue == other.fValue)
    {
      return 0;
    }
    else if(fValue < other.fValue)
    {
      return -1;
    }
    else // fValue > other.getFValue
    {
      return 1;
    }
  } // end compareTo
} // end of Node
