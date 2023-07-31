import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
 
		sb.append((int) (Math.pow(2, N) - 1)).append('\n');
 
		Hanoi(N, 1, 2, 3);
		System.out.println(sb);
	}

	public static void Hanoi(int N, int start, int temp, int to) {
		if (N == 1) {
			sb.append(start + " " + to + "\n");
			return;
		}
		Hanoi(N - 1, start, to, temp);
    
		sb.append(start + " " + to + "\n");
    
		Hanoi(N - 1, temp, start, to);
	}
}
