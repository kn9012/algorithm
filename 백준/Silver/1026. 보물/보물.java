import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 1026번 보물
 * [아이디어] 순열
 * 
 * @author 김유나
 * 2023-09-05
 *
 */

public class Main {
	static int n, sum = 0;
	static int[] a, b, perm;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		b = new int[n];
		visited = new boolean[n];
		perm = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		Integer[] c = Arrays.stream(b).boxed().toArray(Integer[]::new);
		
		Arrays.sort(a);
		Arrays.sort(c, Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			sum += a[i] * c[i];
		}
		
		System.out.println(sum);
	}
}