/**
 * 프로그래머스 미로 탈출 명령어
 * - 완탐 가능하면 PriorityQueue 넣어서 poll
 */

import java.util.*;

class Solution {
    int[][] deltas = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    String[] movement = {"d", "l", "r", "u"};
    
    public class Node {
        int x, y, level;
        StringBuilder route;
        
        Node (int x, int y, int level, StringBuilder route) {
            this.x = x;
            this.y = y;
            this.level = level;
            this.route = new StringBuilder(route);
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist > k || Math.abs(dist - k) % 2 == 1) return "impossible";
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y, 0, new StringBuilder()));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int dx = cur.x + deltas[i][0];
                int dy = cur.y + deltas[i][1];
                
                StringBuilder route = new StringBuilder(cur.route.toString());
                
                if (dx <= 0 || dy <= 0 || dx > n || dy > m) continue;
                
                route.append(movement[i]);
                
                if(Math.abs(dx - r) + Math.abs(dy - c) > (k - cur.route.length())) continue;
                
                if (route.length() < k) {
                    queue.add(new Node(dx, dy, cur.level + 1, new StringBuilder(route)));
                    break;
                } else {
                    if (dx == r && dy == c && cur.level + 1 == k) return route.toString();
                }
            }
        }
        
        return "impossible";
    }
}