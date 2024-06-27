/**
 * 프로그래머스 이중우선순위큐
 * 
 * 아이디어 - PriorityQueue 사용
 * 기본 PriorityQueue는 오름차순(=최소값 우선순위), Colloections.reverseOrder()가 들어간 PriorityQueue는 내림차순(=최댓값 우선순위)
 * 값을 넣을때마다 두 Queue에 모두 넣어주고 값을 삭제할떄도 같이 삭제헤준다
 * 빈 큐에 데이터를 삭제하라 하면 해당 연산은 무시하기
 */

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 최솟값 우선순위
        PriorityQueue<Integer> asc_queue = new PriorityQueue<>();
        
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