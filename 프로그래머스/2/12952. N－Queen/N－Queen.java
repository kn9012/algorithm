/**
 * 프로그래머스 N-Queen
 *
 *
 */

class Solution {
    static class Point {
        int x, y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int answer = 0;
    int map[];
    
    public int solution(int n) {
        map = new int[n];
        dfs(0, n);
        return answer;
    }
    
    public void dfs(int count, int n) {
        if (count == n) {
            answer++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            map[count] = i;
            
            if (promising(count) == 1) dfs(count + 1, n);
        }
    }
    
    int promising(int count) {
        for (int i = 0; i < count; i++) {
            if (map[count] == map[i] || count - i == Math.abs(map[count] - map[i])) return 0;
        }
        return 1;
    }
    
}