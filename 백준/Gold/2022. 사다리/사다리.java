import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * c = (sqrt(x*x - w*w) * sqrt(y*y - w*w)) / (sqrt(x*x - w*w) + sqrt(y*y - w*w))
 * 이분탐색으로 w 찾기
 */

public class Main {
    static double x, y, c;

    /**
     * h1 = sqrt(x*x - w*w)
     * h2 = sqrt(y*y - w*w)
     */

    static double calc(double mid) {
        double h1 = Math.sqrt(x * x - mid * mid);
        double h2 = Math.sqrt(y * y - mid * mid);
        return (h1 * h2) / (h1 + h2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double left = 0;
        double right = Math.min(x, y);

        // 실수 이분탐색은 100번 반복하면 답이 나옴
        //
        for (int i = 0; i < 100; i++) {
            double mid = left + (right - left) / 2;

            if (calc(mid) > c) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.printf("%.3f", left);
    }
}
