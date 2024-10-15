import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 1406번 에디터
 * - Stack 사용
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());
		
		Stack<String> stack1 = new Stack<>();
		Stack<String> stack2 = new Stack<>();
		
		for (String s : str.split("")) {
			stack1.push(s);
		}
		
		String command;
		char c;
		
		for (int i = 0; i < M; i++) {
			command = br.readLine();
			c = command.charAt(0);
			if (c == 'L' && !stack1.isEmpty()) {
				stack2.push(stack1.pop());
			} else if (c == 'D' && !stack2.isEmpty()) {
				stack1.push(stack2.pop());
			} else if (c == 'B' && !stack1.isEmpty()) {
				stack1.pop();
			} else if (c == 'P'){
				stack1.push(command.charAt(2) + "");
			}
		}
		
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		
		while (!stack2.isEmpty()) {
			sb.append(stack2.pop());
		}
		
		System.out.println(sb);
	}
}
