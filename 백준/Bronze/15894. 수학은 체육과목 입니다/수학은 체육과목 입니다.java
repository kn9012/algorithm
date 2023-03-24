import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Integer.parseInt(bfr.readLine());

        long round = n * 4;

        bfw.write(String.valueOf(round));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}