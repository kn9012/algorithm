/**
 * 프로그래머스 네트워크
 *
 * 아이디어 - DFS
 * computers[i][i]는 0으로 변경하고 DFS로 연결된 컴퓨터 방문
*/

class Solution {
    boolean isVisited[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        isVisited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            computers[i][i] = 0;
        }
        
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                answer++;
                isVisited[i] = true;
                dfs(i, computers);
            }
        }
        
        return answer;
    }
    
    public void dfs(int num, int[][] computers) {
        for (int i = 0; i < computers.length; i++) {
            if (computers[num][i] == 1 && !isVisited[i]) {
                isVisited[i] = true;
                dfs(i, computers);
            }
        }
    } 
}