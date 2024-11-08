import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 20437번 문자열 게임 2
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());

			if (K == 1) {
				System.out.println("1 1");
				continue;
			}
			
			int[] alphabet = new int[26];
			
			for (int i = 0; i < W.length(); i++) {
				alphabet[W.charAt(i) - 97]++;
			}
			
			int min = Integer.MAX_VALUE;
			int max = -1;
			
			for (int i = 0; i < W.length(); i++) {
				if (alphabet[W.charAt(i) - 97] < K) continue;
				
				int count = 1;
				
				for (int j = i + 1; j < W.length(); j++) {
					if (W.charAt(i) == W.charAt(j)) count++;
					
					if (count == K) {
						min = Math.min(min, j - i + 1);
						max = Math.max(max, j - i + 1);
						break;
					}
				}
			}
			
			if (max == -1 || min == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(min + " " + max);
		}	
	}
}
