import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 반의 개수
        int M = Integer.parseInt(st.nextToken()); // 각 반의 학생 수
        
        int[][] students = new int[N][M];

        // 반별 학생 입력 및 정렬
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(students[i]); // 각 반 정렬
        }

        // 우선순위 큐 (MinHeap): (값, 반 번호, 인덱스)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int maxValue = Integer.MIN_VALUE; // 현재 그룹에서 최댓값

        // 각 반의 첫 번째 학생을 우선순위 큐에 삽입
        for (int i = 0; i < N; i++) {
            pq.offer(new int[]{students[i][0], i, 0});
            maxValue = Math.max(maxValue, students[i][0]); // 현재 그룹에서 최대값 갱신
        }

        int minDiff = Integer.MAX_VALUE; // 최소 차이

        // 투 포인터 탐색 시작
        while (!pq.isEmpty()) {
            int[] minStudent = pq.poll(); // 가장 작은 값을 가진 학생 (최솟값)
            int minValue = minStudent[0]; // 현재 그룹의 최솟값
            int classIdx = minStudent[1]; // 반 번호
            int idx = minStudent[2]; // 해당 반에서의 인덱스

            // 현재 선택된 선수들의 차이 갱신
            minDiff = Math.min(minDiff, maxValue - minValue);

            // 해당 반에서 다음 학생이 없다면 종료
            if (idx + 1 == M) break;

            // 다음 학생을 우선순위 큐에 추가
            int nextValue = students[classIdx][idx + 1];
            pq.offer(new int[]{nextValue, classIdx, idx + 1});
            maxValue = Math.max(maxValue, nextValue); // 최대값 갱신
        }

        System.out.println(minDiff);
    }
}
