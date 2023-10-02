
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * [문제] 백준 21608 상어초등학교
 * [아이디어]
 * 인접한다는 것, 즉 |r1-r2| + |c1-c2| = 1는 사방탐색을 했을 경우 존재한다는 것이다.
 * 
 * 메모리  : kb
 * 실행 시간 : ms
 * 
 * @author 김유나
 * 2023-10-02
 *
 */
public class Main {
	static class Seat implements Comparable<Seat> {
		int x;
		int y;
		int likeSum;
		int emptySum;
		
		public Seat(int x, int y, int likeSum, int emptySum) {
			this.x = x;
			this.y = y;
			this.likeSum = likeSum;
			this.emptySum = emptySum;
		}

		@Override
		public int compareTo(Seat o) {
			// 1순위 : 인접한 좋아하는 학생 수 비교
			if (this.likeSum != o.likeSum) return -(this.likeSum- o.likeSum);
			
			// 2순위 : 인접한 빈칸 수 비교
			if (this.emptySum != o.emptySum) return -(this.emptySum - o.emptySum);

			// 3순위 : 행 비교
			if (this.x != o.x) return x - o.x;
			
			// 1~3순위 모두 충족하지 않을 때 : 열 비교
			return y - o.y;
		}
	}
	
	static int n, sum = 0;
	static int arr[][], students[];
	static Map<Integer, Set<Integer>> favor;
	static int deltas[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		students = new int[n * n];
		favor = new HashMap<>();
		
		
		for (int i = 0; i < n * n; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			students[i] = student;
			favor.put(student, new HashSet<>());
			for (int j = 0; j < 4; j++) {
				favor.get(student).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < n * n; i++) {
			Seat seat = sit(students[i]);
			arr[seat.x][seat.y] = students[i];
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int count = getLikeSum(i, j, arr[i][j]);
				
				if (count > 0) sum += (int) Math.pow(10, count - 1);
			}
		}
		
		System.out.println(sum);
	}
	
	static Seat sit(int student) {
		Seat seat = null;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] != 0) continue;
				
				Seat current = new Seat(i, j, getLikeSum(i, j, student), getEmptySum(i, j));
				
				if (seat == null) {
					seat = current;
					continue;
				}
				
				if (seat.compareTo(current) > 0) {
					seat = current;
				}
			}
		}
		
		return seat;
	}
	
	static int getLikeSum(int x, int y, int student) {
		int count = 0;
		
		for (int i = 0; i < 4; i++) {
			int dx = x + deltas[i][0];
			int dy = y + deltas[i][1];
			
			if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
			
			if (favor.get(student).contains(arr[dx][dy])) count++;
		}
	
		return count;
	}
	
	static int getEmptySum(int x, int y) {
		int count = 0;
		
		for (int i = 0; i < 4; i++) {
			int dx = x + deltas[i][0];
			int dy = y + deltas[i][1];
			
			if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
			
			if (arr[dx][dy] == 0) count++;
		}
	
		return count;
	}
}
