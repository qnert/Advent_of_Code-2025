import java.util.*;
import java.io.*;

public class Day5{
    static int counter_first = 0;
    static long counter_second = 0;

	private static boolean checkFresh(Long[][] ranges, Long ingredient){
		for(int i = 0; i < (int)ranges.length; i++){
			if (ingredient <= ranges[i][1] && ingredient >= ranges[i][0])
				return true;
		}
		return false;
	}

    private static void doOperationFirst(Long[][] ranges, Long[] ingredients){
		for(int i = 0; i < (int)ingredients.length; i++){
			if (checkFresh(ranges, ingredients[i]))
				counter_first++;
		}
    }

private static long doOperationSecond(Long[][] ranges) {
    List<long[]> list = new ArrayList<>();
    for (Long[] r : ranges) {
        list.add(new long[]{r[0], r[1]});
    }

    list.sort(Comparator.comparingLong(a -> a[0]));

    long total = 0;
    long currentStart = list.get(0)[0];
    long currentEnd = list.get(0)[1];

    for (int i = 1; i < list.size(); i++) {
        long start = list.get(i)[0];
        long end = list.get(i)[1];

        if (start > currentEnd + 1) {
            total += (currentEnd - currentStart + 1);
            currentStart = start;
            currentEnd = end;
        } else {
            currentEnd = Math.max(currentEnd, end);
        }
    }
    total += (currentEnd - currentStart + 1);

    return total;
}


	private static void getInputAndOperate(String file){
		int i = 0;
		int j = 0;
		Scanner scan = null;
		String curr_str = "";
		Long[] ingredients = new Long[1000];
        Long[][] matrix = new Long[192][2];
		String[] curr_range = new String[2];

		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
		while (scan.hasNext()) {
			curr_str = scan.next();
			if (curr_str.contains("-")){
				curr_range = curr_str.split("-");
				matrix[i][0] = Long.parseLong(curr_range[0]);
				matrix[i][1] = Long.parseLong(curr_range[1]);
				i++;
			}
			else if (curr_str.compareTo("\n") != 0){
				ingredients[j] = Long.parseLong(curr_str);
				j++;
			}
		}
		doOperationFirst(matrix, ingredients);
		counter_second = doOperationSecond(matrix);
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
