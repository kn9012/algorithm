import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, count = 0;
    static int[][] a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        b = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            for (int j = 0; j < m; j++) {
                b[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }

        if (n < 3 || m < 3) {
            System.out.println(isSame() ? 0 : -1);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != b[i][j] && i + 2 < n && j + 2 < m) {
                    reverse(i, j);
                }
            }
        }

        System.out.println(isSame() ? count : -1);
    }

    static void reverse(int x, int y) {
        count++;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (a[i][j] == 0) a[i][j] = 1;
                else a[i][j] = 0;
            }
        }
    }

    static boolean isSame() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }

        return true;
    }
}
