import javax.imageio.metadata.IIOMetadataNode;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bfr.readLine());

        String str = bfr.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        int count = 0;
        boolean decimal = false;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num > 1) {
                for (int j = 2; j <= num; j++) {
                    if (num % j == 0 && num != j) {
                        decimal = false;
                        break;
                    }
                    else {
                        decimal = true;
                    }
                }

                if (decimal) {
                    count++;
                }
            }
        }

        bfw.write(String.valueOf(count));
        bfr.close();
        bfw.flush();
        bfw.close();
    }
}