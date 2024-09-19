/**
 * 프로그래머스 더 맵게
 * PriorityQueue 사용
 */

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int food : scoville) {
            queue.add(food);
        }
        
        while (queue.peek() < K) {
            // 음식이 하나밖에 없는데 K보다 작다면 -1
            if (queue.size() == 1) {
                answer = -1;
                break;
            }
            // 가장 맵지 않은 음식이랑 두번째로 맵지 않은 음식 섞어서 새로 추가하기
            int newFood = queue.poll() + (queue.poll() * 2);            
            queue.add(newFood);
            
            answer++;
        }
        
        return answer;
    }
}