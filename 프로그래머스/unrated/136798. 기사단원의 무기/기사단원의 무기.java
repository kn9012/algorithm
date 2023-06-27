class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        int num[] = new int [number];
        
        for (int i = 1; i <= number; i++) {
            int count = 0;
            for (int j = 1; j * j <= i; j++) {
                if (j * j == i) {
                    count++;
                }
                else if (i % j == 0) count += 2;
            }
            num[i - 1] = count;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (num[i] > limit) {
                num[i] = power;
            }
            answer += num[i];
        }
        
        return answer;
    }
}