import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bfr.readLine();
        String st[] = str.split("");
        String croatia[] = {"c=", "c-", "d-", "lj", "nj", "s=", "z="};
        int count = 0;
        boolean isCroatia = false;

        for (int i = 0; i < st.length - 1; i++) {
            for (int j = 0; j < croatia.length; j++) {
                if ((st[i] + st[i + 1]).equals(croatia[j])) {
                    st[i] = "0";
                    st[i + 1] = "0";
                    count++;
                } else if ((i + 2) < st.length && (st[i] + st[i + 1] + st[i + 2]).equals("dz=")) {
                    st[i] = "0";
                    st[i + 1] = "0";
                    st[i + 2] = "0";
                    count++;
                }
            }
        }

        for (int i = 0; i < st.length; i++) {
            if(!st[i].equals("0")) {
                count++;
            }
        }

        bfw.write(String.valueOf(count));


        bfr.close();
        bfw.flush();
        bfw.close();
    }
}