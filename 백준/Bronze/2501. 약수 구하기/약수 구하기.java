import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int divisor;
        int count = 0;


        for (int j = 1; j <= N; j++) {
            if (N % j == 0) {
                divisor = j;
                ++count;

                if (count == K) {
                    bfw.write(String.valueOf(divisor));
                    break;
                }
            }
        }

        if (count < K) {
            bfw.write("0");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}