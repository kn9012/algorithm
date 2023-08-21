import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * [문제] 백준 1697번 숨바꼭질
 * 
 * [아이디어] BFS 그래프
 * 수빈이가 이동할 수 있는 것은 2*X, X-1, X+1
 * 
 * @author 김유나
 * 2023-08-21
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int sec = 0;
		boolean [] isVisited = new boolean[100001];

		Queue<int []> queue = new ArrayDeque<>();
		queue.offer(new int[] {n, 0});
		isVisited[n] = true;
		
		while (!queue.isEmpty()) {
			int current[] = queue.poll();
			int loc = current[0]; // 수빈이 위치
			int depth = current[1]; // 깊이(초)
			
			if (loc == k) { // 수빈이가 동생의 위치에 도달했다면
				sec = depth; // 그떄의 깊이가 초가 됨
				break;
			}
			depth++;
			
			isVisited[loc] = true; // 수빈이 위치의 인덱스 true
		
			if (loc * 2 <= 100000 && !isVisited[loc * 2]) {
				queue.offer(new int[] {loc * 2, depth});
				isVisited[loc * 2] = true;
			}
			if (loc + 1 <= 100000 && !isVisited[loc + 1]) {
				queue.offer(new int[] {loc + 1, depth});
				isVisited[loc + 1] = true;
			}
			if (loc - 1 >= 0 && !isVisited[loc - 1]) {
				queue.offer(new int[] {loc - 1, depth});
				isVisited[loc - 1] = true;
			}
		}
		
		System.out.println(sec);
	}
}