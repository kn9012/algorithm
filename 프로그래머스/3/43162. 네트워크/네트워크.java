class Solution {
    boolean isVisited[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        isVisited = new boolean[computers.length];
        
        for (int i = 0; i < computers.length; i++) {
            computers[i][i] = 0;
        }
        
        for (int i = 0; i < computers.length; i++) {
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