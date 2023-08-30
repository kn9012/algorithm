import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
  * 
  * [문제] 백준 10971번 외판원순회2
  * [아이디어] 
  * 
  * @author 김유나
  * 2023-08-30
  *
  */
public class Main {
	static int arr[][], n, choiceCity[], cityNum[], min = Integer.MAX_VALUE;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		visited = new boolean[n];
		choiceCity = new int[n];
		cityNum = new int[n];
		
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			cityNum[i] = i;
		}
		
		perm(0);
		
		System.out.println(min);
	}
	
	static void perm(int count) {
		if (count == n) {
			int sum = 0;
			for (int i = 1; i < n; i++) {
				if (arr[choiceCity[i - 1]][choiceCity[i]] == 0) return;
				else sum += arr[choiceCity[i - 1]][choiceCity[i]];
			}
			
			if (arr[choiceCity[n - 1]][choiceCity[0]] == 0) return;
			else sum += arr[choiceCity[n - 1]][choiceCity[0]];
			min = Math.min(sum, min);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			choiceCity[count] = cityNum[i];
			visited[i] = true;
			perm(count + 1);
			visited[i] = false;			
		}
	}
}