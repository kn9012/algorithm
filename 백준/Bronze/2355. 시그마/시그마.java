import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bfr.readLine(), " ");

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long A = Math.min(X, Y);
        long B = Math.max(X, Y);
        long sum = (A + B) * ((B - A + 1) / 2);

        if((B - A) % 2 == 0) {
            sum += (A + B) / 2;
        }

        bfw.write(String.valueOf(sum));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}