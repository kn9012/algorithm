import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str = bfr.readLine();
        st = new StringTokenizer(str, " ");
        int count = st.countTokens();

        bfw.write(String.valueOf(count));


        bfr.close();
        bfw.flush();
        bfw.close();
    }
}