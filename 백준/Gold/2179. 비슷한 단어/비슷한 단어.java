import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int maxLen = -1;
        int first = 0, second = 1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int len = commonPrefixLength(words[i], words[j]);
                if (len > maxLen) {
                    maxLen = len;
                    first = i;
                    second = j;
                } else if (len == maxLen) {
                    // 입력 순서상 더 먼저 나온 쌍을 선택
                    if (i < first || (i == first && j < second)) {
                        first = i;
                        second = j;
                    }
                }
            }
        }

        System.out.println(words[first]);
        System.out.println(words[second]);
    }

    static int commonPrefixLength(String a, String b) {
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return len;
    }
}
