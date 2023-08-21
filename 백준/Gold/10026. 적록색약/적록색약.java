
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * [문제] 백준 10026번 적록색약
 * 크기가 NxN인 그리드의 각 칸에 R, G, B 중 하나가 색칠되어 있는데 적록 색약을 가지고 있는 사람은 초록색과 빨강색을 구분하지 못한다.
 * 같은 색상이 상하좌우로 인접해 있는 경우를 한 구역이라고 할 때, 적록 색약이 아닌 사람과 적록 색약을 가지고 있는 사람이 봤을 때의 구역 수를 구하여라.
 * 
 * [아이디어] DFS를 구현하여 적록 색약이 아닌 사람과 적록 색약인 사람이 보는 배열을 각각 만들어 구역의 수를 구한다.
 * 이때, 방문한 칸인지 체크하기 위해 boolean 배열을 만들고 가장 최근의 값과 사방탐색을 한 값이 같은지 비교한다. 
 * 
 * 메모리 : kb 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-08-21
 *
 */
public class Main {
    static int n, cnt = 0;
    static boolean isVisited[][];
    static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 배열의 크기
        char [][] arr = new char[n][n]; // 적록 색약이 아닌 사람의 배열
        char [][] colorArr = new char[n][n]; // 적록 색약인 사람의 배열
        isVisited = new boolean[n][n]; // 방문 체크 배열

        // 적록 색약인 사람과 아닌 사람의 배열 각각 입력받기
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = str.charAt(j);
                arr[i][j] = c;
                
                // 적록 색약인 경우 R을 G로 변경 (둘의 차이를 느끼지 못하기 때문에)
                if (c == 'R') colorArr[i][j] = 'G';
                else colorArr[i][j] = c;
            }
        }

        
        // 적록 색약이 아닌 경우
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		// 방문되지 않은 칸
        		if (!isVisited[i][j]) {
        			dfs(i, j, arr);
        			cnt++;
        		}
        	}
        }
        // 구역 수 출력
        System.out.print(cnt);
        
        // 방문 체크 배열, count 초기화
        isVisited = new boolean[n][n];
        cnt = 0;
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		// 방문되지 않은 칸
        		if (!isVisited[i][j]) {
        			dfs(i, j, colorArr);
        			cnt++;
        		}
        	}
        }
        // 구역 수 출력
        System.out.println(" " + cnt);
    }

    static void dfs(int x, int y, char arr[][]) {
        isVisited[x][y] = true; // 방문 체크
        char current = arr[x][y]; // 최근 값

        // 사방탐색
        for (int i = 0; i < 4; i++) {
            int dx = x + deltas[i][0];
            int dy = y + deltas[i][1];

            // 배열의 범위를 벗어날 경우
            if (dx < 0 || dy < 0 || dx >= n || dy >= n)
                continue;

            // 방문하지 않았고 최근 값과 같다면 계속해서 호출
            if (!isVisited[dx][dy] && arr[dx][dy] == current) {
                dfs(dx, dy, arr);
            }
        }
    }
}