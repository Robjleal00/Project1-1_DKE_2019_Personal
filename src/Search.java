import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Search {
	
    public final int NUMBER_OF_ROWS = 5;                   // the number of rows
    public final int NUMBER_OF_COLS = 12;                   // the number of columns
    public final int DEFAULT_CELL_SIZE = 50;                
    public final int PENT_SIZE = 5;
    public final int LOC_CENT_X = 2;
    public final int LOC_CENT_Y = 2;
    public final char[] INPUT = {'X', 'I', 'Z', 'T', 'U', 'V', 'W', 'Y', 'L', 'P', 'N', 'F'};    // sample INPUT
    
    public PentominoBuilder database = new PentominoBuilder();
    public UI ui = new UI(NUMBER_OF_ROWS, NUMBER_OF_COLS, DEFAULT_CELL_SIZE);
    
    public int[][] field = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];
    /*public int[][] field = { {-1, -1, -1, -1, -1, -1},
    		                 {-1, -1, -1, -1, -1, -1},
    		                 {-1, -1, -1, -1, -1, -1},
    		                 {-1, -1, -1, -1, -1, -1},
    		                 {-1, -1, -1, -1, -1, -1},
    	                   	 {-1, -1, -1, -1, -1, -1} };*/
    public int[] mask = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
 
    // Helper function which starts the brute force algorithm
    public Search() {
    	long start = System.nanoTime();
    	
    	// Initialize an empty board
    	
        for(int i = 0; i < NUMBER_OF_ROWS; i++) {
            for(int j = 0; j < NUMBER_OF_COLS; j++) {
                // -1 in the state matrix corresponds to empty square
                // Any positive number identifies the ID of the pentomino
                field[i][j] = -1;
            }
        }
        
        boolean result = false;
        for (int centY = 0; centY < NUMBER_OF_ROWS && (!result); centY++) {
        	for (int centX = 0; centX < NUMBER_OF_COLS && (!result); centX++) {
        		result = backtracking(centX, centY);
        	}
        }
        
        long finish = System.nanoTime();
        System.out.println("Execution time is " + (double)(finish - start) / 1e9 + " s");
        
        if (!result) {
        	ui.closeWindow();
        	System.out.println("No solution");
        	System.out.println("Finished");
        } else {
        	ui.setState(field);
        	try {
    		    Thread.sleep(5000);
    		}
    		catch(InterruptedException ex) {
    		    Thread.currentThread().interrupt();
    		}
            
            ui.closeWindow();
            System.out.println("Solution found");
            System.out.println("Finished");
        }
    }
   
    public int characterToID(char character) {
        int pentID = -1;
        if (character == 'X') {
            pentID = 0;
        } else if (character == 'I') {
            pentID = 1;
        } else if (character == 'Z') {
            pentID = 2;
        } else if (character == 'T') {
            pentID = 3;
        } else if (character == 'U') {
            pentID = 4;
        } else if (character == 'V') {
            pentID = 5;
        } else if (character == 'W') {
            pentID = 6;
        } else if (character == 'Y') {
            pentID = 7;
        } else if (character == 'L') {
            pentID = 8;
        } else if (character == 'P') {
            pentID = 9;
        } else if (character == 'N') {
            pentID = 10;
        } else if (character == 'F') {
            pentID = 11;
        }
        return pentID;
    }
   
    public void goBack(int pentID) {
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLS; j++) {
                if (field[i][j] == pentID) {
                    field[i][j] = -1;
                }
            }
        }
    }
    
    public boolean tryToPush(int pentID, int glCentX, int glCentY) {
    	int[][]locField = database.getPent(pentID);
    	
    	for (int locY = 0; locY < PENT_SIZE; locY++) {
    		for (int locX = 0; locX < PENT_SIZE; locX++) {
    			if (locField[locY][locX] == 0)	
    				continue;
    			
    			int globalY = (locY - LOC_CENT_Y) + glCentY;
    			int globalX = (locX - LOC_CENT_X) + glCentX;
    			
    			if (!(0 <= globalY && globalY < NUMBER_OF_ROWS))
    				return false;
    			if (!(0 <= globalX && globalX < NUMBER_OF_COLS))
    				return false;
    			if (field[globalY][globalX] != -1)
    				return false;
    			
    			field[globalY][globalX] = pentID;
    		}
    	}
    	return true;
    }
    
    public boolean[][] used = new boolean[NUMBER_OF_ROWS][NUMBER_OF_COLS];
    public int dfs(int i, int j) {
    	used[i][j] = true;
    	int sum = 1;
    	if (i != 0)
    		if (!used[i - 1][j])
    			sum += dfs(i - 1, j);
    	if (i != NUMBER_OF_ROWS - 1)
    		if (!used[i + 1][j])
    			sum += dfs(i + 1, j);
    	if (j != 0)
    		if (!used[i][j - 1])
    			sum += dfs(i, j - 1);
    	if (j != NUMBER_OF_COLS - 1)
    		if (!used[i][j + 1])
    			sum += dfs(i, j + 1);
    	return sum;
    }
    
    public boolean isValid() {
    	for (int i = 0; i < NUMBER_OF_ROWS; i++) {
    		for (int j = 0; j < NUMBER_OF_COLS; j++) {
    			used[i][j] = !(field[i][j] == -1);
    		}
    	}
    	
    	for (int i = 0; i < NUMBER_OF_ROWS; i++) {
    		for (int j = 0; j < NUMBER_OF_COLS; j++) {
    			if (!used[i][j]) {
    				int sum = dfs(i, j);
    				if (sum % 5 != 0)
    					return false;
    			}
    		}
    	}
    	return true;
    }
    
    public boolean backtracking(int x, int y) {   
    	//System.out.println(x + "  " + y);
        
    	if (!isValid()) {
        	return false;
        }
    	
    	/*ui.setState(field);
        try {
		    Thread.sleep(10);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}*/
    	
        boolean ok = true;
    	for (int i = 0; i < INPUT.length; i++)
    		if (mask[i] == 1)
    			ok = false;
    	if (ok)
    		return true;
    	
    	if (field[y][x] == -1)
    	{
        	for (int i = 0; i < INPUT.length; i++) {
	            if (mask[i] == 0) {
	                continue;
	            }
	           
	            int pentID = characterToID(INPUT[i]);
	            
	            for (int j = 0; j < 4; j++) {
	                database.rotate(pentID);
	                boolean result = tryToPush(pentID, x, y);
	                mask[i] = 0;
	                if (result) {
	                    boolean successfully = backtracking(x, y);
	                    if (successfully)
	                        return true;
	                }
	                goBack(pentID);
	                mask[i] = 1;
	            }
	            
	            database.reflect(pentID);
	            for (int j = 0; j < 4; j++) {
	                database.rotate(pentID);
	                boolean result = tryToPush(pentID, x, y);
	                mask[i] = 0;
	                if (result) {
	                    boolean successfully = backtracking(x, y);
	                    if (successfully)
	                        return true;
	                }
	                goBack(pentID);
	                mask[i] = 1;
	            }
	            database.reflect(pentID);
	        }
    	}
        x++;
        if (x == NUMBER_OF_COLS) {
        	x = 0;
        	y++;
        }
        
        if (y == NUMBER_OF_ROWS) {
        	return false;
        }
        
        boolean result = backtracking(x, y);
        return result;
    }
    
    public static void main(String args[]) {
    	Search obj = new Search();
    }
}