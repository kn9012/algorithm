
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 15654번 N과 M (5)
 * N개의 자연수 중에서 M개를 고른 수열을 구하라.
 * 
 * [아이디어]
 * M은 N보다 작으므로 순열을 구현하면 된다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-27
 *
 */
public class Main {
	static int n, m;
	static int[] arr, select;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		select = new int[m];
		visited = new boolean[n];
		
		perm(0);
		
		System.out.println(sb);
	}
	
	static void perm(int count) {
		if (count == m) {
			//Arrays.sort(select);
			for (int i = 0; i < m; i++) {
				sb.append(select[i]).append(" ");
			} sb.append("\n");
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			select[count] = arr[i];
			perm(count + 1);
			visited[i] = false;
		}
	}
}
