import java.util.Scanner;
import java.io.FileReader;
import java.util.LinkedList;
import java.io.FileNotFoundException;

/*
 *  Title: ReverseList
 *  Name: Rubaiat Tazim
 *  Date: 04/09/2018
 *  Login: cs12sip
 *  PID: A12739293
 */

public class ReverseList {

/* Main method that calls in the read method */
public static void main(String[] args) throws FileNotFoundException {

	if (args.length == 0) {
	   System.err.println("Usage: ReverseList <filename>");
 	   System.exit(0);
	}
	else {
		read(args[0]);
	}
}

/* Reads file's lines in one-by-one, stores it in a linked list, 
  then prints out the lines in reverse order */

public static void read(String file) {
    try {
	Scanner scanner = new Scanner(new FileReader(file)); //reads in file

	LinkedList<String> line = new LinkedList<String>(); //creates a list of strings
	while (scanner.hasNext()) { //scans file's lines one-by-one
	line.add(scanner.nextLine());
	}

	scanner.close();
	for (int i = line.size() - 1; i>= 0; i--) { //prints lines in reverse order
		System.out.println(line.get(i)); 
	}

     } catch (FileNotFoundException e) { //handles file not found exception
                System.err.println("File not found");
    }  
  }
}
