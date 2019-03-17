import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main
{

  static int nodesGenerated = 0;

  public static void main(String[] args)
  {
    Scanner reader = new Scanner(System.in);

    System.out.println("--- 8-Puzzle Project by Minwoo Soh ---");
    System.out.println("Choose an option (1 to 3):");
    System.out.println("1. Use a random puzzle.");
    System.out.println("2. Create a puzzle.");
    System.out.println("3. Test cases. \n");
    System.out.print("Input: ");

    int input = 0;
    while(true)
    {
      if(reader.hasNextInt())
      {
        input = reader.nextInt();
        if(input == 1 || input == 2 || input == 3)
        {
          break;
        }
        else
        {
          System.out.println("Not a valid option. Please select 1 or 2.");
          System.out.print("Input: ");
        }
      }
      else
      {
        reader.next();
        System.out.println("Not a valid option. Please select option from 1 - 3.");
        System.out.print("Input: ");
      }
    }
    System.out.println();

    switch(input)
    {
      case 1:
        optionOne();
        break;
      case 2:
        optionTwo();
        break;
      case 3:
        optionThree();
        break;
      default:
        System.out.println("Not a valid option.");
    }
  } // end main

  public static void optionOne()
  {
    System.out.println("-- Random Puzzle: --");
    System.out.println();
    int[] puzzle = generatePuzzle();
    for(int i = 0; i < 9; i++)
    {
      System.out.print(puzzle[i] + " ");
    }
    System.out.println("\n");

    generateResults(puzzle);
  } // end optionOne()

  public static void optionTwo()
  {
    Scanner reader = new Scanner(System.in);

    System.out.println(" -- User-generated Puzzle -- \n");
    System.out.println("Please provide a puzzle in an array format.");
    System.out.println("0 represents the empty tile and the digits are separated by space. \n");
    int[] puzzle = {};
    while(true)
    {
      System.out.print("Enter a puzzle: ");
      String input = reader.nextLine();

      // check if string contains only digits and whitespaces
      boolean check = true;
      for(int i = 0; i < input.length(); i++)
      {
        char ch = input.charAt(i);
        if(check && !Character.isDigit(ch) && !Character.isWhitespace(ch))
        {
          System.out.println("Array must contain only digits.");
          check = false;
        }
      }
      if(check)
      {
        input = input.trim();
        // convert string to int array
        String[] parts = input.split(" ");
        puzzle = new int[parts.length];
        for(int i = 0; i < parts.length; i++)
        {
          puzzle[i] = Integer.parseInt(parts[i]);
        }
        // check if puzzle is valid, if it is, break out of loop
        if(checkPuzzle(puzzle))
        {
          break;
        }
      }
    } // end while, puzzle is valid if out of loop
    System.out.println();

    System.out.print("Using Puzzle: ");
    for(int i = 0; i < 9; i++)
    {
      System.out.print(puzzle[i] + " ");
    }
    System.out.println("\n");

    generateResults(puzzle);
  } // end optionTwo

  public static void optionThree()
  {
    Scanner reader = new Scanner(System.in);

    System.out.println(" -- Test Case -- \n");
    System.out.print("Please provide a depth to test (between 2 to 24): ");
    int depth = 0;
    while(true)
    {
      if(reader.hasNextInt())
      {
        depth = reader.nextInt();
        if(depth >= 2 && depth <= 24)
        {
          break;
        }
        else
        {
          System.out.println("Not a valid option. Please select a depth between 2 to 24.");
          System.out.print("Please provide a depth: ");
        }
      }
      else
      {
        reader.next();
        System.out.println("Not a valid option. Please select a number between 2 to 24.");
        System.out.print("Please provide a depth: ");
      }
    }
    System.out.println();

    System.out.print("Choose the number of test cases (1000 or greater): ");
    int testcases = 0;
    while(true)
    {
      if(reader.hasNextInt())
      {
        testcases = reader.nextInt();
        if(testcases >= 1000)
        {
          break;
        }
        else
        {
          System.out.println("Not a valid option. Please select a number greater than or equal to 1000.");
          System.out.print("Choose the number of test cases: ");
        }
      }
      else
      {
        reader.next();
        System.out.println("Not a valid option. Please select a number greater than or equal to 1000.");
        System.out.print("Choose the number of test cases: ");
      }
    }
    System.out.println();

    System.out.println("Generating " + testcases + " test cases with depth " + depth + "\n");

    int total = 0;
    double totalTime = 0.0;
    int average = 0;
    double averageTime = 0.0;
    long start = 0;
    long end = 0;
    long executionTime = 0;
    double seconds = 0.0;

    // for h1
    for(int i = 0; i < testcases; i++)
    {
      //System.out.println("Test case" + i);
      int[] puzzle = generatePuzzleAtDepth(depth);

      /**
      for(int j = 0; j < puzzle.length; j++)
      {
        System.out.print(puzzle[j] + " ");
      }
      System.out.println();
      */

      start = System.nanoTime();
      Node solution1 = AStarSearch(puzzle, 1);
      end = System.nanoTime();
      executionTime = end - start;
      seconds = (double)executionTime/1000000000.0;
      total += nodesGenerated;
      totalTime += seconds;
      nodesGenerated = 0;
    }
    average = total/testcases;
    averageTime = totalTime/testcases;
    System.out.println("h1 heuristic average search cost: " + average);
    System.out.println("h1 heuristic average run time (seconds): " + averageTime + "\n");
    total = 0;
    totalTime = 0;
    nodesGenerated = 0;

    // for h2
    for(int i = 0; i < testcases; i++)
    {

      //System.out.println("Test case" + i);
      int[] puzzle = generatePuzzleAtDepth(depth);

      /**
      for(int j = 0; j < puzzle.length; j++)
      {
        System.out.print(puzzle[j] + " ");
      }
      System.out.println();
      */

      start = System.nanoTime();
      Node solution1 = AStarSearch(puzzle, 2);
      end = System.nanoTime();
      executionTime = end - start;
      seconds = (double)executionTime/1000000000.0;
      total += nodesGenerated;
      totalTime += seconds;
      nodesGenerated = 0;
    }
    average = total/testcases;
    averageTime = totalTime/testcases;
    System.out.println("h2 heuristic average search cost: " + average);
    System.out.println("h2 heuristic average run time (seconds): " + averageTime);
  } // end optionThree

  public static void generateResults(int[] puzzle)
  {
    long start = System.nanoTime();
    Node solution1 = AStarSearch(puzzle, 1);
    long end = System.nanoTime();
    long executionTime = end - start;
    double seconds = (double)executionTime/1000000000.0;
    System.out.println("- Result for h1 heuristic (number of misplaced tiles): -");
    System.out.println("Actions: " + solution1.getSolution());
    System.out.println("Depth: " + solution1.getPathCost());
    System.out.println("Search Cost (Nodes Generated): " + nodesGenerated);
    System.out.println("Search Time (seconds): " + seconds);
    System.out.print("Final State: ");
    int[] finished = solution1.state();
    for(int j = 0; j < finished.length; j++)
    {
      System.out.print(finished[j] + " ");
    }
    System.out.println("\n");

    nodesGenerated = 0;
    start = System.nanoTime();
    Node solution2 = AStarSearch(puzzle, 2);
    end = System.nanoTime();
    executionTime = end - start;
    seconds = (double)executionTime/1000000000.0;
    System.out.println("- Result for h2 heuristic (sum of the distance of the tiles from their goal positions): -");
    System.out.println("Actions: " + solution2.getSolution());
    System.out.println("Depth: " + solution2.getPathCost());
    System.out.println("Search Cost (Nodes Generated): " + nodesGenerated);
    System.out.println("Search Time (seconds): " + seconds);
    System.out.print("Final State: ");
    finished = solution2.state();
    for(int j = 0; j < finished.length; j++)
    {
      System.out.print(finished[j] + " ");
    }
    System.out.println();
  } // end generateResults

  public static Node AStarSearch(int[] puzzle,int heuristic)
  {
    int index = -1;
    for(int i = 0; i < 9; i++)
    {
      if(puzzle[i] == 0)
      {
        index = i;
      }
    }

    Node problem = new Node(puzzle, index);
    LinkedPriorityQueue frontier = new LinkedPriorityQueue();
    frontier.add(problem);
    Node exploredList = null;

    while(true)
    {
      if(frontier.isEmpty())
      {
        return null;
      }

      Node expand = frontier.remove();
      nodesGenerated++;
      if(expand.checkGoal())
      {
        return expand;
      }

      if(exploredList == null)
      {
        exploredList = expand;
      }
      else
      {
        exploredList.setNext(expand);
      }

      int[] possibleSpaces = expand.getAdjacent();
      String[] possibleActions = expand.getAdjacentActions();
      for(int i = 0; i < possibleActions.length; i++)
      {
        Node childNode = new Node(expand, possibleSpaces[i], possibleActions[i], heuristic);
        //nodesGenerated++;
        //System.out.println("Testing actions");
        if(!frontier.checkFor(childNode) && !checkList(exploredList, childNode)) // If not in frontier or explored
        {
          //System.out.println("Action added");
          frontier.add(childNode);
          //nodesGenerated++;
        }
        else if(frontier.checkFor(childNode))
        {
          frontier.replaceWithBetter(childNode);
          //System.out.println("Replaced");
        }
      }
    } // end while
  } // end AStarSearch

  public static boolean checkList(Node list, Node target)
  {
    if(list == null)
    {
      return false;
    }
    while(list != null)
    {
      if(Arrays.equals(list.state(), target.state()))
      {
        return true;
      }
      list = list.getNext();
    }
    return false;
  } // end checkList

  public static int[] generatePuzzle()
  {
    Random rand = new Random();
    int[] puzzle = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    do
    {
      for(int i = 0; i < 9; i++)
      {
        int random = rand.nextInt(9);
        int temp = puzzle[random];
        puzzle[random] = puzzle[i];
        puzzle[i] = temp;
      }
    } while(!checkInversion(puzzle)); // // generate another puzzle if current one is not valid
    return puzzle;
  } // end generatePuzzle

  public static boolean checkPuzzle(int[] puzzle)
  {
    // check if puzzle size is valid, must be 9
    if(puzzle.length != 9)
    {
      System.out.println("Array must only contain 9 entries.");
      return false;
    }
    // check if digits are only 0-8
    for(int i = 0; i < 9; i++)
    {
      if(puzzle[i] < 0 || puzzle[i] > 8)
      {
        System.out.println("Digits must be between 0-8.");
        return false;
      }
    }
    // check for duplicates
    for(int i = 0; i < 8; i++)
    {
      for(int j = i+1; j < 9; j++)
      {
        if(puzzle[i] == puzzle[j])
        {
          System.out.println("Array must not contain any duplicates.");
          return false;
        }
      }
    }
    // check if puzzle is solvable
    if(!checkInversion(puzzle))
    {
      System.out.println("Puzzle is unsolvable.");
      return false;
    }
    return true;
  } // end checkPuzzle

  public static boolean checkInversion(int[] puzzle)
  {
    int inversionCount = 0;
    for(int i = 0; i < 8; i++)
    {
      for(int j = i+1; j < 9; j++)
      {
        if((puzzle[i] != 0) && (puzzle[j] != 0) && (puzzle[i] > puzzle[j]))
        {
          inversionCount++;
        }
      }
    }
    return inversionCount % 2 == 0;
  } // end checkInversion

  public static int[] generatePuzzleAtDepth(int depth)
  {
    Random rand = new Random();
    int[] puzzle = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    Node expand = new Node(puzzle, 0);

    int tries = 0;
    int reset = depth;
    //System.out.println("Generating for depth " + depth);
    while(depth > 0)
    {
      //System.out.println("Selecting Action");
      int[] possibleSpaces = expand.getAdjacent();
      while(tries < 4)
      {
        int random = rand.nextInt(possibleSpaces.length);
        //Node nextNode = new Node(expand, possibleSpaces[random], possibleActions[random]);
        Node nextNode = new Node(expand, possibleSpaces[random]);
        //System.out.println("Testing Action");
        if(!checkList(expand, nextNode)) // if state was already visited.
        {
          //System.out.println("Action selected");
          nextNode.setNext(expand);
          expand = nextNode;
          break;
        }
        tries++;
      }
      if(tries >= 4) // stuck in loop, restart puzzle making
      {
        depth = reset;
        expand = new Node(puzzle, 0);
      }
      else
      {
        depth--;
      }
      tries = 0;
      if(depth == 0) // check if depth matches up with number of parents.
      {
        /**
        if(reset != expand.getPathCost())
        {
          depth = reset;
          expand = new Node(puzzle, 0);
        }
        */
        Node solution = AStarSearch(expand.state(), 2);
        if(solution.getPathCost() != reset)
        {
          depth = reset;
          expand = new Node(puzzle, 0);
        }
      }
    } // end while
    //System.out.println("Complete");
    return expand.state();
  } // end generatePuzzleAtDepth

} // end of class
