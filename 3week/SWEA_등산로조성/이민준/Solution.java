import java.io.*;
import java.util.*;

public class Solution {
	
	private static int[][] arr;
	private static int n, k, resultMax;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			arr = new int[n][n];
			visited = new boolean[n][n];
			resultMax = 0;
			int max = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, arr[i][j]);
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(max == arr[i][j]) {
						visited[i][j] = true;
						dfs(i,j, 1, false);
						visited[i][j] = false;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(resultMax).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void dfs(int i, int j, int depth, boolean check) {
		
		resultMax = Math.max(resultMax, depth);
		
		for(int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			
			if(nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
				
				if(arr[i][j] > arr[nr][nc]) {
					
					visited[nr][nc] = true;
					dfs(nr, nc, depth + 1, check);
					visited[nr][nc] = false;
					
				}else if(arr[i][j] > (arr[nr][nc]-k) && !check) {
					int diff = arr[nr][nc] - arr[i][j] + 1;
					
					arr[nr][nc] -= diff;
					
					visited[nr][nc] = true;
					dfs(nr, nc, depth + 1, true);
					visited[nr][nc] = false;
					
					arr[nr][nc] += diff;
					
				}
			}
		}
	}
}
