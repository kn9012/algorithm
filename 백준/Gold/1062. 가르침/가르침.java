import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * a, n, t, i, c는 항상 가르쳐야 하므로 K에서 5 빼기
 */

public class Main {
    static int N, K;
    static int[] words;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new int[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int bit = 0;

            for (char c : s.toCharArray()) {
                // << : 왼쪽 시프트 연산자
                // |= : 비트 OR 연산자
                // 한 단어의 알파벳 집합을 bit에 저장
                bit |= (1 << (c - 'a'));
            }

            words[i] = bit;
        }

        // a, n, t, i, c는 무조건 배워야 함
        if (K < 5) {
            System.out.println(0);
            return;
        }

        K -= 5;

        int learn = 0;
        learn |= (1 << ('a' - 'a'));
        learn |= (1 << ('n' - 'a'));
        learn |= (1 << ('t' - 'a'));
        learn |= (1 << ('i' - 'a'));
        learn |= (1 << ('c' - 'a'));

        dfs(0, 0, learn); // a, n, t, i, c가 포함된 비트

        System.out.println(answer);
    }

    static void dfs(int idx, int count, int learn) {
        if (count == K) {
            int readable = 0;

            for (int word : words) {
                // work에 있는 알파벳이 모두 learn에 포함된다면
                if ((word & learn) == word) {
                    readable++;
                }
            }

            answer = Math.max(answer, readable);
            return;
        }

        for (int i = idx; i < 26; i++) {
            // 이미 배운 문자면 스킵
            if ((learn & (1 << i)) != 0) continue;

            // 아직 배우지 않은 문자라면 탐색
            dfs(i + 1, count + 1, learn | (1 << i));
        }
    }
}
