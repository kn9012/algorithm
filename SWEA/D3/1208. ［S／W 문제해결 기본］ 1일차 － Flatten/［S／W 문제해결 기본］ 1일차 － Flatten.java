
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 김유나
 * 2023-08-01
 * [문제] SWEA D3 1208번 SW 문제해결 기본 1일차 - Flatten
 * [아이디어] max, min을 
 * 
 */
public class Solution {
	static int dumpN;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		for (int T = 1; T <= 10; T++) {
			dumpN = Integer.parseInt(br.readLine());
			
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			int[] arr = new int[100];
			
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + T + " " + flatten(arr));
		}
	}
	
	static int flatten(int[] arr) {
		
		int maxI = 0;
		int minI = 0;
		while (dumpN > 0) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;

			for (int i = 0; i < arr.length; i++) {
				if (arr[i] >= max ) {
					max = arr[i];
					maxI = i;
				}
			}
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] <= min) {
					min = arr[i];
					minI = i;
				}
			}
			
			
//			if (max - min <= 1) 
//				return max - min;
			
			arr[maxI] -= 1;
			arr[minI] += 1;

			dumpN--;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] >= max ) {
					max = arr[i];
					maxI = i;
				}
			}
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] <= min) {
					min = arr[i];
					minI = i;
				}
			}

			//System.out.println(Arrays.toString(arr));
			
		}
		
		
		return arr[maxI] - arr[minI];
	}
}
