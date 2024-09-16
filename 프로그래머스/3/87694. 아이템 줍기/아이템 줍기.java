/**
 * 프로그래머스 아이템 줍기
 * - BFS 사용
 * - map을 2배해서 직사각형 외부는 0, 선은 1, 내부는 2로
 */

import java.util.*;

class Solution {
    static int answer = 0;
    static int map[][], deltas[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static boolean isVisited[][];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[102][102];
        isVisited = new boolean[102][102];
        
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            // 직사각형 내부 2
            for (int j = x1 + 1; j < x2; j++) {
                for (int k = y1 + 1; k < y2; k++) {
                    map[j][k] = 2;
                }
            }
            
            // 직사각형 선 1
            for (int j = x1; j <= x2; j++) {
                if (map[j][y1] == 2) continue;
                else map[j][y1] = 1;
            }
            
            for (int j = x1; j <= x2; j++) {
                if (map[j][y2] == 2) continue;
                else map[j][y2] = 1;
            }
            
            
            for (int j = y1; j <= y2; j++) {
                if (map[x1][j] == 2) continue;
                else map[x1][j] = 1;
            }
            
            for (int j = y1; j <= y2; j++) {
                if (map[x2][j] == 2) continue;
                else map[x2][j] = 1;
            }
        }
                
        bfs(new int[] {characterX * 2, characterY * 2}, new int[] {itemX * 2, itemY * 2});
        return answer / 2;
    }
    
    public void bfs(int c[], int i[]) {
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[] {c[0], c[1], 0});
        isVisited[c[0]][c[1]] = true;
        
        while (!queue.isEmpty()) {
            int cur[] = queue.poll();
            int X = cur[0];
            int Y = cur[1];
            int distance = cur[2];
            
            if (X == i[0] && Y == i[1]) {
                answer = distance;
                break;
            }
            
            for (int j = 0; j < 4; j++) {
                int dx = X + deltas[j][0];
                int dy = Y + deltas[j][1];
                
                if (dx < 0 || dx > 101 || dy < 0 || dy > 101 || isVisited[dx][dy] || map[dx][dy] != 1) continue;
                
                queue.add(new int[] {dx, dy, (distance + 1)});
                isVisited[dx][dy] = true;
            }
        }
    }
}