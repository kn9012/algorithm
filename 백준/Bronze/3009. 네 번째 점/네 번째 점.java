import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int vertex[][] = new int[4][2];
        String str;

        for (int i = 0; i < 3; i++) {
            str = bfr.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            for (int j = 0; j < 2; j++) {
                vertex[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (vertex[0][0] == vertex[1][0]) {
            vertex[3][0] = vertex[2][0];
        }
        else if (vertex[1][0] == vertex[2][0]) {
            vertex[3][0] = vertex[0][0];
        }
        else {
            vertex[3][0] = vertex[1][0];
        }

        if (vertex[0][1] == vertex[1][1]) {
            vertex[3][1] = vertex[2][1];
        }
        else if (vertex[1][1] == vertex[2][1]) {
            vertex[3][1] = vertex[0][1];
        }
        else {
            vertex[3][1] = vertex[1][1];
        }

        bfw.write(vertex[3][0] + " " + vertex[3][1]);

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}