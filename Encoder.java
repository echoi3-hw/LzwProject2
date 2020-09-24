import java.util.*;
import java.io.*;

public class Encoder {
	/*
	 * Edit 1:
	 * made arraylist into HashMap
	 */
	HashMap <String, Integer> map = new HashMap<String,Integer>();
	int mapSize = 128;
	
	public Encoder(){
		/*
		 * Edit 2
		 * input all chars in ascii table into dictionary - only had a-z before
		 */
		//initializes the array with 0-127 from ascii table
		for (int i = 0; i < 128; i++) {
			map.put("" + (char)i, i);
		}
	}

	public void encodeText(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		PrintWriter pw = new PrintWriter (new FileWriter (new File (fileName +".lzw")));
		String code = "";
		/*
		 * Edit 3
		 * changed from for loop to while loop
		 * made the loop actually work
		 */
		while (br.ready()) {
			String c = "" + (char)(br.read()); //reads in new char as variable 'c'
			String newcode = "" + code + c; //appends c onto code
			
			if (map.containsKey(newcode)) {
				code = newcode;
			}
					
			else {
				pw.print(map.get(code) + " ");
				map.put(newcode, mapSize++);
				code = c;
			}
		}
		pw.print(map.get(code) + " ");
		br.close();
		pw.close();
	}
}