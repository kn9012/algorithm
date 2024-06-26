/**
 * 프로그래머스 미로 탈출
 *
 * 아이디어
 * 먼저 레버가 있는 곳으로 이동 후 출구로 이동한다
 * 이때, 입구 -> 레버, 레버 -> 출구 모두 최소 이동 거리여야 한다
 * 최소 이동 거리 -> BFS
 * 
 * 처음엔 입구 -> 레버 -> 출구의 최단거리를 구했는데 레버 방문 후 출구까지 갈 때 방문했던 곳을 또 방문할 수 있어서 틀렸다
 * 1. 입구 -> 레버 2. 레버 -> 출구 각각을 count한 뒤 값을 더해서 해결해줬다
 */

import java.util.*;

class Solution {
    class Point {
        int x, y, cnt;
        
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    boolean visitLever = false; // 레버 당겼는지 확인 flag
    int end[] = new int[2], lever[] = new int[2];
    int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] maps) {
        int start[] = new int[2];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (maps[i].charAt(j) == 'E') {
                    end[0] = i;
                    end[1] = j;
                } else if (maps[i].charAt(j) == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }
        
        int leverCnt = bfs(start, lever, maps); // 레버까지의 최단 거리
        int endCnt = bfs(lever, end, maps); // 출구까지의 최단 거리
        
        if (leverCnt != -1 && endCnt != -1) return leverCnt + endCnt;
        return -1; // 레버나 출구에 닿지 못한 경우
    }
    
    public int bfs(int start[], int target[], String[] maps) {

        boolean isVisited[][] = new boolean[maps.length][maps[0].length()];   
        Queue<Point> q = new ArrayDeque<>();
        
        q.add(new Point(start[0], start[1], 0));
        isVisited[start[0]][start[1]] = true;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int X = cur.x;
            int Y = cur.y;
            int cnt = cur.cnt;
            
            for (int i = 0; i < 4; i++) {
                int dx = X + deltas[i][0];
                int dy = Y + deltas[i][1];
                
                if (dx < 0 || dy < 0 || dx >= maps.length || dy >= maps[0].length() || isVisited[dx][dy] || maps[dx].charAt(dy) == 'X') continue;

                if (dx == target[0] && dy == target[1]) return cur.cnt + 1;
                else {
                    q.add(new Point(dx, dy, cnt + 1));
                    isVisited[dx][dy] = true;                    
                }
            }
        }
        // 레버나 출구 모두 닿지 못한 경우
        return -1;
    }
}