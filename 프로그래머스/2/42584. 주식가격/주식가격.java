/**
 * 프로그래머스 주식가격
 *
 * 아이디어
 * Stack에 주식가격 넣고 뺄때마다 바로 앞의 값과 비교하기?
 */

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            queue.add(prices[i]);
        }
        
        int index = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int i = prices.length - queue.size(); i < prices.length; i++) {
                if (cur > prices[i]) {
                    answer[index]++;
                    break;
                } else {
                    answer[index]++;
                }
            }    
            index++;
        }
        
        return answer;
    }
}