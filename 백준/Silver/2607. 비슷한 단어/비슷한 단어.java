import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 백준 2607번 비슷한 단어
 * 1. 들어가 있는 문자는 모두 같은데 순서가 다를 경우
 * 2. 한 문자를 더할 경우
 * 3. 한 문자를 뺄 경우
 * 4. 한 문자를 다른 문자로 바꿀 경우
 * 
 * List에 알파벳 하나씩 넣어서 contains로 확인하고 그 차이가 2개 이상 나지 않도록?
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int count = 0;
		
		String str = br.readLine();
		
		for (int i = 0; i < n - 1; i++) {
			String word = br.readLine();
			List<String> list = new ArrayList<>();
			List<String> newWord = new ArrayList<>();
			
			for (int j = 0; j < str.length(); j++) {
				list.add(str.split("")[j]);
			}
			
			for (int j = 0; j < word.length(); j++) {
				newWord.add(word.split("")[j]);
			}

			for (int j = 0; j < word.length(); j++) {
				if (list.contains(word.charAt(j) + "")) {
					list.remove(word.charAt(j) + "");
					newWord.remove(word.charAt(j) + "");
				}
			}
			
			if (list.size() <= 1 && newWord.size() <= 1) count++;
			
		}
		
		System.out.println(count);
	}
}
