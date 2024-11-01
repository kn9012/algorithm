import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String S, T;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		
		dfs(T);
		
		System.out.println(answer);
	}
	
	public static void dfs(String t) {
		if (t.length() == 0) return;
		if (t.equals(S)) {
			answer = 1;
			return;
		}
		
		
		if (t.charAt(t.length() - 1) == 'A') dfs(t.substring(0, t.length() - 1));
		if (t.charAt(0) == 'B') {
			StringBuffer sb = new StringBuffer(t.substring(1, t.length()));
			dfs(sb.reverse().toString());
		}
	}
}
