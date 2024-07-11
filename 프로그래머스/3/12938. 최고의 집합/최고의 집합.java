/**
 * 프로그래머스 최고의 집합
 */

import java.util.*;
class Solution {
    public int[] solution (int n, int s) {
        if (n > s) return new int[] {-1};
        
        int [] answer = new int[n];
        int idx = 0;
        
        while (n > 0) {
            int value = s / n;
            answer[idx++] = value;
            s -= value;
            n--;
        }
        return answer;
    }
}