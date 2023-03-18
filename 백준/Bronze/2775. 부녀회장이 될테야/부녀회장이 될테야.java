import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bfr.readLine());
        int floor[][] = new int[15][15];

        for(int i = 0; i <= 14; i++) {
            for(int j = 1; j <= 14; j++) {
                if(i == 0) {
                    floor[i][j] = j;
                }
                else {
                    for(int s = 1; s <= j; s++) {
                        floor[i][j] += floor[i-1][s];
                    }
                }
            }
        }

        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(bfr.readLine());
            int n = Integer.parseInt(bfr.readLine());

            bfw.write(floor[k][n] + "\n");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}