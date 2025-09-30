/**
 * 프로그래머스 광고 삽입
 * - 구간 합 + 슬라이딩 윈도우 사용
 */

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = changeToSec(play_time);
        int advTime = changeToSec(adv_time);
        
        int[] prefixSum = new int[playTime];
        
        for (String log : logs) {
            String[] l = log.split("-");
            
            int start = changeToSec(l[0]);
            int end = changeToSec(l[1]);
            
            for (int i = start; i < end; i++) {
                prefixSum[i]++;
            }
        }
        
        long sum = 0;
        long maxTime = 0;
        
        for (int i = 0; i < advTime; i++) {
            sum += prefixSum[i];
        }
        
        long maxSum = sum;
        
        for (int i = advTime; i < playTime; i++) {
            sum += prefixSum[i] - prefixSum[i - advTime];
            
            if (sum > maxSum) {
                maxSum = sum;
                maxTime = i - advTime + 1;
            }
        }
        
        return changeToString(maxTime);
    }
    
    public int changeToSec(String time) {
        String[] times = time.split(":");
        
        int hour = Integer.parseInt(times[0]) * 3600;
        int min = Integer.parseInt(times[1]) * 60;
        int sec = Integer.parseInt(times[2]);
        
        return hour + min + sec;
    }
    
    public String changeToString(long time) {
        long hour = time / 3600;
        long min = time % 3600 / 60;
        long sec = time % 3600 % 60;
        
        return (hour < 10 ? "0" + hour : hour + "") + ":" + (min < 10 ? "0" + min : min + "") + ":" + (sec < 10 ? "0" + sec : sec + "");
    }
}