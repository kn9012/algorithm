import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int c : course) {
            Map<String, Integer> map = new HashMap<>();

            for (String order : orders) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                combine(arr, new StringBuilder(), 0, c, map);
            }

            int max = 0;
            for (int count : map.values()) {
                if (count >= 2) {
                    max = Math.max(max, count);
                }
            }

            for (String key : map.keySet()) {
                if (map.get(key) == max && max >= 2) {
                    answer.add(key);
                }
            }
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private void combine(char[] arr, StringBuilder sb, int start, int len, Map<String, Integer> map) {
        if (sb.length() == len) {
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            sb.append(arr[i]);
            combine(arr, sb, i + 1, len, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
