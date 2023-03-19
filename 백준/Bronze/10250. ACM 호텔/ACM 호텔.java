import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bfr.readLine());

        for (int i = 0; i < T; i++) {
            String str = bfr.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int yy, xx;

            if(n % h != 0) {
                yy = n % h;
                xx = n / h + 1;
            }
            else {
                yy = h;
                xx = n / h;
            }

            bfw.write(yy * 100 + xx + "\n");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}