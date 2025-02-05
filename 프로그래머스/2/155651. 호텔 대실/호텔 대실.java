/**
 * 프로그래머스 호텔 대실
 * - 시작 시간을 기준으로 오름차순 정렬
 * - 
 */

import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0]));
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (String books[] : book_time) {
            int start = changeToMinute(books[0]);
            int end = changeToMinute(books[1]) + 10;
            
            if (!queue.isEmpty() && queue.peek() <= start) {
                queue.poll();
            }
            
            queue.add(end);
        }
        
        return queue.size();
    }
    
    public int changeToMinute (String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        
        return hour * 60 + minute;
    }
}