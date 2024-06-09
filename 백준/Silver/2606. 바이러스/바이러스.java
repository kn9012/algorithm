import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int arr[][], N, count = 0;
	static boolean isSelected[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수 (1 <= N <= 100)
		int M = Integer.parseInt(br.readLine()); // 연결되어 있는 컴퓨터 쌍의 수
		
		arr = new int[N + 1][N + 1];
		isSelected = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		bfs(1);
		System.out.println(count);
	}
	
	static void bfs(int M) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(M);
		isSelected[M] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (arr[cur][i] == 1 && !isSelected[i]) {
					q.add(i);
					isSelected[i] = true;
					count++;
				}
			}
		}
	}
}
