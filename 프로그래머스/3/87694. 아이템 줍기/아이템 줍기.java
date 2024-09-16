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
        
        for (int k = 0; k < rectangle.length; k++) {
            int x1 = rectangle[k][0] * 2;
            int y1 = rectangle[k][1] * 2;
            int x2 = rectangle[k][2] * 2;
            int y2 = rectangle[k][3] * 2;
            
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (map[i][j] == 2) continue;
                    map[i][j] = 2;
                    
                    if (i == x1 || i == x2 || j == y1 || j == y2) map[i][j] = 1;
                }
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
                
                if (dx < 0 || dx > 102 || dy < 0 || dy > 102 || isVisited[dx][dy] || map[dx][dy] != 1) continue;
                
                queue.add(new int[] {dx, dy, (distance + 1)});
                isVisited[dx][dy] = true;
            }
        }
    }
}