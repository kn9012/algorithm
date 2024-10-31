import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, min = Integer.MAX_VALUE;
	static boolean visit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visit = new boolean[100001];
		
		bfs();
		
		System.out.println(min);
	}
	
	public static void bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {N, 0});
		visit[N] = true;
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int loc = cur[0];
			int count = cur[1];
			
			if (loc == K) min = Math.min(min, count);
			
			if (loc * 2 <= 100000 && !visit[loc * 2]) {
				queue.add(new int[] {loc * 2, count});
				visit[loc * 2] = true;
			}
			if (loc - 1 >= 0 && !visit[loc - 1]) {
				queue.add(new int [] {loc - 1, count + 1});
				visit[loc - 1] = true;
			}
			if (loc + 1 <= 100000 && !visit[loc + 1]) {
				queue.add(new int [] {loc + 1, count + 1});
				visit[loc + 1] = true;
			}
			
		}
	}
}
