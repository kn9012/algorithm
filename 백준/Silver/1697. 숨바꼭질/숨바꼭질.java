import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean isVisited[] = new boolean[100001];
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {N, 0});
		isVisited[N] = true;
		
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			
			if (cur[0] == K) {
				System.out.println(cur[1]);
				break;
			}
			
			int minus = cur[0] - 1;
			int plus = cur[0] + 1;
			int twice = cur[0] * 2;
			
			if (minus >= 0 && !isVisited[minus]) {
				queue.add(new int[] {minus, cur[1] + 1});
				isVisited[minus] = true;
			}
			if (plus <= 100000 && !isVisited[plus]) {
				queue.add(new int[] {plus, cur[1] + 1});
				isVisited[plus] = true;
			}
			if (twice <= 100000 && !isVisited[twice]) {
				queue.add(new int[] {twice, cur[1] + 1});
				isVisited[twice] = true;
			}
		}
	}
}
