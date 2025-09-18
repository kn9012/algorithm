/**
 * 프로그래머스 멀리 뛰기
 * - dp
 */

class Solution {
    public long solution(int n) {
        // dp[도달한 거리] = 도달할 수 있는 경우의 수
        long[] dp = new long[n + 2];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            dp[i] += (dp[i - 1] + dp[i - 2]) % 1234567;
        }
        
        
        return dp[n];
    }
}