import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bfr.readLine());


        bfw.write(String.valueOf(hansu(N)));

        bfr.close();
        bfw.flush();
        bfw.close();
    }

    static int hansu(int N) {

        int count = 0;

        for(int i = 1; i <= N; i++) {
            if(i < 1000 && i >= 100) {
                if((i / 100) - (i % 100 / 10) == (i % 100 / 10) - (i % 100 % 10)) {
                    count++;
                }
            }
            else if(i < 100) {
                count++;
            }
        }

        return count;
    }
}