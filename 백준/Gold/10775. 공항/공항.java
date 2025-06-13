import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) parent[i] = i;
		
		int answer = 0;
		
		for (int i = 0; i < P; i++) {
			int airplane = Integer.parseInt(br.readLine());
			
			if (find(airplane) == 0) break;
			
			union(parent[airplane] - 1, parent[airplane]);
			answer++;
		}
		
		System.out.println(answer);
	}
	
	public static int find(int n) {
		if (parent[n] == n) return n;
		else return parent[n] = find(parent[n]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
}
