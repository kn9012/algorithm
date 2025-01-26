import java.util.*;

/**
 * 프로그래머스 호텔 대실
 * - 시작 시간을 기준으로 배열 오름차순한 뒤, 우선순위 큐에 끝나는 시간을 넣으면서 비교
 */

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0]));
        
        for (String times[] : book_time) {
            int start = changeMin(times[0]);
            int end = changeMin(times[1]) + 10;
            
            if (!queue.isEmpty() && queue.peek() <= start) {
                queue.poll();
            }
            
            queue.add(end);
        }
        
        return queue.size();
    }
    
    public int changeMin(String time) {
        String t[] = time.split(":");
        int hour = Integer.parseInt(t[0]) * 60;
        int min = Integer.parseInt(t[1]);
        
        return hour + min;
    }
}
