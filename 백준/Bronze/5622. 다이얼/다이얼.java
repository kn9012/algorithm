import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        char ch[] = new char[str.length()];

        for(int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }

        char alpa[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int timeList[] = {3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 10, 10, 10, 10};

        int time = 0;

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < ch.length; j++) {
                if(ch[j] == alpa[i]) {
                    time += timeList[i];
                }
            }
        }

        bfw.write(String.valueOf(time));

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}