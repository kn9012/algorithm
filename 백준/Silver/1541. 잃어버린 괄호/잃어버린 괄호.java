import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1541번 잃어버린 괄호
 * - 덧셈 부분을 먼저 계산하기
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split("-");
		int sum = Integer.MAX_VALUE;
		
		for (int i = 0; i < str.length; i++) {
			String addition[] = str[i].split("\\+");
			int num = 0;
			
			for (int j = 0; j < addition.length; j++) {
				num += Integer.parseInt(addition[j]);
			}
			
			if (sum == Integer.MAX_VALUE) sum = num;
			else sum -= num;
		}
		
		System.out.println(sum);
	}
}	
