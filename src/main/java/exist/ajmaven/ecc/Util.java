package exist.ajmaven.ecc;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

public class Util {

	private static Scanner scanWithDel = new Scanner(System.in).useDelimiter("\n");
	private static Scanner input = new Scanner(System.in);
	private static BufferedWriter writer;
	private static File filePath;

	public static int scanInt(String str) {
		while (!scanWithDel.hasNextInt()) {
		  	System.out.println("Error! Invalid input. Please try again.");
			if (str == "action") {
				System.out.print("\n1. Print\n2. Edit\n3. Search\n4. Reset\n5. Exit\nSelect action:");
				System.out.print("Select action: ");
			} else if (str == "row" || str == "column") {
				System.out.print("Input " + str + ":");
			}
			scanWithDel.next();
		}
		return scanWithDel.nextInt();
	}

	public static void scanNxtLine() {
		scanWithDel.nextLine();
	}

	public static String scanStr() { 
		return input.nextLine();
	}

	public static  List<List<StringPair>> fileToMatrix() {
		Scanner fileInput = new Scanner(System.in).useDelimiter("\n");
        boolean redo = true;
        List<List<StringPair>> row = new ArrayList<List<StringPair>>();
        StringPair sp;
        while (redo) {
            try {
            	System.out.print("Enter file name: ");
        		File file = new File(fileInput.nextLine());
            	fileInput = new Scanner(file);
            	filePath = file;
                while (fileInput.hasNextLine()) {
                    ArrayList<StringPair> column = new ArrayList<StringPair>();
                    String line = fileInput.nextLine();
                    String[] tokens = line.split(Pattern.quote(" █ "));
                    for (String token : tokens) {
                        if (token.isEmpty()) {
                        	continue;
                        }
                        String[] pairTokens = token.split(",",2);
                        if ((pairTokens.length & 1) != 0 ) {
                        	row.clear();
                        	column.clear();
                        	Arrays.fill(tokens, null);
                        	System.out.println("Invalid input.");
                        	System.out.print("Enter file name: ");
                        	fileInput = new Scanner(System.in).useDelimiter("\n");
                        	file = new File(fileInput.nextLine());
            				fileInput = new Scanner(file);
            				filePath = file;
                        	break;
                        }
                        for (int i = 0; i < pairTokens.length; i++) {
                            sp = new StringPair("","");
                            sp.setKey(pairTokens[i]);
                            sp.setValue(pairTokens[++i]);
                            column.add(sp);
                        }
                    }
                    row.add(column);
                }
                redo = false;
            } 
            catch (FileNotFoundException ex) {
                System.out.println("Error! File not found.");
            }
        }
        return row;
	}

	public static String mergeString(List<List<StringPair>> matrix, int i, int j) {
		return matrix.get(i).get(j).merged();
	}

	public static void writeToFile(List<List<StringPair>> matrix) {
		String key, value;
		try {
		  	writer = new BufferedWriter(new FileWriter(filePath));
			for (int i = 0; i < matrix.size(); i++) {
				for (int j = 0;j < matrix.get(i).size(); j++) {
					key = matrix.get(i).get(j).key();
					value = matrix.get(i).get(j).value();
					writer.write(key + "," + value);
					if (j < (matrix.get(i).size()-1)) {
						writer.write(" █ ");
					}
				}
				writer.newLine();
			}
			writer.close();
		} catch(IOException e) {
		  e.printStackTrace();
		}
	}
}