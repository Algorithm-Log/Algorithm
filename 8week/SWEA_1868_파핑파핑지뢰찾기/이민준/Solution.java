import java.io.*;
import java.util.*;

public class Solution {
	static int n;
	static char[][] map;
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new char[n][n];
			visited = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().trim().toCharArray();
			}
			
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 주위에 지뢰가 없고, 현재 위치가 .이며, 방문하지 않았다면
					if(check(i, j) && map[i][j] == '.' && !visited[i][j]) {
						cnt++; visited[i][j] = true;
						dfs(i, j);
					}
				}
			}
			
			// 0이 아닌 .들을 클릭하기 위한 반복문
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 현재 위치가 .이라면
					if(map[i][j] == '.' && !visited[i][j]) {
						cnt++;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	private static void dfs(int r, int c) {
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isRanges(nr, nc)) continue;
			
			// 지뢰가 아닌 미방문 길인데 8방에 지뢰가 있다면 방문체크만 진행, 없다면 방문체크 후 dfs 진행  
			if(map[nr][nc] == '.' && !visited[nr][nc]) {
				visited[nr][nc] = true;
				if(check(nr, nc))
					dfs(nr, nc);
			}
		}
	}
	
	// 주위 8방에 지뢰가 있는지 확인 
	private static boolean check(int r, int c) {
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isRanges(nr, nc)) continue;
			
			if(map[nr][nc] == '*') return false;
		}
		return true;
	}
	
	// n x n을 벗어나는지 확인
	private static boolean isRanges(int r, int c) {
		if(r < 0 || r >= n || c < 0 || c >= n)
			return false;
		else
			return true;
	}
}
