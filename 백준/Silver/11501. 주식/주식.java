import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] agrs) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			Stack<Integer> stack = new Stack<>();
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				stack.push(num);
			}
			
			long sum = 0;
			int max = stack.pop();
			while (!stack.isEmpty()) {
				int cur = stack.peek();
				
				if (max > cur) sum += max - cur;
				else max = cur;
				stack.pop();
			}
			
			System.out.println(sum);
		}
	}
}
