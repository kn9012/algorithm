import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 완탐 무조건 터짐
 * a, b 모두 정렬 후 비교
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int t = 0; t < T; t++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] a = new int[n];
            int[] b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b[j] = Integer.parseInt(st.nextToken());
            }

            // 정렬
            Arrays.sort(a);
            Arrays.sort(b);

            int count = 0;

            // 풀이 1) 비교 (1088ms)
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (a[i] <= b[j]) break;
//                    else count++;
//                }
//            }

            // 풀이 2) 이분탐색 (340ms)
            // a[i]에 대해 a[i]보다 작은 b 값들 찾기
//            for (int i = 0; i < n; i++) {
//                int left = 0, right = m - 1;
//                int idx = -1;
//
//                while (left <= right) {
//                    int mid = left + (right - left) / 2;
//
//                    if (b[mid] < a[i]) {
//                        idx = mid;
//                        left = mid + 1;
//                    } else {
//                        right = mid - 1;
//                    }
//                }
//
//                count += (idx + 1);  // 0 ~ idx 까지 개수
//            }

            // 풀이 3) 투 포인터 (ms)
            int j = 0;
            for (int i = 0; i < n; i++) {
                while (j < m && b[j] < a[i]) {
                    j++;
                }

                count += j;
            }

            System.out.println(count);
        }
    }
}