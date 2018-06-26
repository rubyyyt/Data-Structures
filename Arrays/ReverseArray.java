import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

/**
/**
 *  Title: ReverseArray
 *  Name: Rubaiat Tazim
 *  Date: 04/09/2018
 *  Login: cs12sip
 *  PID: A12739293
 * */

public class ReverseArray {

/* Main method that calls in the read method */
public static void main(String[] args) throws FileNotFoundException {

        if (args.length == 0) {
		System.err.println("Usage: ReverseArray <filename>");
		System.exit(0);
	}
	else {
	 read(args[0]);
	}
}

/*   Reads file's lines in one-by-one, stores it in an array of strings, 
 *   then prints out the lines in reverse order. If the number of strings
 *   exceed 100, another array of strings is created that has a length of
 *   an increased 100, and the values are copied from the old array to
 *   the new array. */

public static void read(String file) {
    try {
	Scanner scanner = new Scanner(new FileReader(file)); //reads in file
	String[] array = new String[100];
	int i = 0;
	while ((scanner.hasNextLine())) {
		array[i] = scanner.nextLine();
	 	i++;
		if ((i == array.length-1)  && (scanner.hasNext())) {
			//checks if array is enough to fit all strings in file,
			//if not big enough, makes new larger array
			String[] largerArray = new String[array.length + 100];
			for (int j = 0; j < array.length; j++) {
				largerArray[j] = array[j];
			}
			array = largerArray;
		}
	
	}	
	scanner.close();
	
	//prints in reverse line order
	for (int x = array.length-1; x >= 0; x--) {
		if (array[x] != null) { 
			System.out.println(array[x]);
		}
	}
		

     } catch (FileNotFoundException e) {
                System.err.println("File not found");
    }
  }
 
}


