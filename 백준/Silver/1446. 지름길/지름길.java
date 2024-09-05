import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[] dp = new int[10_001];
		int[][] highway = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			highway[i][0] = Integer.parseInt(st.nextToken());
			highway[i][1] = Integer.parseInt(st.nextToken());
			highway[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
        
		for (int i = 1; i < dp.length; i++) {
			for (int h = 0; h < highway.length; h++) { // i위치에서 탑승 가능한 고속도로가 있는지 확안합니다.
            	// 기존 값(0번부터 h-1까지의 고속도로를 모두 고려한 상태), i-1에서 1칸 이동, 현재(h번) 고속도로 탑승 -> 중 최솟값 선택
				if(i == highway[h][1]) { 
					dp[i] = Math.min(dp[i],
											Math.min(dp[i-1]+1, dp[highway[h][0]]+highway[h][2])
									);
				}else { // 고속도로가 없는 경우
					dp[i] = Math.min(dp[i], dp[i-1]+1); 
				}
			}
		}
		
		System.out.println(dp[D]);
		

	}
}