class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        StringBuilder sb = new StringBuilder(answer);

        int xArr[] = new int[10];
        int yArr[] = new int[10];

        for (String x : X.split("")) {
            xArr[Integer.parseInt(x)]++;
        }

        for (String y : Y.split("")) {
            yArr[Integer.parseInt(y)]++;
        }

       for (int i = 9; i >= 0; i--) {
            while (xArr[i] > 0 && yArr[i] > 0) {
                sb.append(i);
                xArr[i]--;
                yArr[i]--;
            }
        }
        
        answer = sb.toString();
        
        if (answer.isEmpty()) {
            answer = "-1";
        }
        
        else if (answer.charAt(0) == '0') {
            answer = "0";
        }
    
        return answer;
    }
}