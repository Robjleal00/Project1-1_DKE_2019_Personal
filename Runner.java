import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("***********************");
		System.out.println("****CONSOLE TEST...****");
		System.out.println("***********************");
		
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		
		System.out.println("How many test samples?");
		int nbr = in.nextInt();
		in.close();
		System.out.println("Runing...");
		//Runs brute force for like the sample size
		Search search = new Search();
		search.search();
		
	}
}
