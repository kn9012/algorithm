import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = bfr.readLine();
        int i = Integer.parseInt(bfr.readLine());

        String arr[] = S.split("");

        bfw.write(arr[i - 1]);

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}
