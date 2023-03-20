import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str = bfr.readLine();
        st = new StringTokenizer(str, " ");
        long sum = 0;

        for(int i = 0; i < 3; i++) {
            sum += Long.parseLong(st.nextToken());
        }

        bfw.write(String.valueOf(sum));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}