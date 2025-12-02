

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

	private static String getInputAndOperate(String file){
		Scanner scan = null;
		String lines = "";
		try {
    		scan = new Scanner(new File(file));
		}catch (FileNotFoundException e) {
    		e.printStackTrace();
		}
		while (scan.hasNext()) {
			lines += scan.next();
		}
		scan.close();
		return lines;
	}

	public static void main(String[] argv){
		return;
	}
}