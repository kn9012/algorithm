import java.util.*;

/**
 * 프로그래머스 무지의 먹방 라이브
 * - 시간 순서로 정렬 후 사이클로 계산
 */

class Solution {
    class Food implements Comparable<Food> {
        int index, time;
        
        Food (int index, int time) {
            this.index = index;
            this.time = time;
        }
        
        @Override
        public int compareTo(Food o) {
            return Integer.compare(this.time, o.time);
        }
    }
    
    public int solution(int[] food_times, long k) {
        PriorityQueue<Food> queue = new PriorityQueue<>();
        
        for (int i = 0; i < food_times.length; i++) {
            queue.add(new Food(i + 1, food_times[i]));
        }
        
        long sum = 0;
        long prevTime = 0;
        
        while (!queue.isEmpty()) {
            Food cur = queue.peek();
            
            long total = (long) (cur.time - prevTime) * queue.size();
            
            if (sum + total <= k) {
                sum += total;
                queue.poll();
                prevTime = cur.time;
            } else {
                k -= sum;
                break;
            }
        }
        
        if (queue.isEmpty()) return -1;
        
        List<Food> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.index, o2.index));
        
        return list.get((int) (k % list.size())).index;
    }
}