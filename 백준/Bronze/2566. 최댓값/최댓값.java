import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arr[][] = new int[9][9];
        int max = -1;
        int indexI = 0;
        int indexJ = 0;

        for(int i = 0; i < 9; i++) {
            String str = bfr.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            for(int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(max < arr[i][j]) {
                    max = arr[i][j];
                    indexI = i + 1;
                    indexJ = j + 1;
                }
            }
        }

        bfw.write(String.valueOf(max + "\n" + indexI + " " + indexJ));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}