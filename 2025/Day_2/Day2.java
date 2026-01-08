import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.*;

public class Day2{
    static long counter_first = 0;
    static long counter_second = 0;

    public static boolean containsTwiceRepeatedSubstring(String input){
            Pattern pattern = Pattern.compile("^([0-9]+)\\1$");
            Matcher matcher = pattern.matcher(input);
            
            return matcher.find();
    }

    public static boolean containsAnyRepeatedSubstring(String input){
            Pattern pattern = Pattern.compile("^([0-9]+)\\1+$");
            Matcher matcher = pattern.matcher(input);
            
            return matcher.find();
    }

	private static void findInvalidID(String line){
        String[] range = line.split("-");
        long end = Long.parseLong(range[1]);
        long start = Long.parseLong(range[0]);

        for (long i = start; i <= end; i++){
            if (containsTwiceRepeatedSubstring(i + "")){
                counter_first += i;
            }
            if (containsAnyRepeatedSubstring(i + "")){
                counter_second += i;
            }
        }
        return;
	}

	private static void getInputAndOperate(String file){
        String[] line;
		Scanner scan = null;
		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
        line = scan.next().split(",");
        for (String word: line){
            findInvalidID(word);
        }
		scan.close();
        System.out.println(counter_first);
        System.out.println(counter_second);
		return ;
	}

	public static void main(String[] argv){
		getInputAndOperate(argv[0]);
		return;
	}
}