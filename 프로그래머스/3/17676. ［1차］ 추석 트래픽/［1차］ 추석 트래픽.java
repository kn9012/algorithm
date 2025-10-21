class Solution {
    public int solution(String[] lines) {
        int n = lines.length;
        
        long[] start = new long[n];
        long[] end = new long[n];
        
        for (int i = 0; i < n; i++) {
            String[] times = lines[i].split(" ");
            
            long time = stringToMilis(times[1]);
            
            double processingTime = Double.parseDouble(times[2].substring(0, times[2].length() - 1));
            long processingMs = (long) (processingTime * 1000);
            //System.out.println(processingMs);
            long startMs = time - processingMs + 1;
            
            start[i] = startMs;
            end[i] = time;
            
            //System.out.println(start[i] + " " + end[i]);
        }
        
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            long startCheck = start[i];
            long endCheck = end[i];
            
            
            for (long check : new long[] {startCheck, endCheck}) {
                long rangeEnd = check + 999;
                int count = 0;

                for (int j = 0; j < n; j++) {
                    if (end[j] >= check && start[j] <= rangeEnd) count++;
                }

                max = Math.max(max, count);
            }
        }
        
        return max;
    }
    
    public long stringToMilis(String time) {
        long totalTime = 0;
        
        String[] times = time.split(":");
        totalTime += Integer.parseInt(times[0]) * 60 * 60 * 1000;
        totalTime += Integer.parseInt(times[1]) * 60 * 1000;
        totalTime += Double.parseDouble(times[2]) * 1000;
        
        return totalTime;
    }
}