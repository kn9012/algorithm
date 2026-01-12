import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();
        arr = new int[n];

        recur(0, stack, 1);
    }

    public static void recur(int count, Deque<Integer> stack, int next) {
        if (count == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            } System.out.println();
        }

        if (!stack.isEmpty()) {
            arr[count] = stack.pop();
            recur(count + 1, stack, next);
            stack.push(arr[count]);
        }

        if (next <= n) {
            stack.push(next);
            recur(count, stack, next + 1);
            stack.pop();
        }
    }
}
