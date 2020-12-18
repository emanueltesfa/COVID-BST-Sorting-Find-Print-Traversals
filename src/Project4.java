
/**
* COP 3530: Project 4 – Binary Search Trees
* <p>
* Description of the class using as many lines as needed
* with <p> between paragraphs. Including descriptions of the
* input required and output generated.
*
* @author <your name>
* @version <the date you last modified the program>
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project4 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Name: Emanuel Tesfa \n"
				+ "Binary Search Trees\n");

		String name = null, fileName = null;
		Double cfr = 0.0, cases = 0.0, deaths = 0.0;

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter in name of file, followed by enter");

		fileName = input.next();
		Scanner read = new Scanner(new File(fileName));
		read.useDelimiter(",|\\n");

		BinarySearchTree t = new BinarySearchTree();

		while (read.hasNext()) {
			read.nextLine();
			name = read.next();
			read.next();
			read.next();
			Double.parseDouble(read.next());
			cases = read.nextDouble();
			deaths = Double.parseDouble(read.next());
			cfr = deaths / cases;
			t.insert(name, cfr);
		}
		
		t.printInorder();

		t.delete("Greece");
		t.delete("Mongolia");
		t.delete("Norway");

		t.printPreorder();

		t.find("Mongolia");
		t.find("Cyprus");
		t.find("United States");
		t.find("Norway");

		t.delete("Qatar");
		t.delete("Somalia");
		t.delete("Canada"); // which one
		t.delete("Yemen");
		t.delete("New Zealand");

		t.printPostorder();

		t.printBottom20();
		t.printTop20();

	}

}
