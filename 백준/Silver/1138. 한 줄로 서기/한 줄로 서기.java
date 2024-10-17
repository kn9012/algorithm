import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int seat[] = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			int index = 1;
			while (true) {
				if (arr[i] == 0 && seat[index] == 0) {
					seat[index] = i;
					break;
				} else if (seat[index] == 0) arr[i]--;
				index++;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(seat[i] + " ");
		}
	}
}
