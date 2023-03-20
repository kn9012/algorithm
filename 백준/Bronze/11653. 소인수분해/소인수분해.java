import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bfr.readLine());
        int i = 2;

        if (N != 1) {
            while (i <= N) {
                if (N % i == 0) {
                    bfw.write(i + "\n");
                    N /= i;
                    i = 2;
                }
                else {
                    i++;
                }
            }

        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}