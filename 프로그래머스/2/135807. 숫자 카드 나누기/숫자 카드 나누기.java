class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answerA = 0;
        int answerB = 0;
        
        int a = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            a = gcd(a, arrayA[i]);
        }
        
        if (a != 1) {
            for (int i = 0; i < arrayB.length; i++) {
                if (arrayB[i] % a == 0) {
                    answerA = 0;
                    break;
                } else answerA = a;
            }
        }
        
        int b = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            b = gcd(b, arrayB[i]);
        }
        
        if (b != 1) {
            for (int i = 0; i < arrayA.length; i++) {
                if (arrayA[i] % b == 0) {
                    answerB = 0;
                    break;
                } else answerB = b;
            }
        }
        
        return Math.max(answerA, answerB);
    }
    
    public int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        else return gcd(b, r);
    }
}