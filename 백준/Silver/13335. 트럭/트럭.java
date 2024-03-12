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
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int time = 0;
		int weight = 0;
		
		st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new ArrayDeque<>();
		Queue<Integer> truck = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			truck.add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < w; i++) {
			queue.add(0);
		}
		
		while (!queue.isEmpty()) {
			time++;
			weight -= queue.poll();
			
			if (!truck.isEmpty()) {
				if (truck.peek() + weight <= L) {
					weight += truck.peek();
					queue.add(truck.poll());
				} else {
					queue.add(0);
				}
			}
		}
		
		System.out.print(time);
	}
}