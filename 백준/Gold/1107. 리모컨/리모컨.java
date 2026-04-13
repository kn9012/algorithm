import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        broken = new boolean[10];

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int answer = Math.abs(N - 100); // 현재 채널이 100

        // i가 999,999까지인 이유
        // 500000
        // 8
        // 0 2 3 4 6 7 8 9
        // 채널은 무한대만큼 있으며 이동하려는 채널 N보다 큰 경우에서 아래로 내려가는 경우도 고려해야 함
        for (int i = 0; i <= 999999; i++) {
            int len = canPress(i);

            if (len > 0) { // len이 0보다 큰 경우에만
                int move = Math.abs(N - i); // N까지 +나 -로 몇번 움직여야 하는지
                answer = Math.min(answer, len + move);
            }
        }

        System.out.println(answer);
    }

    /**
     * 해당 버튼을 누를 수 있는지 판단하는 메서드
     * 누를 수 없는 경우 0 반환, 누를 수 있는 경우 횟수 반환
     * @param num
     * @return
     */
    static int canPress(int num) {
        // 1
        // 9
        // 1 2 3 4 5 6 7 8 9
        // while문이 0보다 클 경우에만 진행되기 때문에 num이 0인 경우는 따로 체크
        if (num == 0) return broken[0] ? 0 : 1;

        int len = 0;

        while (num > 0) {
            if (broken[num % 10]) return 0;
            len++;
            num /= 10;
        }

        return len;
    }
}
