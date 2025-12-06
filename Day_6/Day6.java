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
		String[] curr = null;
		int[][] numbers = null;
		String[] operations = null;

		while (scan.hasNextLine()) {
			curr = scan.nextLine().trim().split("\\s+");

			if (operations == null && numbers == null){
				operations = new String[curr.length];
				numbers = new int[4][curr.length];
			}

			if (isNumeric(curr[i])){
				for (int x = 0; x < curr.length; x++){
					numbers[i][x] = Integer.parseInt(curr[x]);
				}
				i++;
			}
			else
				operations = curr;

		}
		doOperationFirst(numbers, operations);
		scan.close();

		System.out.println("Final result: " + counter_first);
	}

	public static void main(String[] argv){
		getInputAndOperate(argv[0]);
		return;
	}
}
