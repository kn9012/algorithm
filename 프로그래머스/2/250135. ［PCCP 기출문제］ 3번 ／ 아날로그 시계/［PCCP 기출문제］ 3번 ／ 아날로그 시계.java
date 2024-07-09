/**
 * 프로그래머스 아날로그 시계
 * 
 * 1. 시침, 분침, 초침이 1초에 몇도씩 움직이는지 구하기
 * 2. 시침, 분침이 겹치는 경우 -1
 * 3. 초침이 시침/분침과 겹치는 경우 +1
 */

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        // 시작 초, 종료 초
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        if (startTime % 360 == 0 || startTime % 360 == 12) answer++;
                
        while (startTime < endTime) {
            double hourAngle = (startTime / (double)120) % 360;
            double minuteAngle = (startTime / (double)10) % 360;
            double secondsAngle = (startTime * 6) % 360;
            
            double nextHourAngle = ((startTime + 1) / (double)120) % 360;
            double nextMinuteAngle = ((startTime + 1) / (double)10) % 360;
            double nextSecondsAngle = ((startTime + 1) * 6) % 360;
            
            if (nextHourAngle == 0) nextHourAngle = 360;
            if (nextMinuteAngle == 0) nextMinuteAngle = 360;
            if (nextSecondsAngle == 0) nextSecondsAngle = 360;
            
            if (secondsAngle < hourAngle && nextSecondsAngle >= nextHourAngle) answer++;
            if (secondsAngle < minuteAngle && nextSecondsAngle >= nextMinuteAngle) answer++;
            if (nextHourAngle == nextMinuteAngle) answer--;
            
            startTime++;
        }
        
        return answer;
    }
}