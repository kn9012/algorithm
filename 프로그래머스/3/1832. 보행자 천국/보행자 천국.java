class Solution {
    int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m + 1][n + 1][2];
        
        dp[0][0][0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) { // 자동차가 자유롭게 지나갈 수 있음
                    dp[i + 1][j][0] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i][j + 1][1] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                } else if (cityMap[i][j] == 2) { // 좌회전이나 우회전 금지
                    dp[i + 1][j][0] += dp[i][j][0] % MOD;
                    dp[i][j + 1][1] += dp[i][j][1] % MOD;
                }
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}