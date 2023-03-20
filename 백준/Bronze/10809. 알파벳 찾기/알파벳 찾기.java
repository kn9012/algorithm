import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = bfr.readLine();
        char[] sChar = new char[S.length()];

        for (int i = 0; i < S.length(); i++) {
            sChar[i] = S.charAt(i);
        }

        String arr[] = new String[26];

        for (char ch = 97; ch <= 122; ch++) {
            arr[ch - 97] = String.valueOf(ch);
        }

        for (int i = 0; i < sChar.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equals(String.valueOf(sChar[i]))) {
                    arr[j] = String.valueOf(i);
                    break;
                }
            }
        }


        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i].charAt(0);
            if(48 > ch || ch > 57) {
                arr[i] = String.valueOf("-1");
            }
            bfw.write(arr[i] + " ");
        }

        bfw.flush();
        bfw.close();
        bfr.close();
    }
}