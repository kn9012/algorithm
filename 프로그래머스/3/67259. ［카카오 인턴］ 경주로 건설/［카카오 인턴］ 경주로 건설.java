/**
 * 프로그래머스 경주로 건설
 * - 다익스트라인데 비용 계산은 어떻게 할지?
 */

import java.util.*;

class Solution {
    static int deltas[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    class Node implements Comparable<Node> {
        int x, y, value, direction;
        
        Node (int x, int y, int value, int direction) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.direction = direction;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.value, other.value);
        }
    }
    
    public int solution(int[][] board) {
        int len = board.length;
        int dist[][][] = new int[len][len][4];
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < 4; k++) {
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        dist[0][0][0] = 0;
        dist[0][0][1] = 0;
        dist[0][0][2] = 0;
        dist[0][0][3] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, 0, 0, -1));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int X = cur.x;
            int Y = cur.y;
            int val = cur.value;
            int dir = cur.direction;
            
            for (int i = 0; i < 4; i++) {
                int dx = X + deltas[i][0];
                int dy = Y + deltas[i][1];
                
                if (dx < 0 || dy < 0 || dx >= len || dy >= len || board[dx][dy] == 1) continue;
                
                int cost = val;
                if (dir != i) cost += 600;
                else cost += 100;
                
                if (dist[dx][dy][i] > cost) {
                    queue.add(new Node(dx, dy, cost, i));
                    dist[dx][dy][i] = cost;
                }
            }
        }
        
        int answer = Math.min(Math.min(Math.min(dist[len - 1][len - 1][0], dist[len - 1][len - 1][1]), dist[len - 1][len - 1][2]), dist[len - 1][len - 1][3]);
        
        return answer - 500;
    }
}