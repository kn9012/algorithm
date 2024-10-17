import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] balls = br.readLine().split("");
		int rCount = 0;
		int bCount = 0;
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			if (balls[i].equals("B")) {
				bCount++;
			} else {
				rCount++;
			}
		}
		
		int firstCount = 0;
		for (int i = 0; i < N; i++) {
			if (balls[i].equals("B")) {
				firstCount++;
			} else break;
		}
		
		min = Math.min(min, bCount - firstCount);
		
		firstCount = 0;
		for (int i = 0; i < N; i++) {
			if (balls[i].equals("R")) {
				firstCount++;
			} else break;
		}
		
		min = Math.min(min, rCount - firstCount);
		
		firstCount = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (balls[i].equals("B")) {
				firstCount++;
			} else break;
		}
		
		min = Math.min(min, bCount - firstCount);
		
		firstCount = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (balls[i].equals("R")) {
				firstCount++;
			} else break;
		}
		
		min = Math.min(min, rCount - firstCount);
		
		System.out.println(min);
	}
}
