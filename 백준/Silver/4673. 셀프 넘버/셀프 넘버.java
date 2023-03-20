import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = new int[10001];
        arr[0] = 0;

        for (int i = 1; i <= 10000; i++) {
            arr[i] = i;
        }

        for (int i = 1; i <= 10000; i++) {
            int index = 0;

            if (i > 0 && i < 10) {
                index = i + i;
                if (index <= 1000) {
                    arr[index] = 0;
                }
            }

            else if (i >= 10 && i < 100) {
                index = i + (i / 10) + (i % 10);
                if (index <= 10000) {
                    arr[index] = 0;
                }
            }

            else if (i >= 100 && i < 1000) {
                index = i + (i / 100) + (i % 100 / 10) + (i % 100 % 10);
                if (index <= 10000) {
                    arr[index] = 0;
                }
            }

            else if (i >= 1000 && i <= 10000) {
                index = i + (i / 1000) + (i % 1000 / 100) + (i % 1000 % 100 / 10) + (i % 1000 % 100 % 10);
                if (index <= 10000) {
                    arr[index] = 0;
                }
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (arr[i] != 0) {
                bfw.write(String.valueOf(arr[i] + "\n"));
            }
        }

        bfw.flush();
        bfw.close();
    }
}