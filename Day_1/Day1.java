import java.util.Scanner;
import java.io.*;

public class Day1{
	static int value_first = 50;
	static int value_second = 50;
	static int counter_first = 0;
	static int counter_second = 0;

	private static int operateFirst(String line, int nb){
		int curr = Integer.parseInt(line.substring(1));
		
		if (line.charAt(0) == 'L'){
				nb -= curr;
				if (nb < 0){nb = 100 + nb;}
		}
		else if (line.charAt(0) == 'R'){
				nb += curr % 100;
		}
		if ((nb % 100) == 0)
			counter_first++;
		return nb % 100;
	}

	private static int operateSecond(String line, int nb){
        char dir = line.charAt(0);
        int move = Integer.parseInt(line.substring(1));

        for (int i = 0; i < move; i++) {
            if (dir == 'L') {
                nb--;
                if (nb < 0) 
					nb = 99;
            } else if (dir == 'R') {
                nb++;
                nb %= 100;
            }
            if (nb == 0) 
				counter_second++;
        }
        return nb;
    }

	private static void getInputAndOperate(String file){
		Scanner scan = null;
		String curr_str = "";

		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
		while (scan.hasNext()) {
			curr_str = scan.next();
			value_first = operateFirst(curr_str, value_first);
			value_second = operateSecond(curr_str, value_second);
		}
		scan.close();
		System.out.println(counter_first);
		System.out.println(counter_second);
		return;
	}

	public static void main(String[] argv){
		getInputAndOperate(argv[0]);
		return;
	}
}
