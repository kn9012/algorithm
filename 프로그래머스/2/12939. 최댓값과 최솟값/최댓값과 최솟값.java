class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] str = s.split(" ");
        int[] arr = new int[str.length];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            
            if (max < arr[i]) max = arr[i];
            if (min > arr[i]) min = arr[i];
        }
        
        answer += min + " " + max;
        
        return answer;
    }
}