/**
 * 프로그래머스 합승 택시 요금
 * - 다익스트라!
 */

import java.util.*;

class Solution {
    List<Node> list[];
    int len;
    
    public class Node implements Comparable<Node> {
        int node, value;
        
        Node(int node, int value) {
            this.node = node;
            this.value = value;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        len = n;
        
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int value = fares[i][2];
            
            list[start].add(new Node(end, value));
            list[end].add(new Node(start, value));
        }
        
        int[] together = dijstra(s);
        int[] aloneA = dijstra(a);
        int[] aloneB = dijstra(b);
        
        for (int i = 1; i <= n; i++) {
            if (together[i] == Integer.MAX_VALUE || aloneA[i] == Integer.MAX_VALUE || aloneB[i] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, together[i] + aloneA[i] + aloneB[i]);
        }
        
        return answer;
    }
    
    public int[] dijstra(int s) {
        int[] dist = new int[len + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, 0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int node = cur.node;
            int value = cur.value;
            
            if (dist[node] < value) continue;
            
            for (int i = 0; i < list[node].size(); i++) {
                Node next = list[node].get(i);
                
                if (dist[next.node] > value + next.value) {
                    queue.add(new Node(next.node, value + next.value));
                    dist[next.node] = value + next.value;
                }
            }
        }
        
        return dist;
    }
}