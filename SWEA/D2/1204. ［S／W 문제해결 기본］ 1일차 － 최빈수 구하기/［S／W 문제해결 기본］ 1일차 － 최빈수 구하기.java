import java.util.Scanner;
import java.io.FileInputStream;
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int TC = sc.nextInt();
			int score[] = new int[101];
			int mostInt = 0;
			
			for (int i = 0; i < 1000; i++) {
				int newInt = sc.nextInt();
				score[newInt]++;
			}
			
			int max = 0;
			for (int i = 0; i < 101; i++) {
				if (score[i] >= max) {
					max = score[i];
					mostInt = i;
				}
			}
			
			
			System.out.println("#" + TC + " " + mostInt);
		}
	}
}