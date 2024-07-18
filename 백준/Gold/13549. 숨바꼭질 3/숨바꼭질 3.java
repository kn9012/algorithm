import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Time {
		int num, count;
		
		Time(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}
	
	static int N, K, answer;
	static boolean isVisited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[100001];
		
		bfs();
		
		System.out.println(answer);
	}
	
	public static void bfs() {
		Queue<Time> queue = new ArrayDeque<>();
		queue.add(new Time(N, 0));
		isVisited[N] = true;
		
		while (!queue.isEmpty()) {
			Time cur = queue.poll();
			if (cur.num == K) {
				answer = cur.count;
				break;
			}
			
			if (cur.num * 2 <= 100000 && !isVisited[cur.num * 2]) {
				queue.add(new Time(cur.num * 2, cur.count));
				isVisited[cur.num * 2] = true;
			}
			if (cur.num - 1 >= 0 && !isVisited[cur.num - 1]) {
				queue.add(new Time(cur.num - 1, cur.count + 1));
				isVisited[cur.num - 1] = true;
			}
			if (cur.num + 1 <= 100000 && !isVisited[cur.num + 1]) {
				queue.add(new Time(cur.num + 1, cur.count + 1));
				isVisited[cur.num + 1] = true;
			}

		}
	}
}
