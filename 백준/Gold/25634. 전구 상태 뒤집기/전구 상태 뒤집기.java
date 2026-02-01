import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] bulb = new int[n];
        int[] brightness = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            bulb[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            brightness[i] = Integer.parseInt(st.nextToken());

            if (brightness[i] == 1) sum += bulb[i];
        }

        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int gain = (brightness[i] == 0) ? bulb[i] : -bulb[i];
            cur = Math.max(gain, cur + gain);
            max = Math.max(max, cur);
        }

        System.out.println(sum + max);
    }
}
