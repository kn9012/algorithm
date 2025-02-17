/**
 * 프로그래머스 합승 택시 요금
 * - 다익스트라
 */

import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int node, value;
        
        Node (int node, int value) {
            this.node = node;
            this.value = value;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.value, other.value);
        }
    }
    
    List<Node> list[];
    int[] dist;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < fares.length; i++) {
            list[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
            list[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
        }
        
        int[] together = dijkstra(s, n);
        int[] aloneA = dijkstra(a, n);
        int[] aloneB = dijkstra(b, n);
        
        int charge = Integer.MAX_VALUE;
        
        for (int i = 1; i <= n; i++) {
            if (together[i] == Integer.MAX_VALUE || aloneA[i] == Integer.MAX_VALUE || aloneB[i] == Integer.MAX_VALUE) continue;
                
            charge = Math.min(charge, together[i] + aloneA[i] + aloneB[i]);
        }
        
        return charge;
    }
    
    public int[] dijkstra(int s, int n) {
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(new Node(s, 0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int node = cur.node;
            int value = cur.value;
            
            if (dist[node] < value) continue;
            
            for (int i = 0; i < list[node].size(); i++) {
                int nextN = list[node].get(i).node;
                int nextV = list[node].get(i).value;
                
                if (dist[nextN] > value + nextV) {
                    queue.add(new Node(nextN, value + nextV));
                    dist[nextN] = value + nextV;
                }
            }
        }
        
        return dist;
    }
}