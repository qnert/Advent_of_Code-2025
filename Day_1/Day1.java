import java.util.Scanner;
import java.io.*;

public class Day1{
	private static int operate(String line, int nb){
		int curr = Integer.parseInt(line.substring(1));
		if (line.charAt(0) == 'L'){
				nb -= curr;
				if (nb < 0){nb = 100 + nb;}
		}
		else if (line.charAt(0) == 'R'){
				nb += curr % 100;
		}
		return nb % 100;
	}

	private static int getInputAndOperate(String file){
		int counter = 0;
		int value = 50;
		Scanner scan = null;
		String lines = "";
		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
		while (scan.hasNext()) {
			value = operate(scan.next(), value);
			if (value == 0){
					counter++;
			}
		}
		scan.close();
		return counter;
	}

	public static void main(String[] argv){
		System.out.println(getInputAndOperate(argv[0]));
		return;
	}
}