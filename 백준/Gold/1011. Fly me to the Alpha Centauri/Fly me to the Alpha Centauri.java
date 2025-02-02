import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 Fly me to the Alpha Centauri
 * - dp
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int distance = y - x;
			
			int squareRoot = (int) Math.sqrt(distance);
			if (distance > squareRoot * squareRoot + squareRoot) {
				answer = squareRoot * 2 + 1;
			} else {
				if (distance == squareRoot * squareRoot) {
					answer = squareRoot * 2 - 1;
				} else {
					answer = squareRoot * 2;
				}
			}
			
			System.out.println(answer);
		}
	}
}
