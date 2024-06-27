/**
 * 프로그래머스 이중우선순위큐
 * 
 * 아이디어
 * 
 */

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 최솟값 우선순위
        PriorityQueue<Integer> asc_queue = new PriorityQueue<Integer>();
        
        // 최댓값 우선순위
        PriorityQueue<Integer> desc_queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].charAt(0) == 'I') {
                int num = Integer.parseInt(operations[i].split(" ")[1]);
                asc_queue.add(num);
                desc_queue.add(num);
            } else if (operations[i].charAt(0) == 'D') {
                if (operations[i].split(" ")[1].equals("1") && !asc_queue.isEmpty()) {
                    int num = desc_queue.poll();
                    asc_queue.remove(num);
                } else if (operations[i].split(" ")[1].equals("-1") && !desc_queue.isEmpty()) {
                    int num = asc_queue.poll();               
                    desc_queue.remove(num);
                }
            }   
        }
        
        if (asc_queue.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = desc_queue.poll();
            answer[1] = asc_queue.poll();
        }
        return answer;
    }
}