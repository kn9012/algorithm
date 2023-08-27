import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 15686번 치킨 배달
 * 크기가 NxN인 도시가 있을 때 0은 빈칸, 1은 집, 2는 치킨집이다.
 * 치킨 거리는 집과 가까운 치킨집 사이의 거리로 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다.
 * 도시의 치킨 거리는 모든 집의 치킨 거리의 합일때, 도시의 치킨 거리가 최소가 되도록 하여라.
 * 
 * [아이디어]
 * 최대 M개의 치킨집을 골라야하므로 조합을 이용하여 지도의 2 좌표 중 M개를 골라 집과의 거리가 가장 작은 것을 구한다.
 * 처음에는 각 집의 치킨 거리(가장 작은 값)를 구한 뒤 그 값들을 더하는 것을 구하는 줄 알았는데
 * 조합에 따른 치킨 거리를 구한 뒤 그 값이 가장 작은 조합을 구하는 것이였다. 
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-27
 *
 */
public class Main {
	static int n, m, min = Integer.MAX_VALUE;
	static ArrayList<int[]> chickenH;
	static int[] select;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 도시 한 변의 크기
		m = Integer.parseInt(st.nextToken()); // 고를 치킨집의 최대 개수
		
		arr = new int[n][n]; // 도시 지도 배열
		chickenH = new ArrayList<int[]>(); // 치킨집 좌표를 저장할 리스트
		select = new int[m]; // M개만큼 고른 치킨집 저장할 배열
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) chickenH.add(new int[] {i, j}); // 좌표가 2인 경우 (치킨집인 경우) 좌표 저장
			}
		}
		
		comb(0, 0);
		
		System.out.println(min);
	}
	
	static void comb(int start, int count) {
		 if (count == m) { // m개 골랐을 경우
			 min = Math.min(min, check()); // 조합의 최소값 구하기
			 return;
		 }
		 
		 // 조합
		 for (int i = start; i < chickenH.size(); i++) {
			 select[count] = i;
			 comb(i + 1, count + 1);
		 }	 
	}
	
	static int check() {
		int sum = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 1) { // 좌표가 1인 경우 (집인 경우)
					int minSum = Integer.MAX_VALUE;
					
					for (int k = 0; k < m; k++) {
						int[] chicken = chickenH.get(select[k]); // 치킨집 좌표
						int distance = Math.abs(i - chicken[0]) + Math.abs(j - chicken[1]); // 거리 계산
						minSum = Math.min(minSum, distance); // 치킨 거리 : 가장 작은 거리
					}
					sum += minSum; // 조합의 치킨 거리 합
				}
			}
		}
		return sum;
	}
}