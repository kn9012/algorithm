import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int arr[][], N, count;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[2][N + 1];
			visited = new boolean[N + 1];
			count = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[0][j] = j + 1;
				arr[1][j] = Integer.parseInt(st.nextToken());
			}
			
			for (int j = 1; j <= N; j++) {
				if (!visited[j]) {
					bfs(j);
				}
			}
			
			sb.append(count + "\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int num) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(num);
		visited[num] = true;
		
//		System.out.println("n " + (num + 1));
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				
				if (arr[1][i] == cur && !visited[i]) {
//					System.out.println(i + 1);
					queue.add(i);
					visited[i] = true;
				}
			}
		}
		
		count++;
	}
}