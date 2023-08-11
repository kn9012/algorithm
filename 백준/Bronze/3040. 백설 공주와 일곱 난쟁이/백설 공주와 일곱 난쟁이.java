import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] shortMan, real;
	static int sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		shortMan = new int[9];
		real = new int[7];
		
		for (int i = 0; i < 9; i++) {
			shortMan[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
	}
	
	static void comb(int count, int start, int sum) {
		if (count == 7) {
			if (sum == 100) {
				for (int i = 0; i < 7; i++) {
					System.out.println(real[i]);
				}
				return;
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			real[count] = shortMan[i];
			comb(count + 1, i + 1, sum + real[count]);
		}
	}
}
