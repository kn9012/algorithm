import java.util.Scanner;

public class Main {
	static int answer = 0, n, map[];
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		map = new int[n];
		
		dfs(0); // 여기서 count는 행 번호
		
		System.out.println(answer);
	}
	
	public static void dfs(int count) {
		if (count == n) {
			answer++;
			return;
		}
		
		for (int i = 0; i < n; i++) {
			map[count] = i; // count행의 i열
			if (promising(count)) dfs(count + 1);
		}
	}
	
	public static boolean promising(int count) {
		for (int i = 0; i < count; i++) {
			// 다른 행인데 열이 같을 경우거나 같은 대각선에 있을 경우
			if (map[count] == map[i] || (count - i) == Math.abs(map[count] - map[i])) return false;
			
		}
		return true;
	}
}
