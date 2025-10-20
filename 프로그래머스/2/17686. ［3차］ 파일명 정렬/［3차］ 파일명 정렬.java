import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[][] parts = new String[files.length][3];
        
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            int len = file.length();
            int idx = 0;

            // 1️⃣ HEAD
            int j = 0;
            while (j < len && !Character.isDigit(file.charAt(j))) j++;
            parts[i][0] = file.substring(0, j);

            // 2️⃣ NUMBER (최대 5자리)
            int k = j;
            while (k < len && Character.isDigit(file.charAt(k)) && k - j < 5) k++;
            parts[i][1] = file.substring(j, k);

            // 3️⃣ TAIL
            parts[i][2] = file.substring(k);
        }

        // 🔽 정렬 기준: HEAD(대소문자 무시) → NUMBER(숫자)
        Arrays.sort(parts, (a, b) -> {
            int cmp = a[0].compareToIgnoreCase(b[0]);
            if (cmp != 0) return cmp;
            return Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
        });

        // 🔄 다시 합치기
        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                if (parts[i][j] != null) sb.append(parts[i][j]);
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}
