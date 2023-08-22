import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 13023번 ABCDE
 * 총 N명이 있을 때 사람들은 0번부터 N-1번까지 번호가 매겨져 있고 일부 사람들은 친구이다.
 * 사람의 수 N과 친구 관계의 수 M이 주어지고 M번만큼 a와 b가 친구라고 주어질때 A-B, B-C, C-D, D-E와 같은 친구 관계가 존재하는지 구하여라.
 * 
 * [아이디어]
 * A-B, B-C, C-D, D-E와 같은 친구 관계란, 간선이 M개고 정점이 N개인 그래프에서 간선이 4개인 관계가 존재하는지 찾는 것이다.
 * 따라서 DFS를 사용하였고 만약 탐색하는 도중 간선이 4개가 아니라면 다른 시작 정점을 정해야하므로 방문 배열을 초기해준다.
 * 또한 무향 그래프이기 때문에 2차원 배열보단 크기가 유동적인 ArrayList 타입의 배열이 유용하다.
 * 
 * 메모리 : 20,912kb
 * 실행 시간 : 316ms
 * 
 * @author 김유나
 * 2023-08-22
 *
 */
public class Main {
	static int n, m;
	static boolean[] isVisited;
	static boolean isExist = false;
	static ArrayList<Integer> [] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 총 사람 수
		m = Integer.parseInt(st.nextToken()); // 친구 관계 수

		list = new ArrayList[n]; // ArrayList<Integer> 타입의 n 크기의 배열
		
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>(); // n번 ArrayList 객체 만들기
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 첫번째 친구 a
			int b = Integer.parseInt(st.nextToken()); // 두번째 친구 b
			
			// 무향 그래프이기 때문에 (a, b), (b, a) 모두 값 대입
			list[a].add(b); 
			list[b].add(a);
		}

		for (int i = 0; i < n; i++) {
			// 시작 정점 i로부터 탐색 시작 됐을때 조건(간선 4개)을 만족시키지 않을 수 있으니 방문 배열 매번 선언해주기
			isVisited = new boolean[n]; 
			isVisited[i] = true; // 방문 체크
			dfs(i, 0); // 정점 i와 count(0)
		}

		// 존재한다면 1 출력, 존재하지 않는다면 0 출력
		if (isExist) System.out.println(1);
		else System.out.println(0);
	}

	static void dfs(int i, int count) {
		// 만약 존재한다면
		if (isExist) return;
		
		// 간선이 4개에 도달한다면 존재하는 것
		if (count == 4) isExist = true;
	
		for (int j = 0; j < list[i].size(); j++) {
			if (!isVisited[list[i].get(j)]) { // 방문되지 않았을 경우
				isVisited[list[i].get(j)] = true; // 방문 체크
				dfs(list[i].get(j), count + 1);
				// 재귀를 벗어난 경우 : 간선 4개 충족이 안됐기 때문에 방문 체크 해제
				isVisited[list[i].get(j)] = false;
			}				
		}
	}
}
