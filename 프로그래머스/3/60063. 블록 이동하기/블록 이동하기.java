/**
 * 프로그래머스 블록 이동하기
 * (N, N)까지 이동하는 최소 시간 구하기 -> BFS
 *
 * 처음 풀이 : 이동, 회전하는 경우 모두 pq에 넣었더니 시간 초과 발생
 */

import java.util.*;

class Solution {
    static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    static class Robot implements Comparable<Robot> {
        int x1, y1, x2, y2;
        int time;
        
        Robot(int x1, int y1, int x2, int y2, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
        }
        
        @Override
        public int compareTo(Robot o) {
            return Integer.compare(this.time, o.time);
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        int answer = 0;
        
        Queue<Robot> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[n][n][n][n];
        
        q.offer(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;
       
        while (!q.isEmpty()) {
            Robot cur = q.poll();
            
            int x1 = cur.x1;
            int y1 = cur.y1;
            
            int x2 = cur.x2;
            int y2 = cur.y2;
            
            int time = cur.time;
            
            if ((x1 == n - 1 && y1 == n - 1) || (x2 == n -1 && y2 == n - 1)) {
                answer = time;
                break;
            }
            
            // 상하좌우 네 방향 모두 탐색
            for (int i = 0; i < 4; i++) {
                int dx1 = x1 + deltas[i][0];
                int dy1 = y1 + deltas[i][1];
                int dx2 = x2 + deltas[i][0];
                int dy2 = y2 + deltas[i][1];

                // 범위 체크 + 벽 체크
                if (dx1 < 0 || dy1 < 0 || dx2 < 0 || dy2 < 0 ||
                    dx1 >= n || dy1 >= n || dx2 >= n || dy2 >= n) continue;
                if (board[dx1][dy1] == 1 || board[dx2][dy2] == 1) continue;

                // 방문 여부 확인
                if (visited[dx1][dy1][dx2][dy2]) continue;

                // 이동 가능 → 큐에 추가
                visited[dx1][dy1][dx2][dy2] = true;
                q.offer(new Robot(dx1, dy1, dx2, dy2, time + 1));
            }
            
            // 가로인 경우 (x1 == x2)
            if (x1 == x2) {
                // 위쪽 회전
                if (x1 - 1 >= 0 && board[x1 - 1][y1] == 0 && board[x2 - 1][y2] == 0) {
                    if (!visited[x1 - 1][y1][x1][y1]) {
                        visited[x1 - 1][y1][x1][y1] = true;
                        q.offer(new Robot(x1 - 1, y1, x1, y1, time + 1));
                    }
                    if (!visited[x2 - 1][y2][x2][y2]) {
                        visited[x2 - 1][y2][x2][y2] = true;
                        q.offer(new Robot(x2 - 1, y2, x2, y2, time + 1));
                    }
                }

                // 아래쪽 회전
                if (x1 + 1 < n && board[x1 + 1][y1] == 0 && board[x2 + 1][y2] == 0) {
                    if (!visited[x1][y1][x1 + 1][y1]) {
                        visited[x1][y1][x1 + 1][y1] = true;
                        q.offer(new Robot(x1, y1, x1 + 1, y1, time + 1));
                    }
                    if (!visited[x2][y2][x2 + 1][y2]) {
                        visited[x2][y2][x2 + 1][y2] = true;
                        q.offer(new Robot(x2, y2, x2 + 1, y2, time + 1));
                    }
                }
            }
            // 세로인 경우 (y1 == y2)
            else if (y1 == y2) {
                // 왼쪽 회전
                if (y1 - 1 >= 0 && board[x1][y1 - 1] == 0 && board[x2][y2 - 1] == 0) {
                    if (!visited[x1][y1 - 1][x1][y1]) {
                        visited[x1][y1 - 1][x1][y1] = true;
                        q.offer(new Robot(x1, y1 - 1, x1, y1, time + 1));
                    }
                    if (!visited[x2][y2 - 1][x2][y2]) {
                        visited[x2][y2 - 1][x2][y2] = true;
                        q.offer(new Robot(x2, y2 - 1, x2, y2, time + 1));
                    }
                }

                // 오른쪽 회전
                if (y1 + 1 < n && board[x1][y1 + 1] == 0 && board[x2][y2 + 1] == 0) {
                    if (!visited[x1][y1][x1][y1 + 1]) {
                        visited[x1][y1][x1][y1 + 1] = true;
                        q.offer(new Robot(x1, y1, x1, y1 + 1, time + 1));
                    }
                    if (!visited[x2][y2][x2][y2 + 1]) {
                        visited[x2][y2][x2][y2 + 1] = true;
                        q.offer(new Robot(x2, y2, x2, y2 + 1, time + 1));
                    }
                }
            }
        }
        
        return answer;
    }
}