/**
 * 프로그래머스 전력망을 둘로 나누기
 * - DFS, 인접행렬 사용
 */

class Solution {
    static boolean[] isVisited;
    static int arr[][];
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        arr = new int[n + 1][n + 1];
        
        for (int i = 0; i < wires.length; i++) {
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }
               
        for (int i = 0; i < wires.length; i++) {
            // 전선 임시로 끊기
            arr[wires[i][0]][wires[i][1]] = 0;
            arr[wires[i][1]][wires[i][0]] = 0;
            
            isVisited = new boolean[n + 1];
            
            int cnt = dfs(n, 1);
            // cnt + (n - cnt) = n이므로 차이는 cnt - (n - cnt)
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);
            
            // 전선 다시 연결
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }
        
        return answer;
    }
    
    public int dfs(int n, int num) {
        isVisited[num] = true;
        int count = 1;
        
        for (int i = 1; i <= n; i++) {
            if ((arr[i][num] == 1 || arr[num][i] == 1) && !isVisited[i]) {
                count += dfs(n, i);
            }
        }
        
        return count;
    }
}