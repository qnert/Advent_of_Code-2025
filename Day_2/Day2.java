import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.*;

public class Day2{
    public static boolean containsRepeatedSubstring(String input){
            Pattern pattern = Pattern.compile("^(\\d+)\\1$");
            Matcher matcher = pattern.matcher(input);
            
            return matcher.find();
    }

	private static long findInvalidID(String line){
        long counter = 0;
        String[] range = line.split("-");
        long end = Long.parseLong(range[1]);
        long start = Long.parseLong(range[0]);

        for (long i = start; i <= end; i++){
            if (containsRepeatedSubstring(i + "")){
                counter += i;
            }
        }
        return counter;
	}

	private static long getInputAndOperate(String file){
        String[] line;
        long counter = 0;
		Scanner scan = null;
		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
        line = scan.next().split(",");
        for (String word: line){
            counter += findInvalidID(word);
        }
		scan.close();
		return counter;
	}

	public static void main(String[] argv){
		System.out.println(getInputAndOperate(argv[0]));
		return;
	}
}