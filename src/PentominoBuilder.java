import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class PentominoBuilder {

    //All basic pentominoes that will be rotated and inverted
    static int[][][] basicDatabase = {
            {
            	// pentomino representation X
            		{0, 0, 0, 0, 0},
            		{0, 0, 1, 0, 0},
                    {0, 1, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            },
            {
            	// pentomino representation I
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0}
            },
            {
            	// pentomino representation Z
            		{0, 0, 0, 0, 0},
            		{0, 0, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            },
            {
            	// pentomino representation T
            		{0, 0, 0, 0, 0},	
            		{0, 1, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            },
            {
            	// pentomino representation U
            		{0, 0, 0, 0, 0},
            		{0, 0, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 1, 0},
                    {0, 0, 0, 0, 0}
            },
            {
            	// pentomino representation V
            		{0, 0, 0, 0, 0},
            		{0, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0},
                    {0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0}
            },
            {
            	// pentomino representation W
            		{0, 0, 0, 0, 0},
            		{0, 0, 0, 1, 0},
                    {0, 0, 1, 1, 0},
                    {0, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0}
            },
            {
            	// pentomino representation Y
            		{0, 0, 0, 0, 0},	
            		{0, 0, 1, 0, 0},
                    {0, 0, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0}
            },
            {
            	// pentomino representation L
            		{0, 0, 0, 0, 0},
            		{0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 1, 1, 0}
            },
            {
            		{0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 0},
                    {0, 0, 1, 1, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}

            	
            },
            {
            		{0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 0},
                    {0, 0, 1, 1, 1},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}

            	
            },
            {
            		{0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 0},
                    {0, 1, 1, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0}

            }
    };

    public void rotate(int PentID)
    {
    	int[][] rotated = new int[5][5];
    	
    	for (int i = 0; i < 5; i++)
    		for (int j = 0; j < 5; j++)
    			rotated[i][j] = basicDatabase[PentID][j][i];
    	basicDatabase[PentID] = rotated;
    }
}
