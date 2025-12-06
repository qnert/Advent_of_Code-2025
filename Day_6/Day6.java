import java.util.*;
import java.io.*;

public class Day6{
	static long counter_first = 0;
	static long counter_second = 0;

	private static boolean isNumeric(String s) {
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

private static void doOperationSecond(String[] input) {
	int i = 0;
	int j = 0;
	String block;
	int start = 0;
	String reversed;
	int[][] numbers = new int[input.length][input[0].length()];

	for (String line : input) {
		int index = line.length();

		while (index > 0) {
			start = Math.max(0, index - 3);
			block = line.substring(start, index);
			reversed = new StringBuilder(block).reverse().toString();

			index = start;
			index--;
		}
		i++;
		j = 0;
	}
}



	private static void parseAndOperate(Scanner scan){
		int i = 0;
		String pre_process = "";
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

			if (isNumeric(curr_input[0])){
				pre_process = pre_process + curr_line + ".";
				for (int x = 0; x < curr_input.length; x++){
					numbers[i][x] = Integer.parseInt(curr_input[x]);
				}
				i++;
			}
			else
				operations = curr_input;
		}
		doOperationFirst(numbers, operations);
		doOperationSecond(pre_process.split("\\."));
	}

	private static void getInputAndOperate(String file) {
		Scanner scan = null;

		try {
			scan = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		parseAndOperate(scan);
		scan.close();

		System.out.println(counter_first);
		// System.out.println(counter_second);
	}

	public static void main(String[] argv){
		getInputAndOperate(argv[0]);
		return;
	}
}
