import java.util.*;
import java.io.*;

public class Day4{
    static int counter_first = 0;
    static int counter_second = 0;

    private static char[][] copyMatrix(char[][] input){
        char[][] copy = new char[input.length][];

        for (int i = 0; i < input.length; i++) {
            copy[i] = new char[input[i].length];
            for (int j = 0; j < input[i].length; j++) {
                copy[i][j] = input[i][j];
            }
        }
        return copy;
    }


    private static int checkSurrounding(char[][] input, int index_i, int index_j){
        int curr_count = 0;

        for (int i = index_i - 1; i <= index_i + 1; i++) {
            for (int j = index_j - 1; j <= index_j + 1; j++) {

                if (i < 0 || i >= input.length || j < 0 || j >= input[i].length)
                    continue;

                if (i == index_i && j == index_j)
                    continue;
            
                if (input[i][j] == '@')
                    curr_count++;

            }
        }
        return (curr_count < 4 ? 1 : 0);
    }

    private static void doOperationFirst(char[][] input){
        int check = 0;
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[i].length; j++){
                if (input[i][j] == '@'){
                    counter_first += checkSurrounding(input, i, j);
                }
            }
        }
    }

    private static char[][] doOperationSecond(char[][] input) {
        int tmp;
        int check;
        char [][] copy = copyMatrix(input);

        tmp = counter_second;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '@') {
                    check = checkSurrounding(input, i, j);
                    if (check == 1)
                        copy[i][j] = '.';
                    counter_second += check;
                }
            }
        }
        if (tmp == counter_second)
            return null;
        return copy;
    }


    private static void getInputAndOperate(String file) {
        int ch;
        char[][] copy;
        int row = 0, col = 0;
        char[][] input = new char[137][137];

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((ch = reader.read()) != -1 && row < 137) {

                if (ch == '\n' || ch == '\r') 
                    continue;

                input[row][col] = (char) ch;
                col++;

                if (col == 137) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        doOperationFirst(input);
        copy = doOperationSecond(input);
        while (copy != null)
            copy = doOperationSecond(copy);
        System.out.println(counter_first);
        System.out.println(counter_second);
    }

	public static void main(String[] argv){
		getInputAndOperate(argv[0]);
		return;
	}
}