import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (n > s) return new int[] {-1};
        else {
            int count = 0;
            
            while (n > 0 && s / n > 0) {
                int num = s / n--;
                answer[count++] = num;
                s -= num;
            }
        }
        
        return answer;
    }
}