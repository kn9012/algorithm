import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 백준 9184번 신나는 함수 실행
 * 1. a, b, c 중 하나라도 0 이하면 return 1
 * 2. a, b, c 중 하나라도 20 초과면 return w(20, 20, 20)
 * 3. a<b, b<c면 w(a, b, c-1)+w(a, b-1, c-1)-w(a, b-1, c)
 * 4. 이외라면 w(a-1, b, c)+w(a-1, b-1, c)+w(a-1, b, c-1)-w(a-1, b-1, c-1)
 */

public class Main {
	public static int dp[][][] = new int[21][21][21];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			String str = br.readLine();
			
			if (str.equals("-1 -1 -1")) break;
			
			StringTokenizer st = new StringTokenizer(str);
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
				
			bw.write("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c) + "\n");	
		}
		
		bw.flush();
		bw.close();
	}
	
	static int w(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0) return 1;
		
		if (a > 20 || b > 20 || c > 20) return w(20, 20, 20);
		
		if (dp[a][b][c] != 0) return dp[a][b][c];
		
		if (a < b && b < c){
			dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		} else {
			dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);			
		}
		
		return dp[a][b][c];
	}
}
