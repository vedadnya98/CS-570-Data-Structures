package Maze;

import java.util.*;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
//    	ArrayList<ArrayList<PairInt>> allPaths = findAllMazePaths(0,0);
//    	ArrayList<PairInt> optimal = findMazePathMin(0,0);
//    	System.out.println("All Paths : \n");
//    	for(int i=0 ; i<allPaths.size();i++)
//    	{
//    		System.out.println(i+"."+allPaths.get(i));
//    	}
//    	System.out.println("Minimum Path : \n"+optimal);
//    	return true;
    	return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    
    //METHOD FOR PROBLEM 1
    public boolean findMazePath(int x, int y) {
        
    	// • If the current cell being analyzed falls outside the grid , false is returned
    	if(x<0||y<0||x>=maze.getNCols()||y>=maze.getNRows()) 
     	{
     		return false;
     	}
        // • If the current cell being analyzed does not have NON_BACKGROUND , false is returned
    	else if(!maze.getColor(x,y).equals(NON_BACKGROUND)) 
     	{
     		return false;
     	}	
        /* • If the current cell being analyzed is the exit cell then,
         * • The cell is painted color PATH
         * • True is returned
         */
    	else if(x==maze.getNCols()-1&&y==maze.getNRows()-1) 
     	{
     			maze.recolor(x,y,PATH);
     			return true;
     	} 
        /* • Current cell is assumed to be part of the path and,
         * • It is painted to color PATH.
         * • Recursive method is called for the neighbors.
         * • If any neighbor's recursive method is successful then a path is formed
         * • Else the current cell is colored to TEMPORARY
         */
        else 
        {
            maze.recolor(x, y, PATH);

            // if the neighbour points of current point is exit, return true
            if (findMazePath(x+1,y)||findMazePath(x-1,y)||findMazePath(x,y+1)||findMazePath(x,y-1))
			{
				return true;
            }
            // else, recolor current point to TEMPORARY
            else 
			{
				maze.recolor(x,y,TEMPORARY);
				return false;
			}
        }
    	
    } 
    

    
    // METHOD FOR PROBLEM 2.1
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace)
    {
    	// • If the current cell being analyzed falls outside the grid , exit
    	if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || (!maze.getColor(x, y).equals(NON_BACKGROUND)))
    	{
            return;
    	}
        /* • If exit Pair is found, then push the current point to trace, and then add to result
         * • After visiting this point, remove from trace
         * • Recolor this point to Non_background for Re-visiting
    	*/
        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) 
        {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> currentTrace = new ArrayList<>(trace);
            result.add(currentTrace);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
    	/* • Current cell is pushed to trace
         * • It is painted to color PATH before recursion.
         * • Recursive method is called for the neighbors.
         * • After tried all possible paths of current point, recolor it to NON_BACKGROUND.
         * • Implementing Backtracking in this method
         */
        else
        {
        	trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }
    
    
    
    //METHOD FOR PROBLEM 2.2
    public ArrayList < ArrayList < PairInt >> findAllMazePaths ( int x , int y) 
    {
    	ArrayList < ArrayList < PairInt >> result = new ArrayList < >();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased (0 ,0 , result , trace );
    	return result ;	
    }
    

    
    //METHOD FOR PROBLEM 3
    public ArrayList<PairInt> findMazePathMin(int x, int y)
    {
    	ArrayList <ArrayList<PairInt>> all = findAllMazePaths(x, y);
    	ArrayList<PairInt> current = new ArrayList<>();
    	ArrayList<PairInt> minPath = new ArrayList<>();
    	int[] min;
    	int minimum;
    	int index=0;
    	
    	min = new int[all.size()];
    	
    	for(int i=0 ; i<all.size() ; i++)
    	{
    		current = all.get(i);
    		min[i] = current.size();
    	}
    	
    	minimum = min[0];
    	
    	for(int j=1 ; j<min.length ; j++)
    	{
    		if(min[j] < minimum)
    		{
    			  minimum = min[j];
    			  index = j;
    		}
    	}
    	
    	minPath = all.get(index);
    	return minPath;
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
