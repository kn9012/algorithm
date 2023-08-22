import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 3289번 서로소 집합
 * 각각 1부터 n까지 n개의 집합이 있을 때, 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산을 수행한다.
 * 이때, 같은 집합에 속해있다면 1, 아니라면 0을 출력한다.
 * [아이디어] 
 * 서로소 집합 연산인 make, find, union 메소드를 각각 만들어 받은 연산대로 수행한다.
 * 
 * 메모리 : 109,124kb
 * 실행 시간 : 735ms
 * 
 * @author 김유나
 * 2023-08-22
 *
 */

public class Solution {
	static int n; // 집합 개수 (1~n)
	static int parents[]; // 부모 index 저장
	
	// n개만큼의 최소 단위 집합 만들기
	private static void make() {
		parents = new int[n + 1];
		for(int i = 1; i <= n; i++) parents[i] = i; // 부모 index가 자기 자신
	}
	
	// a를 포함하는 집합을 찾는 연산
	private static int find(int a) {
		if (a == parents[a]) return a; // a가 부모와 같다면 a return
		return parents[a] = find(parents[a]); // 같지 않다면 재귀를 통해 부모 찾고 return
	}
	
	// 합집합
	private static boolean union(int a, int b) {
		if (find(a) == find(b)) return false; // a와 b의 부모가 같을 때 = a와 b가 같은 집합에 있을 때 false
		parents[find(b)] = find(a); // 같지 않다면 b의 부모에 a의 부모 넣기
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // n개의 집합
			int m = Integer.parseInt(st.nextToken()); // 연산 횟수
					
			ArrayList<Integer> list = new ArrayList<>(); // 출력할 값 담을 리스트
			
			make(); // 집합 만들기
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); // 0 또는 1
				int a = Integer.parseInt(st.nextToken()); // a가 포함되어 있는 집합
				int b = Integer.parseInt(st.nextToken()); // b가 포함되어 있는 집합
				
				// 합집합 : union
				if (x == 0) {
					union(a, b);
				}
				
				// 같은 집합인지 확인하는 연산 : find
				else if (x == 1) {
					if (find(a) == find(b)) list.add(1); // a와 b의 부모가 같으면 1
					else list.add(0); // 다르면 0
				}
			}
			
			System.out.print("#" + tc + " ");
			for (int i = 0; i < list.size(); i++) System.out.print(list.get(i));
			System.out.println();
		}
	}
}
