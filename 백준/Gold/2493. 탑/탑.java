import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * 백준 2493번 탑
 * 
 * 입력 받을때마다 stack에 넣으면서 top과
 */
public class Main {
	public static class Top {
		int value, height;
		
		public Top (int value, int height) {
			this.value = value;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		Stack<Top> stack = new Stack<>();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty()) {
				if (num <= stack.peek().value) {
					sb.append(stack.peek().height + " ");
					break;
				}
				else stack.pop();
			}

			if (stack.isEmpty()) sb.append("0 ");
			
			stack.push(new Top(num, i + 1));
		}
		
		System.out.println(sb);
	}
	
	
}
