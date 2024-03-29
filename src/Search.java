import java.util.Random;

public class Search
{
    public static final int horizontalGridSize = 5;                      // the number of rows
    public static final int verticalGridSize = 6;                        // the number of columns
    
    public static final char[] input = { 'W', 'Y', 'I', 'T', 'Z', 'L'};    // sample input
    PentominoBuilder database = new PentominoBuilder();
    
    // Static UI class to display the board
    public static UI ui = new UI(horizontalGridSize, verticalGridSize, 50); 

    // Helper function which starts the brute force algorithm
    public static void search()
    {
        // Initialize an empty board
        int[][] field = new int[horizontalGridSize][verticalGridSize];

        for(int i = 0; i < field.length; i++)
        {
            for(int j = 0; j < field[i].length; j++)
            {
                // -1 in the state matrix corresponds to empty square
                // Any positive number identifies the ID of the pentomino
            	field[i][j] = -1;
            }
        }
        
        boolean[] mask = new boolean[input.length];
        for (int i = 0; i < input.length; i++)
        	mask[i] = false;
        
        //Start brute force
        boolean result = backtracking(field, mask);
        //bruteForce(field);
    }
    
    private static int characterToID(char character) {
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

    private static boolean try_to_push(int[][] field, int pentID, int rotation) {
    	for (int i = 0; i < )
    }
    
    private static void go_back(int[][] field, int pentID) {
    	for (int i = 0; i < field.length; i++) {
    		for (int j = 0; j < field[0].length; j++)
    			if (field[i][j] == pentID)
    				field[i][j] = -1;
    	}
    }
    
    private static boolean backtracking(int[][] field, boolean[] mask) {   // mask corresponds to figures usage,
    	                                                                      // if the ith figure places, than ith bit is 1, otherwise 0 
    	boolean ok = true;
    	for (int i = 0; i < horizontalGridSize; i++) {
    		for (int j = 0; j < verticalGridSize; i++) {
    			if (field[i][j] == -1)
    				ok = false;
    		}
    	}
    	if (ok)
    		return true;
    
    	for (int i = 0; i < mask.length; i++) {
    		if (mask[i])
    			continue;
    		
    		int pentID = characterToID(input[i]);
    		for (int j = 0; j < 4; j++) {
    			database.rotate();
    			boolean result = try_to_push(field, pentID);
    			if (result)
    			{
    				mask[i] = true;
    				boolean successfully = backtracking(field, mask);
    				go_back(field, pentID);
    				if (successfully)
    					return true;
    			}
    		}
    	}
    }
    
    private static void bruteForce(int[][] field) {
    	Random random = new Random();
    	boolean solutionFound = false;
    	
    	while (!solutionFound) {
    		solutionFound = true;
    		
    		//Empty board again to find a solution
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					field[i][j] = -1;
				}	
			}
    		
    		//Put all pentominoes with random rotation/inversion on a random position on the board
    		for (int i = 0; i < input.length; i++) {
    			
    			//Choose a pentomino and randomly rotate/inverse it
    			int pentID = characterToID(input[i]);
    			int mutation = random.nextInt(PentominoDatabase.data[pentID].length);
    			int[][] pieceToPlace = PentominoDatabase.data[pentID][mutation];
    		
    			//Randomly generate a position to put the pentomino on the board
    			int x;
    			int y;
    			if (horizontalGridSize < pieceToPlace.length) {
    				//this particular rotation of the piece is too long for the field
    				x=-1;
    			} else if (horizontalGridSize == pieceToPlace.length) {
    				//this particular rotation of the piece fits perfectly into the width of the field
    				x = 0;
    			} else {
    				//there are multiple possibilities where to place the piece without leaving the field
    				x = random.nextInt(horizontalGridSize-pieceToPlace.length+1);
    			}

    			if (verticalGridSize < pieceToPlace[0].length) {
    				//this particular rotation of the piece is too high for the field
    				y=-1;
    			} else if (verticalGridSize == pieceToPlace[0].length) {
    				//this particular rotation of the piece fits perfectly into the height of the field
    				y = 0;
    			} else {
    				//there are multiple possibilities where to place the piece without leaving the field
    				y = random.nextInt(verticalGridSize-pieceToPlace[0].length+1);
    			}
    		
    			//If there is a possibility to place the piece on the field, do it
    			if (x >= 0 && y >= 0) {
	    			addPiece(field, pieceToPlace, pentID, x, y);
	    		} 
    		}
    		//Check whether complete field is filled
    		//
    		//
    		// TODO: To be implemented
    		//
    		//
    		

    		
    		if (solutionFound) {
    			//display the field
    			ui.setState(field); 
    			System.out.println("Solution found");
    			break;
    		}
    	}
    }

    

    // Adds a pentomino to the position on the field (overriding current board at that position)
    public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y)
    {
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1)
                {
                    // Add the ID of the pentomino to the board if the pentomino occupies this square
                    field[x + i][y + j] = pieceID;
                }
            }
        }
    }


    // Main function. Needs to be executed to start the brute force algorithm
    public static void main(String[] args)
    {
        search();
    }
}
