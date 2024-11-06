import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 16928번 뱀과 사다리 게임 
 * - BFS
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int board[] = new int[101];
		
		for (int i = 1; i <= 100; i++) {
			board[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int ladderH = Integer.parseInt(st.nextToken());
			int ladderT = Integer.parseInt(st.nextToken());
			board[ladderH] = ladderT;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int snakeH = Integer.parseInt(st.nextToken());
			int snakeT = Integer.parseInt(st.nextToken());
			board[snakeH] = snakeT;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		
		int count[] = new int[101];
		count[1] = 0;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 1; i <= 6; i++) {
				int newNum = cur + i;
				
				if (newNum > 100) continue;
				
				if (count[board[newNum]] == 0) {
					queue.add(board[newNum]);
					count[board[newNum]] = count[cur] + 1;
				}
				
				if (board[newNum] == 100) {
					System.out.println(count[100]);
					return;
				}
			}
		}
		
	}
}
