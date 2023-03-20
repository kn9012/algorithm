import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str = bfr.readLine();
        st = new StringTokenizer(str, " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int newA = (a % 100 % 10) * 100 + (a % 100 / 10) * 10 + (a / 100);
        int newB = (b % 100 % 10) * 100 + (b % 100 / 10) * 10 + (b / 100);

        if(newA > newB) {
            bfw.write(String.valueOf(newA));
        }
        else {
            bfw.write(String.valueOf(newB));
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}