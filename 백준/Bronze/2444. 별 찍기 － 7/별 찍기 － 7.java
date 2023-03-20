import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bfr.readLine());

        for (int i = 1; i <= N - 1; i++) {
            for (int j = N - i; j > 0; j--) {
                bfw.write(" ");
            }

            for (int j = 0; j < (2 * i) - 1; j++) {
                bfw.write("*");
            }
            bfw.write("\n");
        }

        for(int i = N; i > 0; i--) {

            for (int j = N - i; j > 0; j--) {
                bfw.write(" ");
            }

            for (int j = 0; j < (2 * i) - 1; j++) {
                bfw.write("*");
            }
            bfw.write("\n");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}