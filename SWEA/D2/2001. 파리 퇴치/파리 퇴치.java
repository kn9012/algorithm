import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static int N, M;
	static int arr[][];
	static int sum[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			sum = new int[N - M + 1][N - M + 1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N - M+1; i++) {
				for (int j = 0; j < N - M+1; j++) {
					formoon(sum, i, j);
				}
			}
			
			int max = 0;
			for (int i = 0; i < sum.length; i++) {
				for (int j = 0; j < sum.length; j++) {
					//System.out.print(sum[i][j] + " ");
					if (max < sum[i][j]) {
						max = sum[i][j];
					}
				}
				//System.out.println();
			}
			System.out.println("#" + tc + " " + max);
		}
	}
	static void formoon(int sum[][], int i, int j) {
		for (int k = 0; k < M; k++) {
			for (int l = 0; l < M; l++) {
				sum[i][j] += arr[i + k][j + l];
			}
		}
	}
}
