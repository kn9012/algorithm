import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * [문제] 백준 11729번 하노이 탑 이동 순서
 * [아이디어] 재귀를 이용한 풀이
 * 가장 작은 원판을 1, 가장 큰 원판을 n이라 할 때, 1부터 n-1까지는 도착 기둥, 즉 3번 기둥이 아닌 다른 기둥으로 옮겨야 한다.
 * n-1번째 원판까지 옮기고 나서는 도착 기둥으로 원판들을 옮겨야 한다.
 * 원판들을 옮길때마다 시간 단축을 위한 StringBuilder에 넣어주고 count를 세준다. 
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-31
 * 
 */
public class Main {
	static int count = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 원판 개수
		
		// 원판 개수, 출발 기둥, 도착 기둥, 임시 기둥
		hanoi(n, 1, 3, 2);
		System.out.println(count);
		System.out.println(sb);
	}
	
	static void hanoi(int n, int from, int to, int other) {
		// 기저조건
		if (n == 0) {
			return;
		}
		
		count++;
		hanoi(n - 1, from, other, to); // n-1번째 원판 다른 기둥에 옮기기
		sb.append(from).append(" ").append(to).append("\n");
		hanoi(n - 1, other, to, from); // 다른 기둥에 롬겨놨던 원판들 도착 기둥에 옮기기 
	}
}
