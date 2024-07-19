import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 12919번 A와 B 2 
 *
 */

public class Main {
	static boolean isSame = false;
	static String T, S;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		
		dfs(T);
		
		if (!isSame) System.out.println(0);
		else System.out.println(1);
	}
	
	public static void dfs(String end) {
		if (end.length() == S.length()) {
			if (end.equals(S)) {
				isSame = true;
				return;
			}
			return;
		}
		if (end.charAt(end.length() - 1) == 'A') dfs(end.substring(0, end.length() - 1));
		if (end.charAt(0) == 'B') {
			StringBuffer sb = new StringBuffer(end.substring(1));
			dfs(sb.reverse().toString());
		}
	}
}
