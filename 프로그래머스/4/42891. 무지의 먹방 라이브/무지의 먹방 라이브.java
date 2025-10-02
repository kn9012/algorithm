import java.util.*;

class Solution {
    public long solution(int[] food_times, long k) {
        // 걸리는 시간이 작은 순서대로 정렬
        // [0] : 인덱스, [1] : 걸리는 시간
        PriorityQueue<long []> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        
        int index = 1;
        // [2, 1], [3, 2], [1, 3] 순으로 정렬됨
        for (int time : food_times) {
            pq.offer(new long[] {index++, time});
        }
        
        long prevValue = 0;
        
        while (!pq.isEmpty()) {
            // 효율성 테스트에서는 pq.size()가 200,000 이하, pq.peek()이 100,000,000 이하 -> long
            long cycleSize = pq.size() * (pq.peek()[1] - prevValue);
            
            // 한 사이클의 크기보다 k가 크다면 한 사이클 빼기
            if (k >= cycleSize) {
                k -= cycleSize;
                prevValue = pq.poll()[1];
            } else break;
        }
        
        List<long []> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }
        
        Collections.sort(list, (o1, o2) -> Long.compare(o1[0], o2[0]));
        
        return (list.size() == 0 ? -1 : list.get((int) (k % list.size()))[0]);
    }
}