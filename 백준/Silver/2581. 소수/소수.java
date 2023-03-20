import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(bfr.readLine());
        int N = Integer.parseInt(bfr.readLine());
        int sum = 0;
        int min = 10000;
        int count = 0;
        boolean decimal = false;

        for (int i = M; i <= N; i++) {
            for (int j = 2; j <= i; j++) {
                if (i % j == 0 && i != j) {
                    decimal = false;
                    break;
                } else {
                    decimal = true;
                }
            }

            if (decimal) {
                if (min > i) {
                    min = i;
                }
                count++;
                sum += i;
            }
        }

        if (count == 0) {
            bfw.write("-1");
        } else {
            bfw.write(sum + "\n" + min);
        }


        bfr.close();
        bfw.flush();
        bfw.close();
    }
}