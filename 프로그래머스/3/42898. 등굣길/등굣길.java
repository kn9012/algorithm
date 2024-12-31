import java.util.*;
class Solution {
    static final int FIX = 1000000007;
    static final int PUDDLE = Integer.MAX_VALUE;
    public int solution(int m, int n, int[][] puddles) {
        int [][] DP = new int [n][m];
        for(int i = 0; i < puddles.length; i++){
            DP[puddles[i][1] - 1][puddles[i][0] - 1] = PUDDLE;
        }
        DP[0][0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(DP[i][j] == PUDDLE) continue;
                // 위에서 
                if(i - 1 >= 0 && DP[i-1][j] != PUDDLE){
                    DP[i][j] += DP[i-1][j];
                }
                // 왼쪽에서 
                if(j - 1 >= 0 && DP[i][j-1] != PUDDLE){
                    DP[i][j] += DP[i][j-1];
                }
                DP[i][j] %= FIX;
            }
        }
        int answer = DP[n-1][m-1] % FIX;
        return answer;
    }
}