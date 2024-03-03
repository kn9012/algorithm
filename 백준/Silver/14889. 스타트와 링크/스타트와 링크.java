import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, arr[][], min = Integer.MAX_VALUE;
	static boolean isSelect[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		isSelect = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(1, 0);
		
		System.out.print(min);
	}
	
	/**
	 * 조합 먼저
	 * 선택된 것들과 선택되지 않은 것들의 차이 구한 후 min값 구하기 
	 */
	
	static void comb(int start, int count) {
		int selectSum = 0;
		int notSelectSum = 0;
		
		if (count == N / 2) {
			for (int i = 1; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					if (isSelect[i] && isSelect[j]) selectSum += arr[i][j] + arr[j][i];
					else if (!isSelect[i] && !isSelect[j]) notSelectSum += arr[i][j] + arr[j][i];
				}
			}
			
			int diff = Math.abs(selectSum - notSelectSum);
			
			if (diff == 0) {
				min = 0;
				return;
			}
			
			min = Math.min(diff, min);
			return;
		}
		
		for (int i = start; i <= N; i++) {
			isSelect[i] = true;
			comb(i + 1, count + 1);
			isSelect[i] = false;
		}
	}
}