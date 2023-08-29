import java.util.Scanner;

/**
 * 
 * [문제] 백준 1463번 1로 만들기
 * 정수 X에 사용할 수 있는 연산은 1. X가 3으로 나누어 떨어지면 3으로 나눈다. 2. X가 2로 나누어 떨어지면 2로 나눈다. 3. 1을 뺀다.
 * 
 * [아이디어]
 * 
 * @author 김유나
 * 2023-08-29
 * 
 */

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] memo = new int[n + 3];
		int min = 0;
		
		memo[0] = 0;
		memo[1] = 0;
		memo[2] = 1;
		memo[3] = 1;
		
		for (int i = 4; i <= n; i++) {
			min = Integer.MAX_VALUE;

			if (i % 3 == 0)	min = memo[i / 3] + 1;
			if (i % 2 == 0) min = Math.min(min, memo[i / 2] + 1);
			min = Math.min(min, memo[i - 1] + 1);
			
			memo[i] = min;
		}
		
		System.out.println(memo[n]);
	}
}