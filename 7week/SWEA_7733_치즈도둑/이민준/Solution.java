import java.io.*;
import java.util.*;

public class Solution {
	static int n, map[][];
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine()); // 치즈 크기
			
			map = new int[n][n];					// 치즈 맛있는 정도
			
			for (int i = 0; i < n; i++) {		// 치즈 맛있는 정도 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 1; // 최대 덩어리 개수
			for (int day = 1; day <= 100; day++) { // 1일부터 100일까지 진행
				visited = new boolean[n][n];			// 덩어리를 세기 위한 방문 배열
				// 덩어리 개수 받아오기
				int cnt = counting(day);
				
				// max와 비교
				if(max <= cnt) {
					max = cnt;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.print(sb.toString());
	}

	private static int counting(int day) {
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {		// day보다 작거나 같은 값 방문 처리
			for (int j = 0; j < n; j++) {
				if(map[i][j] <= day) {
					visited[i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {		// 실제 카운팅
			for (int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					cnt++;
					dfs(day, i, j);
				}
			}
		}
		
		return cnt;
	}

	private static void dfs(int day, int r, int c) {
		// 함수 들어왔을 때 방문처리
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) { // 상 하 좌 우 확인
			int nr = r + dr[d]; int nc = c + dc[d];
			
			// 범위 확인
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			// 방문되어있거나 day가 작거나 같다면
			if(visited[nr][nc]) continue;
			
			dfs(day, nr, nc);
		}
	}
}
