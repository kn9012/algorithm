import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] book;

    public static class Student implements Comparable<Student> {
        int a, b;

        public Student(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.b, o.b);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int c = Integer.parseInt(br.readLine());

        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            book = new int[N + 2];
            for (int i = 1; i <= N + 1; i++) book[i] = i;

            Student[] students = new Student[M];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                students[i] = new Student(a, b);
            }

            Arrays.sort(students);

            int answer = 0;
            for (Student s : students) {
                int book = find(s.a);
                if (book <= s.b) {
                    union(book, book + 1);
                    answer++;
                }
            }

            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }

    public static int find(int n) {
        if (book[n] == n) return book[n];
        return book[n] = find(book[n]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            book[find(a)] = find(b);
        }
    }
}
