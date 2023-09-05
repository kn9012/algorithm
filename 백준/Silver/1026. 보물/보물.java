import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1026번 보물
 * [아이디어] 순열을 이용하여 풀었으나 시간 초과가 떠 (50!을 다 계산해야 한다고 한다.)
 * A 배열은 오름차순, B 배열은 내림차순을 하여 순서대로 곱했다.
 * 문제에서는 B의 수는 재배열하면 안된다 했으나 A 배열을 오름차순 한 뒤, B 배열에서 큰 수를 뽑아 순서대로 곱하는 것과 같다.
 * 
 * 메모리 : 14,592kb
 * 실행 시간 : 132ms
 * 
 * @author 김유나
 * 2023-09-05
 *
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 길이
		int sum = 0; // A와 B의 곱을 더할 변수
		int[] a = new int[n]; // A 배열
		int[] b = new int[n]; // B 배열
		
		// A 배열 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		// B 배열 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		// A 배열 오름차순 정렬, B 배열 내림차순 정렬
		Arrays.sort(a);
		Arrays.sort(b);
		
		// 순서대로 곱하기
		for (int i = 0; i < n; i++) {
			sum += a[i] * b[n - i - 1];
		}
		
		System.out.println(sum);
	}
}