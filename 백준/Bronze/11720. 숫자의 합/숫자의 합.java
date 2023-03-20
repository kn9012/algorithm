import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bfr.readLine());
        String str = bfr.readLine();
        String arr[] = str.split("");
        int sum = 0;

        for(int i = 0; i < N; i++) {
            sum += Integer.parseInt(arr[i]);
        }

        bfw.write(String.valueOf(sum));
        bfw.flush();
        bfw.close();
        bfr.close();
    }
}