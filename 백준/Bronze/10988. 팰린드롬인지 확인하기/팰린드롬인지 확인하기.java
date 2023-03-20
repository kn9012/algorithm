import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        char ch[] = new char[str.length()];
        boolean palindrome = false;

        for (int i = 0; i < ch.length; i++) {
            ch[i] = str.charAt(i);
        }


        for (int i = 0; i < ch.length / 2; i++) {
            if (ch[i] == ch[ch.length - i - 1]) {
                palindrome = true;
            } else {
                palindrome = false;
                break;
            }
        }

        if (ch.length == 1) {
            palindrome = true;
        }

        if (palindrome) {
            bfw.write("1");
        } else {
            bfw.write("0");
        }


        bfr.close();
        bfw.flush();
        bfw.close();
    }
}