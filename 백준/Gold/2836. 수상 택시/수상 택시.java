import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 역주행 구간을 나타내는 클래스 (선분)
    static class Ride implements Comparable<Ride> {
        int start; // 구간의 시작점 (입력에서의 도착지 v)
        int end;   // 구간의 끝점 (입력에서의 출발지 u)

        public Ride(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 스위핑을 위해 시작점을 기준으로 정렬
        @Override
        public int compareTo(Ride other) {
            if (this.start == other.start) {
                return Integer.compare(this.end, other.end);
            }
            return Integer.compare(this.start, other.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        List<Ride> reverseRides = new ArrayList<>();

        // 1. 역주행 구간 추출
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (u > v) {
                reverseRides.add(new Ride(v, u));
            }
        }

        if (reverseRides.isEmpty()) {
            System.out.println(M);
            return;
        }

        // 2. 구간 정렬
        Collections.sort(reverseRides);

        long totalReverseLength = 0;
        
        // 3. 구간 병합 (Sweeping)
        int currentStart = reverseRides.get(0).start;
        int currentEnd = reverseRides.get(0).end;

        for (int i = 1; i < reverseRides.size(); i++) {
            Ride nextRide = reverseRides.get(i);

            if (nextRide.start <= currentEnd) { // 구간이 겹치는 경우
                currentEnd = Math.max(currentEnd, nextRide.end); // 구간 확장
            } else { // 구간이 분리된 경우
                totalReverseLength += (long)currentEnd - currentStart; // 이전 구간 길이를 더함
                currentStart = nextRide.start; // 새 구간으로 초기화
                currentEnd = nextRide.end;
            }
        }
        
        // 마지막으로 처리된 구간의 길이를 더함
        totalReverseLength += (long)currentEnd - currentStart;

        // 4. 최종 거리 계산
        long result = M + (totalReverseLength * 2);
        System.out.println(result);
    }
}