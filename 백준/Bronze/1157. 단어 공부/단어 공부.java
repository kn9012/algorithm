import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine().toUpperCase();

        char ch[] = new char[str.length()];
        char alpa[] = new char[26];
        char aschi = 65;

        int count[] = new int[str.length()];
        int alpaCount[] = new int[26];
        int max = 0;
        int maxIn = 0;
        boolean same = false;

        for (int i = 0; i < ch.length; i++) {
            ch[i] = str.charAt(i);
        }

        for(int i = 0; i < alpa.length; i++) {
            alpa[i] = aschi;
            aschi++;
        }

        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < alpa.length; j++) {
                if (ch[i] == alpa[j]) {
                    alpaCount[j]++;
                    break;
                }
            }
        }

        for (int i = 0; i < alpaCount.length; i++) {
            if (max < alpaCount[i]) {
                max = alpaCount[i];
                maxIn = i;
                same = false;
            }
            else if (max == alpaCount[i]) {
                same = true;
            }
        }

        if (same) {
            bfw.write("?");
        } else {
            bfw.write(alpa[maxIn]);
        }

        bfr.close();
        bfw.flush();
        bfw.close();
    }
}