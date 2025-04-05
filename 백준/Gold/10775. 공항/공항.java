import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 10775번 공항
 * - Union-Find 
 */

public class Main {
	public static int[] parent, gate;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parent = new int[G + 1];
		int count = 0;
		
		for (int i = 1; i <= G; i++) parent[i] = i;
		
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			
			if (find(g) == 0) break;
			
			count++;
			
			union(find(g) - 1, find(g));
		}
		
		System.out.println(count);
	}
	
	public static int find(int n) {
		if (parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
}
