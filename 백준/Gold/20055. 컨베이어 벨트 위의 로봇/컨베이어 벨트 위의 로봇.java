import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static class Belt {
		boolean isRobot;
		int durability;
		
		public Belt(int durability) {
			this.durability = durability;
			isRobot = false;
		}
		
		public void putRobot() {
			isRobot = true;
			durability--;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		LinkedList<Belt> belt = new LinkedList<>();
		
		for (int i = 0; i < 2 * N; i++) {
			belt.add(i, new Belt(Integer.parseInt(st.nextToken())));
		}
		
		int level = 0;
		
		while (K > 0) {
			level++;
			
			// 1.
			belt.add(0, belt.removeLast());
			
			if (belt.get(N - 1).isRobot) belt.get(N - 1).isRobot = false;
			
			// 2.
			for (int i = N - 1; i > 0; i--) {
				if (!belt.get(i).isRobot) continue;
				
				if (belt.get(i + 1).isRobot || belt.get(i + 1).durability <= 0) continue;
				
				belt.get(i).isRobot = false;
				belt.get(i + 1).putRobot();
				if(belt.get(i + 1).durability <= 0) K--;
				
				if (i + 1 == N - 1) belt.get(i + 1).isRobot = false;
			}
			
			// 3.
			if (belt.get(0).durability > 0) {
				belt.get(0).putRobot();
				if(belt.get(0).durability <= 0) K--;
			}
		}
		
		System.out.println(level);
	}
}
