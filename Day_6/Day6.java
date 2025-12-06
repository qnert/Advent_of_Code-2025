import java.util.*;
import java.io.*;

public class Day6{
    static long counter_first = 0;
    static long counter_second = 0;

	public static boolean isNumeric(String s) {
		if (s == null ||s.isEmpty())
			return false;
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static void doOperationFirst(int[][] nums, String[] operations) {
		long result = 0;

		for (int i = 0; i < operations.length; i++){
			switch (operations[i]){
				case "*":
					if (result == 0)
						result = 1;
					for (int j = 0; j < nums.length; j++)
						result *= nums[j][i];
					break;

				case "+":
					for (int j = 0; j < nums.length; j++)
						result += nums[j][i];
					break;
			}
			counter_first += result;
			result = 0;
		}
	}

	private static void getInputAndOperate(String file) {
		Scanner scan = null;

		try {
			scan = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int i = 0;
		int[][] numbers = null;
		String curr_line = null;
		String[] curr_input = null;
		String[] operations = null;

		while (scan.hasNextLine()) {
		curr_line = scan.nextLine();
		curr_input = curr_line.trim().split("\\s+");

		if (operations == null && numbers == null){
			operations = new String[curr_input.length];
			numbers = new int[4][curr_input.length];
		}

		if (isNumeric(curr_input[i])){
			for (int x = 0; x < curr_input.length; x++){
				numbers[i][x] = Integer.parseInt(curr_input[x]);
			}
			i++;
		}
		else
			operations = curr_input;

		}
		doOperationFirst(numbers, operations);
		// doOperationSecond(numbers, operations);
		scan.close();

		System.out.println("Final result: " + counter_first);
	}

	public static void main(String[] argv){
		getInputAndOperate(argv[0]);
		return;
	}
}
