import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author 김유나
 * 2023-08-10
 * 
 * [문제] 
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		
		Queue<Integer> cards = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			cards.offer(i);
		}
		
		if (N == 1) {
			System.out.println(cards.peek());
		}
		
		else {
			while (!cards.isEmpty()) {
				cards.poll();
				if (cards.size() == 1) break;
				cards.offer(cards.poll());
			}
			System.out.println(cards.peek());
		}
	}
}
