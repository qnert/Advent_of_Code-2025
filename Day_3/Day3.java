import java.util.*;
import java.io.*;

public class Day3{
    static int counter_first = 0;
    static long counter_second = 0;

    private static void doOperationFirst(String input){
        int i;
        String result = "";
        char maxChar_second;
        int highest_index = 0;
        char maxChar_first = input.charAt(0);

        for (i = 1; i < input.length() - 1; i++) {
            if (input.charAt(i) > maxChar_first) {
                maxChar_first = input.charAt(i);
                highest_index = i;
            }
        }
        maxChar_second = input.charAt(highest_index + 1);
        for (i = highest_index + 1; i < input.length(); i++){
            if (input.charAt(i) > maxChar_second) {
                maxChar_second = input.charAt(i);
            }
        }
        counter_first += Integer.parseInt(String.valueOf(maxChar_first) + String.valueOf(maxChar_second));
        return;
    }

    private static void doOperationSecond(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = input.length();

        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);

            while (!stack.isEmpty() && stack.peekLast() < c && stack.size() - 1 + (n - i) >= 12) {
                stack.pollLast();
            }
            if (stack.size() < 12) {
                stack.addLast(c);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        counter_second += Long.parseLong(result.toString());
        return ;
    }

	private static void getInputAndOperate(String file){
		String lines = "";
		Scanner scan = null;
		String curr_str = "";

		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
		while (scan.hasNext()) {
			curr_str = scan.next();
            doOperationFirst(curr_str);
            doOperationSecond(curr_str);
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