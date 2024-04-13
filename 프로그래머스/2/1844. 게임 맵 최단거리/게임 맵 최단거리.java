/**
 0 : 벽 o
 1 : 벽 x
 
 (1, 1)에서 (5, 5)까지 도달하는 최단거리 구하기 => BFS 사용
*/

import java.util.*;
class Solution {
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean isVisited[][];
    static int dis[][];
    static public int solution(int[][] maps) {
        isVisited = new boolean[maps.length][maps[0].length];
        dis = new int[maps.length][maps[0].length];
        
        return bfs(maps);
    }
    
    static public int bfs(int[][] maps) {
        int deltas[][] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        
        Queue<Point> q = new ArrayDeque<>();
        isVisited[0][0] = true;
        q.add(new Point(0, 0));
        dis[0][0] = 1;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            
            for (int i = 0; i < 4; i++) {
                int dx = x + deltas[i][0];
                int dy = y + deltas[i][1];
                
                if (dx >= maps.length || dy >= maps[0].length || dx < 0 || dy < 0 || isVisited[dx][dy] || maps[dx][dy] == 0) continue;
                
                q.add(new Point(dx, dy));
                isVisited[dx][dy] = true;
                
                if (dis[dx][dy] == 0) dis[dx][dy] = dis[x][y] + 1;
            }
        }
        
        if (dis[maps.length - 1][maps[0].length - 1] == 0) return -1;
        return dis[maps.length - 1][maps[0].length - 1];
    }
}