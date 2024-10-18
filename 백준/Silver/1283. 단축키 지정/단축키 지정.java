import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<>();

		for (int i = 0; i < N; i++) {
			boolean isShortcutKey = false;
			String str = br.readLine();
			String words[] = str.split(" ");

			// 1. 단어의 첫 글자가 단축키로 지정되었는지
			for (int j = 0; j < words.length; j++) {
				String s = words[j].charAt(0) + "";
				int index = 0;
				
				if (!set.contains(s.toUpperCase()) && !set.contains(s.toLowerCase())) {
					set.add(s.toUpperCase());
					set.add(s.toLowerCase());
					isShortcutKey = true;
					
					for (int k = 0; k < j; k++) {
						index += words[k].length();
						if (index != 0) index += 1;
					}
					
					sb.append(str.substring(0, index) + "[" + str.charAt(index) + "]" + str.substring(index + 1) + "\n");
					break;
				}
			}
			
			if (isShortcutKey) continue;
			
			for (int j = 0; j < str.length(); j++) {
				String s = str.charAt(j) + "";
				
				if (!s.equals(" ") && !set.contains(s.toUpperCase()) && !set.contains(s.toLowerCase())) {
					set.add(s.toUpperCase());
					set.add(s.toLowerCase());
					isShortcutKey = true;
					
					sb.append(str.substring(0, j) + "[" + str.charAt(j) + "]" + str.substring(j + 1) + "\n");
					break;
				}
			}
			
			
			if (isShortcutKey) continue;
			
			sb.append(str + "\n");
		}
		
		System.out.println(sb);
	}
}
