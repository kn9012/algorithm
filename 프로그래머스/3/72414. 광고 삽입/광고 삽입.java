/**
 * 프로그래머스 광고 삽입
 * - 초로 변경
 * - 구간합, 누적합, 슬라이딩 윈도우
 */

import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        
        long playTime = changeToSec(play_time);
        int advTime = changeToSec(adv_time);
        
        int[] sum = new int[99 * 60 * 60 + 59 * 60 + 59];
        
        for (String log : logs) {
            String[] times = log.split("-");
            int start = changeToSec(times[0]);
            int end = changeToSec(times[1]);
            
            for (int i = start; i < end; i++) {
                sum[i]++;
            }
        }
        
        long s = 0;
        long ms = 0;
        long maxTime = 0;
        
        for (int i = 0; i < advTime; i++) {
            s += sum[i];
        }
        
        ms = s;
        
        for (int i = advTime; i < playTime; i++) {
            s += sum[i] - sum[i - advTime];
            
            if (s > ms) {
                ms = s;
                maxTime = i - advTime + 1;
            }
        }
        
        return changeToString(maxTime);
    }
    
    public int changeToSec(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]) * 60 * 60;
        int min = Integer.parseInt(times[1]) * 60;
        return hour + min + Integer.parseInt(times[2]);
    }
    
    public String changeToString(long time) {
        long hour = time / 3600;
        long min = time % 3600 / 60;
        long sec = time % 3600 % 60;
        
        String h = (hour < 10 ? "0" + hour : hour + "");
        String m = (min < 10 ? "0" + min : min + "");
        String s = (sec < 10 ? "0" + sec : sec + "");
        return h + ":" + m + ":" + s;
    }
}