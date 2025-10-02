class Solution {
    private static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        // n >= 1 이라고 가정
        int[] dp1 = new int[n]; // 마름모로 끝나는 경우
        int[] dp2 = new int[n]; // 마름모가 아닌 경우

        dp1[0] = 1;
        dp2[0] = 2 + tops[0]; // tops[0]==0 -> 2, tops[0]==1 -> 3

        for (int i = 1; i < n; i++) {
            dp1[i] = (dp1[i - 1] + dp2[i - 1]) % MOD;
            long term1 = (long) dp1[i - 1] * (1 + tops[i]);
            long term2 = (long) dp2[i - 1] * (2 + tops[i]);
            dp2[i] = (int) ((term1 + term2) % MOD);
        }

        return (dp1[n - 1] + dp2[n - 1]) % MOD;
    }
}
