import java.io.*;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        int dDay = (V - A) / (A - B) + 1;

        if((V - A) % (A - B) != 0) {
            dDay++;
        }

        bfw.write(String.valueOf(dDay));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}