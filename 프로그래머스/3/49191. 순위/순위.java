/**
 * 프로그래머스 순위
 * - DFS 사용
 */

import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            boolean winVisited[] = new boolean[n + 1];
            boolean loseVisited[] = new boolean[n + 1];
            
            int count = winDFS(i, results, winVisited) + loseDFS(i, results, loseVisited);
            
            if (count == n - 1) answer++;
        }
        
        return answer;
    }
    
    public int winDFS(int num, int[][] results, boolean visited[]) {
        int count = 0;
        visited[num] = true;
        
        for (int i = 0; i < results.length; i++) {
            if (results[i][0] == num && !visited[results[i][1]]) {
                count += winDFS(results[i][1], results, visited) + 1;
            }
        }
        
        return count;
    }
    
    public int loseDFS(int num, int[][] results, boolean visited[]) {
        int count = 0;
        visited[num] = true;
        
        for (int i = 0; i < results.length; i++) {
            if (results[i][1] == num && !visited[results[i][0]]) {
                count += loseDFS(results[i][0], results, visited) + 1;
            }
        }
        
        return count;
    }
}