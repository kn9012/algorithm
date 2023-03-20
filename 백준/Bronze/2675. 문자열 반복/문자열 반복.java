import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(bfr.readLine());
        String input;
        int R;
        String str;

        for (int i = 0; i < T; i++) {
            input = bfr.readLine();
            st = new StringTokenizer(input, " ");
            R = Integer.parseInt(st.nextToken());
            str = st.nextToken();

            for (int k = 0; k < str.length(); k++) {
                for (int j = 0; j < R; j++) {
                    bfw.write(str.charAt(k));
                }
            }
            bfw.write("\n");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}