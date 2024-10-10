import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 백준 3758번 KCPC
 * 
 * 테스트 케이스 별 우리 팀의 순위 출력하는 문제
 * 각 테케별로 팀의 개수 n, 문제 개수 k, 우리 팀 ID t, 로그 엔트리 개수 m
 * 로그 엔트리에는 팀 ID i, 문제 번호 j, 획득 점수 s
 * 
 * 팀과 문제 번호에 대해서 가장 높은 점수를 획득한 것이 최종 점수
 * 팀의 점수는 각 최종 점수들을 합한 것
 * 만약, 최종 점수가 같다면 제출 횟수가 적은 팀이 높음
 * 최종 점수와 제출 횟수 다 같다면 마지막 제출 시간이 빠른 팀이 높음
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken()); // 팀 개수
			int k = Integer.parseInt(st.nextToken()); // 문제 개수
			int t = Integer.parseInt(st.nextToken()); // 우리 팀 ID
			int m = Integer.parseInt(st.nextToken()); // 로그 엔트리 개수
			
			// 팀별로 문제 점수를 저장할 2차원 배열
			int[][] scores = new int[n + 1][k + 1]; // 팀별 문제 최고 점수 저장
			// 팀별 정보: 0 - 최종 점수 합계, 1 - 제출 횟수, 2 - 마지막 제출 시간
			Map<Integer, int[]> team = new HashMap<>();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int d = Integer.parseInt(st.nextToken()); // 팀 ID
				int j = Integer.parseInt(st.nextToken()); // 문제 번호
				int s = Integer.parseInt(st.nextToken()); // 획득 점수

				// 해당 팀의 문제 점수 업데이트 (최고 점수 유지)
				if (scores[d][j] < s) {
					scores[d][j] = s;
				}
				
				// 팀 정보 업데이트 (제출 횟수 및 마지막 제출 시간)
				if (!team.containsKey(d)) {
					team.put(d, new int[] {0, 0, i}); // 초기값 설정
				}
				int[] teamInfo = team.get(d);
				
				// 제출 횟수와 마지막 제출 시간 업데이트
				teamInfo[1]++;
				teamInfo[2] = i;
			}

			// 각 팀의 최종 점수 계산
			for (int d : team.keySet()) {
				int[] teamInfo = team.get(d);
				teamInfo[0] = 0; // 팀의 총 점수 초기화
				for (int j = 1; j <= k; j++) {
					teamInfo[0] += scores[d][j]; // 문제별 최고 점수 더함
				}
			}
			
			// 팀을 정렬하기 위해 List로 변환
			List<Map.Entry<Integer, int[]>> teamList = new LinkedList<>(team.entrySet());
			
			teamList.sort(new Comparator<Map.Entry<Integer, int[]>>() {
				@Override
				public int compare(Map.Entry<Integer, int[]> o1, Map.Entry<Integer, int[]> o2) {
					// 총 점수 기준 내림차순
					if (o1.getValue()[0] != o2.getValue()[0]) {
						return Integer.compare(o2.getValue()[0], o1.getValue()[0]);
					}
					// 제출 횟수 기준 오름차순
					if (o1.getValue()[1] != o2.getValue()[1]) {
						return Integer.compare(o1.getValue()[1], o2.getValue()[1]);
					}
					// 마지막 제출 시간 기준 오름차순
					return Integer.compare(o1.getValue()[2], o2.getValue()[2]);
				}
			});
			
			// 우리 팀 순위 찾기
			int rank = 1;
			for (Map.Entry<Integer, int[]> entry : teamList) {
				if (entry.getKey() == t) {
					System.out.println(rank);
					break;
				}
				rank++;
			}
		}
	}
}
