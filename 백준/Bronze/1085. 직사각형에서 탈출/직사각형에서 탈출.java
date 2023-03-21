import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int distance[] = {x, y, Math.abs(w - x), Math.abs(h - y)};
        int min = 1001;

        for(int i = 0; i < 4; i++) {
            if(min > distance[i]) {
                min = distance[i];
            }
        }

        bfw.write(String.valueOf(min));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}