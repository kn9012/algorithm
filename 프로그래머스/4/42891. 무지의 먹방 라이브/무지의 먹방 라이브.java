import java.util.*;

/**
 * 프로그래머스 무지의 먹방 라이브
 * - 섭취 시간 오름차순으로 정렬 후 묶어서 계산
 */

class Solution {
    public int solution(int[] food_times, long k) {
        // 섭취 시간 오름차순으로 정렬
        PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        for (int i = 0; i < food_times.length; i++) {
            pq.add(new int[] {food_times[i], i});       
        }
        
        long prevFoodTime = 0;
        
        while (!pq.isEmpty()) {
            int[] curFood = pq.peek();
            int size = pq.size();
            
            // 한 사이클이 k보다 작다면
            // 해당 사이클 빼주고 직전 음식 시간 갱신
            if (size * (curFood[0] - prevFoodTime) <= k) {
                k -= size * (curFood[0] - prevFoodTime);
                prevFoodTime = curFood[0];
                pq.poll();
            } else {
                break;
            }
        }
        
        if (pq.isEmpty()) return -1;
        
        List<int []> list = new ArrayList<>();
        while (!pq.isEmpty()) list.add(pq.poll());
        
        Collections.sort(list, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        return list.get((int) (k % list.size()))[1] + 1;
    }
}