import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 예약 시간을 시작 시간 기준으로 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        // 우선순위 큐를 사용하여 끝나는 시간을 관리 (가장 빨리 끝나는 시간이 우선)
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();

        for (String[] time : book_time) {
            // 현재 예약의 시작 시간
            int start = convertToMinutes(time[0]);
            int end = convertToMinutes(time[1]) + 10; // 청소 시간 포함

            // 현재 예약 시작 시간이 가장 빨리 끝나는 방의 종료 시간 이후라면, 방 재사용 가능
            if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= start) {
                roomEndTimes.poll(); // 기존 방을 제거
            }

            // 새로운 방 사용 (혹은 기존 방 갱신)
            roomEndTimes.add(end);
        }

        // 큐에 남아있는 방 개수가 최소 방 개수
        return roomEndTimes.size();
    }

    // 시간을 분으로 변환하는 함수
    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
