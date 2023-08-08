import java.util.Scanner;
import java.io.FileInputStream;

/**
 * @author 김유나
 * <pre>
 * SWEA 1859번 - 백만 장자 프로젝트
 * - 처음엔 배열 처음부터 끝까지 for문 돌려가며 max보다 a[i + 1] 값이 크다면
 * max를 a[i + 1]로 지정하고 continue를 사용하여 넘어가는 로직 생각
 * - but, 끝에서부터 처음으로 오면서 max 값을 지정하고 max보다 작은 값들을
 * max에서 빼고 그 값들을 더하면 최대 이득을 구할 수 있음을 구글링을 통해 알아냄
 * - for문을 처음부터 끝까지만 돌리라는 법은 없기 때문에 생각의 전환을 얻게 됨! 
 * </pre>
 */

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt(); // 테스트 케이스의 수 T 입력 받기

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt(); // 날 수를 나타내는 N 입력 받기
            int arr[] = new int[N]; // 날에 따른 매매가를 저장할 N 크기의 배열 arr 선언
            long maxProfit = 0; // 이득들을 더해 구할 최대 이득 maxProfit
            
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt(); // 배열 arr 입력 받기
            }
            
            int max = arr[N - 1]; // max는 가장 끝인 arr[N - 1]로 지정
            for (int i = N - 2; i >= 0; i--) { // max가 arr[N - 1]이므로 N - 2부터 0까지 --
                if (max > arr[i]) {  // 만약 max가 arr[i]보다 크다면,
                        maxProfit += max - arr[i]; // max와 arr[i]의 차이 maxProfit에 더하기
                    }
                    else { // 만약 max가 arr[i]보다 크지 않다면,
                        max = arr[i]; // max에 arr[i] 지정 후 0까지 max보다 큰 값들이 있는지 검증
                    }
                    
                }
        System.out.println("#" + test_case + " " + maxProfit);
    }
    }
}
