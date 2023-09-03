import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [문제] SWEA 1210번 Ladder
 * [아이디어]
 * 도착 좌표에서 1이 있는 길을 따라 올라가면 출발 좌표가 나오지 않을지?!
 * 
 * 메모리 : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-09-01
 * 
 */

public class Solution {	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) {
        	int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
            int arr[][] = new int[100][100]; // 사다리 배열
            int end = 0; // 지정된 도착점
            
            // 입력 받기
            for (int i = 0; i < 100; i++) {
                 st = new StringTokenizer(br.readLine());
                 for (int j = 0; j < 100; j++) { 
                	 arr[i][j] = Integer.parseInt(st.nextToken());
                     if (arr[i][j] == 2) end = j; // 도착점 저장 
                 }
            }
            
            int dx = 99;
            int dy = end;
            
            while (dx >= 0) {
            	if (dy - 1 > -1 && arr[dx][dy - 1] == 1) {
            		while (dy - 1 > -1 && arr[dx][dy - 1] == 1) dy -= 1;
            		dx -= 1;
            	}
            	else if (dy + 1 < 100 && arr[dx][dy + 1] == 1) {
            		while (dy + 1 < 100 && arr[dx][dy + 1] == 1) dy += 1;
            		dx -= 1;
            	}
            	else dx -= 1;
            }
            
            System.out.println("#" + tc + " " + dy);
        }
    }
}