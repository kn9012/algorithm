import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 6번 규칙을 확인하지 못하고 여행을 다녀와 문제 풀이를 늦게 제출했습니다. 죄송합니다.
 * 
 * 
 * [문제] 백준 14889번 스타트와 링크
 * [아이디어] 사람수는 항상 짝수명으로 스타트 팀과 링크 팀으로 N/2명씩 나누기 위해 조합을 사용한다.
 * 스타트 팀을 기준으로 선택한 선수의 번호를 boolean 배열에 저장해두고 이를 이용해 팀의 능력치를 각각 더한다.
 * 능력치 차이의 최소값을 비교해가며 가장 작은 능력치 차이를 구한다.
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * 2023-10-01
 * 
 * @author 김유나
 *
 */

public class Main {
	static int arr[][], N, min = Integer.MAX_VALUE, num[];
	static boolean selected[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		selected = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);

		System.out.println(min);
	}

	public static void comb(int start, int count) {
		int sum[] = new int[2];
		if (count == N / 2) {
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (selected[i] && selected[j]) {
						sum[0] += arr[i][j] + arr[j][i];
					} else if (!selected[i] && !selected[j]) {
						sum[1] += arr[i][j] + arr[j][i];
					}
				}
			}

			int diff = Math.abs(sum[1] - sum[0]);

			if (diff == 0) {
				min = 0;
				return;
			}

			min = Math.min(min, diff);
			return;
		}

		for (int i = start; i < N; i++) {
			selected[i] = true;
			comb(i + 1, count + 1);
			selected[i] = false;
		}
	}
}