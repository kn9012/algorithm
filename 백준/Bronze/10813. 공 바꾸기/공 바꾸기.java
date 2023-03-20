import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str = bfr.readLine();
        st = new StringTokenizer(str, " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[] = new int[N + 1];

        int i, j, tmp;

        for(int s = 1; s <= N; s++) {
            arr[s] = s;
        }

        for(int s = 0; s < M; s++) {
            String as = bfr.readLine();
            StringTokenizer stt = new StringTokenizer(as, " ");
            i = Integer.parseInt(stt.nextToken());
            j = Integer.parseInt(stt.nextToken());

            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        for(int s = 1; s <= N; s++) {
            bfw.write(arr[s] + " ");
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}