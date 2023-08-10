import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author 김유나
 * 2023-08-10
 * [문제] 백준 2309번 일곱 난쟁이
 * - 아홉 명의 난쟁이 중 진짜 난쟁이 일곱명을 찾아라. 이때, 일곱 난쟁이의 키의 합이 100이다.
 * 
 *
 */
public class Main {
	static int height[], real[], flag = 0, sum = 0;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		height = new int[9];
		real = new int[7];
		
		for (int i = 0; i < 9; i++) {
			height[i] = s.nextInt();
		}
		
		comb(0, 0);
	}
	
	static void comb(int count, int start) {
		if (flag == 1) return;
		if (count == 7) {
			sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += real[i];
			}
			
			if (sum == 100) {
				flag++;
				Arrays.sort(real);
				for (int i = 0; i < 7; i++) {
					System.out.println(real[i]);
				}
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			real[count] = height[i];
			comb(count + 1, i + 1);
		}
	}
}
