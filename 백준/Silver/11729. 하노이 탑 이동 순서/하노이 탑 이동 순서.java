import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int count = 0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 원판 개수, 출발 장대, 도착 장대, 다른 장대
		hanoi(n, 1, 3, 2);
		
		System.out.println(count);
		System.out.println(sb);
	}

	static void hanoi(int n, int from, int to, int other) {
		if (n == 0) return;
		
		count++;
		// n-1번째 원판까지는 도착 장대가 아닌 다른 장대로 일단 옮겨놔야 한다.
		hanoi(n - 1, from, other, to);
		sb.append(from + " " + to + "\n");
		// n 이상의 원판부터에서는 다른 장대에서 도착 장대로 옮겨야 한다.
		hanoi(n - 1, other, to, from);
	}
}