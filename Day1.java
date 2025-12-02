

public class Day1{
	private static int operate(String line, int nb){
		switch(line[0]){
			case 'L':
				nb -= Integer.parseInt(line.substring(1, 3));
				if (nb < 0){
					nb = 100 + nb;
				}
			case 'R':
				nb += Integer.parseInt(line.substring(1, 3)) % 100;
		}
		return nb;
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
		System.out.println(getInputAndOperate(argv[1]));
		return 0;
	}
}