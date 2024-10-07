/**
 * 프로그래머스 당구 연습
 * - 빡구현
 * - startX와 x, startY와 y의 차이 중 적은 것을 골라 가장 가까운 벽을 골라서 피타고라소 ㄱㄱ
 */

class Solution {
    public double[] solution(int m, int n, int startX, int startY, int[][] balls) {
        double[] answer = new double[balls.length];
        
        for (int i = 0; i < balls.length; i++) {
            int X = balls[i][0];
            int Y = balls[i][1];
            
            if (startX == X) {
                int d1 = (Y - startY) * (Y - startY) + 4 * startX * startX;
                int d2 = (Y - startY) * (Y - startY) + 4 * (m - startX) * (m - startX);
                int d3 = 0;
                if (startY < Y) d3 = (startY + Y) * (startY + Y);
                else d3 = (2 * n - startY - Y) * (2 * n - startY - Y);
                answer[i] = Math.min(d1, Math.min(d2, d3));
            } else if (startY == Y) {
                int d1 = (X - startX) * (X - startX) + 4 * startY * startY;
                int d2 = (X - startX) * (X - startX) + 4 * (n - startY) * (n - startY);
                
                int d3 = 0;
                if (startX < X) d3 = (startX + X) * (startX + X);
                else d3 = (2 * m - startX - X) * (2 * m - startX - X);
                answer[i] = Math.min(d1, Math.min(d2, d3));
            } else {
                int d1 = (X - startX) * (X - startX) + (Y + startY) * (Y + startY);
                int d2 = (X + startX) * (X + startX) + (Y - startY) * (Y - startY);
                int d3 = (Y - startY) * (Y - startY) + (2 * m - startX - X) * (2 * m - startX - X);
                int d4 = (X - startX) * (X - startX) + (2 * n - startY - Y) * (2 * n - startY - Y);
                
                answer[i] = Math.min(d1, Math.min(d2, Math.min(d3, d4)));
            }
        }
        
        return answer;
    }
}