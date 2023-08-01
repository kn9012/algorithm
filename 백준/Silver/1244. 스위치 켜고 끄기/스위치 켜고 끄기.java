import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchN = Integer.parseInt(br.readLine());
		int switchs[] = new int[switchN];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < switchN; i++) {
			switchs[i] = Integer.parseInt(st.nextToken());
		}
		
		int studentN = Integer.parseInt(br.readLine());
		int students[][] = new int[studentN][2];
		
		for (int i = 0; i < studentN; i++) {
			st = new StringTokenizer(br.readLine());
			students[i][0] = Integer.parseInt(st.nextToken());
			students[i][1] = Integer.parseInt(st.nextToken());
		}
		
		switchRun(switchs, students);
		
	}
	static void switchRun(int[] switchs, int [][] students) {
		int sLength = switchs.length;
		
		for (int i = 0; i < students.length; i++) {
			// if 남학생이면
			if (students[i][0] == 1) {
				for (int j = 1; j <= sLength / students[i][1]; j++) {
					switchs[students[i][1] * j - 1] = switchs[students[i][1] * j - 1] == 0 ? 1 : 0;
				}
			}
			// else if 여학생이면
			else if (students[i][0] == 2) {
				int minIdx = Math.min(sLength - students[i][1] + 1, students[i][1]);
				int stopIdx = -1;
				
				if (students[i][1] == 1 || students[i][1] == sLength) {
					switchs[students[i][1] - 1] = switchs[students[i][1] - 1] == 0 ? 1 : 0;
				}
				
				else {
					for (int j = 0; j <= minIdx - 1; j++) {
						if (j == 0) switchs[students[i][1] - 1] = switchs[students[i][1] - 1] == 0 ? 1 : 0;
						else {
							if (switchs[students[i][1] - 1 - j] == switchs[students[i][1] - 1 + j]) {
								stopIdx = j;
							}
							else break;
							
						}
					}
					
					for (int j = 1; j <= stopIdx; j++) {
						switchs[students[i][1] - 1 - j] = switchs[students[i][1] - 1 - j] == 0 ? 1 : 0;
						switchs[students[i][1] - 1 + j] = switchs[students[i][1] - 1 + j] == 0 ? 1 : 0;
					}
				}
			}
		}
		
		for (int i = 0; i < sLength; i++) {
			if (i > 0 && i % 20 == 0) {
				System.out.println();
			}
			System.out.print(switchs[i] + " ");
		}
	}
}
