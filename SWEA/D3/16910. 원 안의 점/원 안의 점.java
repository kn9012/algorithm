import java.util.Scanner;

/**
 * 
 * 
 * @author 김유나
 * SWEA 16910 원 안의 점
 * - x^2 + y^2 = N^2의 원에서 반지름 N이 주어질때, 원 안에 포함되는 격자점(x, y 좌표가 모두 정수인)의 개수를 구하여라.
 * 
 * 문제에서 x^2 + y^2 <= N^2인 격자점을 개수를 구하라고 했는데 여기서 힌트를 얻었다.
 * 
 *
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt(); // test case 입력 받기
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			
			int count = 0;
			
			for (int x = -N; x <= N; x++) {
				for (int y = -N; y <= N; y++) {
					if (x * x + y * y <= N * N) {
						count++;
					}
				}
			}
			
			
			System.out.println("#" + test_case + " " + count);
		}
	}
}