/**
 * 프로그래머스 정수 삼각형
 *
 * 아이디어
 * i는 항상 +1, j는 +0 또는 +1이므로 이 규칙을 이용해서 최댓값 찾기?
 * x : 행, y : 열
*/


class Solution {
    int answer = Integer.MIN_VALUE;
    int dp[][];
    public int solution(int[][] triangle) {
        dp = new int[triangle.length][triangle.length];
        
        for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
            dp[triangle.length - 1][i] = triangle[triangle.length - 1][i];
        }
        
        return findMax(0, 0, triangle);
        
    }
    
    public int findMax(int x, int y, int[][] triangle) {
        if (x == triangle.length) {
            return dp[x][y];
        }
        
        if (dp[x][y] == 0) {
            dp[x][y] = Math.max(findMax(x + 1, y, triangle), findMax(x + 1, y + 1, triangle)) + triangle[x][y];
        }
        return dp[x][y];
    }
}