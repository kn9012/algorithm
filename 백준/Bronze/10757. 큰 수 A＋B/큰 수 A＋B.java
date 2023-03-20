import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        BigInteger A = new BigInteger(st.nextToken());
        BigInteger B = new BigInteger(st.nextToken());

        bfw.write(String.valueOf(A.add(B)));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}
