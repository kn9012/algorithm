import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * @author 김유나
 * 2023-08-09
 * [문제] 백준 11286번 절댓값 힙
 * 
 * [아이디어]
 * - 최소힙을 사용하기 위해 PriorityQueue를 사용한다. 이때, 절댓값을 비교하고 출력해야 하므로 Math.abs 사용?
 * - o1에게 높은 우선순위를 주고 싶으면 -1 return, o2에게 높은 우선순위룰 주고 싶으면 1 return
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
			int num1 = Math.abs(o1);
			int num2 = Math.abs(o2);
			
			if (num1 == num2) {
				return o1 > o2 ? 1 : -1;
			}
			else if (num1 > num2) {
				return 1;
			}
			else {
				return -1;
			}
		});
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num != 0) {
				heap.offer(num);				
			}
			
			else {
				if (heap.isEmpty()) System.out.println(0);
				else System.out.println(heap.poll());
			}
		}
	}
}
