import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static int[] scv;
	public static int min = Integer.MAX_VALUE;
	public static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		scv = new int[3];
		visited = new boolean[61][61][61];
		
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(scv[0], scv[1], scv[2], 0);
		
		System.out.println(min);
	}
	
	public static void dfs(int s1, int s2, int s3, int count) {
		s1 = Math.max(0, s1);
		s2 = Math.max(0, s2);
		s3 = Math.max(0, s3);
		
		if (s1 == 0 && s2 == 0 && s3 == 0) {
			min = Math.min(min, count);
			return;
		}
		
		Integer[] sort = {s1, s2, s3};
		Arrays.sort(sort, Collections.reverseOrder());
		
		s1 = sort[0];
		s2 = sort[1];
		s3 = sort[2];
		
		if (visited[s1][s2][s3]) return;
		else visited[s1][s2][s3] = true;
		
		if (count > min) return;
		
		dfs(s1 - 9, s2 - 3, s3 - 1, count + 1);
		dfs(s1 - 9, s2 - 1, s3 - 3, count + 1);
		dfs(s1 - 3, s2 - 9, s3 - 1, count + 1);
		dfs(s1 - 3, s2 - 1, s3 - 9, count + 1);
		dfs(s1 - 1, s2 - 9, s3 - 3, count + 1);
		dfs(s1 - 1, s2 - 3, s3 - 9, count + 1);
		
		return;
	}
}
