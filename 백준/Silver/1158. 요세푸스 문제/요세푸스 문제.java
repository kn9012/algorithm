import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김유나
 * 2023-08-07
 * 
 * [문제] 백준 1158번 요세푸스 문제
 * N명의 사람들이 원을 이루면서 앉아있을때, K번째 사람을 제거한다.
 * 이와 같은 과정을 N명의 사람이 모두 제거될 때까지 계속할 때, 사람들이 제거되는 순서를 출력하여라.
 * 
 * [아이디어]
 * N명의 사람 중 K번째 사람을 제거하고 그 이후의 K번째 사람을 제거하는 방식이 반복되므로 Queue를 사용하였다.
 * K-1번째 사람은 enQueue한 뒤 deQueue하고 K번째 사람은 제거하는 과정을 반복했다.
 * 출력 형식과 같이 출력하기 위해서 StringBuilder를 사용했다.
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 시간 단축을 위한 BufferedReader
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); // 출력 형식에 맞추고 시간 단축을 위한 StringBuilder 사용
		
		int N = Integer.parseInt(st.nextToken()); // N 입력받기
		int K = Integer.parseInt(st.nextToken()); // K 입력받기
		
		Queue<Integer> que = new LinkedList<>(); // Queue 선언
		
		for (int i = 1; i <= N; i++) {
			que.offer(i); // Queue에 1부터 N까지 순서대로 넣기
		}

		sb.append("<");
		int index = 0; // 마지막 사람인지 판별할 index
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < K - 1; j++) {
				que.offer(que.peek());
				que.poll();
			}
			
			sb.append(que.peek());
			if (i == N - 1) sb.append(">");
			else sb.append(", ");

			que.poll();
		}
		System.out.println(sb);
	}
}
