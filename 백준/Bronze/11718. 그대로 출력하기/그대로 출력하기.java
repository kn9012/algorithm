import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str;

        while((str = bfr.readLine()) != null) {
            sb.append(str).append('\n');
        }

        System.out.print(sb);

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}