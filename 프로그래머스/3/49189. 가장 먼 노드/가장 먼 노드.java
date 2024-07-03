/**
 * 프로그래머스 가장 먼 노드
 * 
 * 아이디어 - DFS?
 * 
*/

import java.util.*;

class Solution {
    static int min, value[];
    static boolean isVisited[], arr[][];
    static ArrayList<Integer> list = new ArrayList<>();
    
    public class Node {
        int index, weight;
        
        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 1;
        
        arr = new boolean[n][n];
        isVisited = new boolean[n];
        
        for (int i = 0; i < edge.length; i++) {
            int num1 = edge[i][0] - 1;
            int num2 = edge[i][1] - 1;
            
            arr[num1][num2] = true;
            arr[num2][num1] = true;
        }
        
        min = Integer.MAX_VALUE;
        bfs(0);
        
        Collections.sort(list);
        int max = list.get(list.size() - 1);
        for (int i = list.size() - 2; i > 0; i--) {
            if (max == list.get(i)) answer++;
        }
        
        return answer;
    }
    
    public void bfs(int start) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        isVisited[start] = true;
        int count = 0;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int index = cur.index;
            int weight = cur.weight;
            
            for (int i = 0; i < arr.length; i++) {
                if (!isVisited[i] && arr[index][i]) {
                    queue.add(new Node(i, weight + 1));
                    isVisited[i] = true;
                    list.add(weight + 1);
                }
            }
        }
    }
}