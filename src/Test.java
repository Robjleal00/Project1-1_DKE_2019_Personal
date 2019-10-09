import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random; 

public class Test {
	
	public static void main(String[] args) { 
		
		int[][]array1 = generate();
		int[][]array2 = generate();
		System.out.println(isEqual(array1, array2));
		
		/**for(int i = 0; i< 10000; i++) {
			int[][] array = generate();
			for(int k = 0; k<array.length; k++) {
				for(int j = 0 ; j<array[0].length;j++) {
					System.out.print(array[k][j] + " ");
				}
				System.out.println();
			}
		}*/
		
		
		
		
		
		
		
	}
	
	public static boolean isEqual(int[][] data1, int[][] data2)
    {

        for (int i = 0; i < data1.length; i++) {
            for (int j = 0; j < data1[0].length; j++) {
                if (data1[i][j] != data2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
	
	public static int[][] generate() { 
		
		Random rand = new Random(); 
		int height  = rand.nextInt(11)+1; 
		int width = rand.nextInt(11)+1; 
		int[][] array = new int[5][6];
		//ArrayList<Integer> array = new ArrayList<Integer>();
		
		for(int i = 0; i< 5 ; i++) {
			for(int j = 0; j<6 ; j++) {//replace by width and height
				array[i][j] = rand.nextInt();
		
		}
	
	
	
	}
		return array;
	
	
	
	
	
	
	
	
	
	
	

}
}
