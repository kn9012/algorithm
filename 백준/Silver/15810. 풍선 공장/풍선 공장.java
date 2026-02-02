import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());
        long maxStaff = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            maxStaff = Math.max(maxStaff, a[i]);
        }

        long left = 0;
        long right = maxStaff * m;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += mid / a[i];
            }

            if (sum >= m) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}
