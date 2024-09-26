import java.util.*;

class Solution {
    static boolean isVisited[];
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<List<Integer>> list = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        //int[][] arr = new int[n + 1][n + 1];
        
        for (int i = 0; i < roads.length; i++) {
            int num1 = roads[i][0];
            int num2 = roads[i][1];
            
            list.get(num1).add(num2);
            list.get(num2).add(num1);
        }
        
        for (int i = 0; i < sources.length; i++) {
            isVisited = new boolean[n + 1];
            answer[i] = bfs(n, sources[i], destination, list, isVisited);
        }
        
        return answer;
    }
    
    public static int bfs(int n, int num, int destination, List<List<Integer>> list, boolean[] isVisited) {
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[] {num, 0});
        isVisited[num] = true;
        
        while (!queue.isEmpty()) {
            int cur[] = queue.poll();
            int curN = cur[0];
            int curC = cur[1];
            
            if (curN == destination) return curC;
            
            for (int i = 0; i < list.get(curN).size(); i++) {
                int next = list.get(curN).get(i);
                
                if (isVisited[next]) continue;
                
                queue.add(new int[] {next, curC + 1});
                isVisited[next] = true;
            }
        }
        
        return -1;
    }
}