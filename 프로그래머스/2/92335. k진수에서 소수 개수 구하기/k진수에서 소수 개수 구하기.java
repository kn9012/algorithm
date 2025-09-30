class Solution {
    public int solution(int n, int k) {
        // 1. n을 k진수 문자열로 변환
        String baseK = Integer.toString(n, k);

        // 2. "0"을 기준으로 split
        String[] parts = baseK.split("0");

        int count = 0;
        for (String part : parts) {
            if (part.equals("")) continue;  // 빈 문자열 제외

            long num = Long.parseLong(part); // 조각을 숫자로 변환
            if (isPrime(num)) {
                count++;
            }
        }

        return count;
    }

    // 3. 소수 판별
    private boolean isPrime(long num) {
        if (num < 2) return false;
        long limit = (long) Math.sqrt(num);
        for (long i = 2; i <= limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
