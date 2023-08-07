
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<>();
		
		
		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}

		sb.append("<");
		int index = 0;
		
		while (index < N) {
			for (int i = 0; i < K - 1; i++) {
				que.offer(que.peek());
				que.poll();
			}
			sb.append(que.peek());
			index++;
			if (index == N) sb.append(">");
			else sb.append(", ");

			que.poll();
		}
		System.out.println(sb);
	}

}
