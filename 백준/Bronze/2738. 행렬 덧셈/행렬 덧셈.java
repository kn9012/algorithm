import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int A[][] = new int[N][M];
        int B[][] = new int[N][M];
        int newArr[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            String strr = bfr.readLine();
            StringTokenizer stt = new StringTokenizer(strr, " ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            String strr = bfr.readLine();
            StringTokenizer stt = new StringTokenizer(strr, " ");
            for (int j = 0; j < M; j++) {
                B[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newArr[i][j] = A[i][j] + B[i][j];
                bfw.write(String.valueOf(newArr[i][j] + " "));
            }
            bfw.write("\n");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}