class Solution {
    public int solution(int n) {
        int arr[] = new int[n + 1];
        arr[1] = 1;
        
        for (int i = 0; i <= n - 2; i++) {
            arr[i + 2]= (arr[i] + arr[i + 1]) % 1234567;
        }
        return arr[n] % 1234567;
    }
}