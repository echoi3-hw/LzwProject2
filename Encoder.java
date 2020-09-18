import java.util.*;
import java.io.*;

public class Encoder {
	ArrayList<String> arr = new ArrayList<String>();
	ArrayList<Character> file = new ArrayList<Character>();

	//dont talk about it 
	public Encoder(){
		for (int i = 0; i < 128; i++) {
			arr.add(""+(char)i);
		}
	}

	public void encodeText(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		char character;
		while (br.ready()){
			character = (char)(br.read());
			file.add(character);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter (new File ("file.lzw")));
		//Declare temp variables
		String encoded = "";
		String temp = "";

		//Loop through each character in the input file
		for(int i = 0; i < file.size(); i++){
			temp = temp + file.get(i); //Temp adds latest string 
			//Triggers when we reach a string that hasn"t been added to the key yet
			if (!arr.contains(temp)){
				//temp is always contained in our key if we take out the last letter, so we take out the last letter and add the key to the file
				writer.write("" + arr.indexOf(temp.substring(0,temp.length()-1)) + " ");
				arr.add(temp); //Add the new sequence to the key
				temp = "" + file.get(i); //Reset temp to the most recent character
			} 
		} if(!temp.equals("")) { //Handle the final temp 
			writer.write("" + arr.indexOf(temp));
		}
		writer.close();
		br.close();
	}
}