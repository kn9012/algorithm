import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 백준 200006번 랭킹전 대기열
 * 방 하나에 들어갈 정보 : 첫번째 입장 플레이어의 레벨, 입장 플레이어 아이디
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int p = Integer.parseInt(st.nextToken()); // 플레이어 수
		int m = Integer.parseInt(st.nextToken()); // 방의 정원
		
		Map<String, Integer> map = new HashMap<>();
		List<Integer> room = new ArrayList<>();
		List<List<String>> player = new ArrayList<>();
		
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			int level = Integer.parseInt(st.nextToken());
			String id = st.nextToken();
			
			map.put(id, level);
			
			if (room.isEmpty()) {
				room.add(level);
				List<String> list = new ArrayList<>();
				list.add(id);
				player.add(list);
			} else {
				boolean enter = false;
				
				for (int j = 0; j < room.size(); j++) {
					if (room.get(j) + 10 >= level && room.get(j) - 10 <= level) {
						if (player.get(j).size() < m) {
							player.get(j).add(id);
							enter = true;
							break;
						}
					}
				}
			
				if (!enter) {
					room.add(level);
					List<String> list = new ArrayList<>();
					list.add(id);
					player.add(list);
				}
			}
		}
		
		for (int i = 0; i < room.size(); i++) {
			if (player.get(i).size() == m) {
				sb.append("Started!\n");
			} else {
				sb.append("Waiting!\n");
			}
			
			Collections.sort(player.get(i));
			for (String s : player.get(i)) {
				sb.append(map.get(s) + " " + s + "\n");
			}
		}
		
		System.out.println(sb);
	}
}