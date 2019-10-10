import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class TestGenerators {
	
	private int width;
	private int height;
	private Scanner in;
	private File file;
	private Random rand;
	private int[] array;
	private ArrayList<String> data;
	
	/**
	 * Initialises random widths and heights.
	 * @throws FileNotFoundException 
	 */
	public TestGenerators(String file_name) throws FileNotFoundException {
		this.file = new File(file_name);
		this.in = new Scanner(this.file);
		this.rand = new Random();
		this.height = this.rand.nextInt();
		this.width = this.rand.nextInt();
		this.data = new	ArrayList<String>();
		fillData();
		
		
	}
	private void fillData() {
		while(this.in.hasNextLine()) {
			data.add(this.in.next());
		}
	}
	
	public int[] getTest(int line) {
		String lineToConvert = data.get(line);
		this.array = new int[lineToConvert.length()];
		for(int i = 0; i < lineToConvert.length(); i++) {
			this.array[i] = Character.getNumericValue(lineToConvert.charAt(i));
		}
		return this.array;
	}
	
	public int[] getRandomTest() {
		this.rand = new Random();
		int randomLine = this.rand.nextInt(data.size());
		String lineToConvert = data.get(randomLine);
		this.array = new int[lineToConvert.length()];
		for(int i = 0; i < lineToConvert.length(); i++) {
			this.array[i] = Character.getNumericValue(lineToConvert.charAt(i));
		}
		return this.array;
		
	}
	
	public int generateHeight() {
		this.height = this.rand.nextInt();
		return this.height;
	}
	
	public int generateWidth() {
		this.width = this.rand.nextInt();
		return this.width;
	}
	
	
}
