import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, arr[][], count;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N + 1][N + 1];
			visited = new boolean[N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				count = st.countTokens();
			
				if (count == 2) {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					arr[a][b] = 1;
					arr[b][a] = 1;	
				}
			}
			

			count = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					bfs(i);
				}
			}
			
			sb.append("#" + tc + " " + count + "\n");
		}
		System.out.println(sb);
		
	}

	static void bfs(int i) {
		Queue<Integer> queue = new ArrayDeque<>();
		visited[i] = true;
		queue.add(i);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && arr[cur][j] == 1) {
					queue.add(j);
					visited[j] = true;
				}
			}
		}
		
		count++;
	}
}