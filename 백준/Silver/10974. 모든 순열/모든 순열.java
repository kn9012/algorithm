import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 순열 연습
 * @author kimyuna
 *
 */

public class Main {
	static int n, arr[];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		visited = new boolean[n];
		
		perm(0);
	}
	
	static void perm(int count) {
		if (count == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(arr[i] + " ");
			} System.out.println();
		}
		
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			arr[count] = i + 1;
			perm(count + 1);
			visited[i] = false;
		}
		
	}
}