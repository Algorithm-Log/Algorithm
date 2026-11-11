import java.util.*;
import java.io.*;

class Solution
{
	static int n, MaxCore, MinLine;
	static int[][] map;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static List<int[]> cores; //core위치
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			cores = new ArrayList<>();
			MaxCore = 0;
			MinLine = Integer.MAX_VALUE;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
						if(map[i][j] == 1) {
							if(i != (n-1) && i != 0 && j != (n-1) && j != 0) { //	가장자리꺼 빼고 담아야하니까						
							cores.add(new int[] {i,j});
						}
					}
				}
			}
			dfs(0,0,0);
			System.out.println("#" + test_case + " " + MinLine );
		}
	}
	public static void dfs(int index, int Corecnt, int Linecnt) {
		if(index == cores.size()) {
			if(MaxCore == Corecnt) {
				MinLine = Math.min(MinLine, Linecnt);
			}
			else if(MaxCore < Corecnt){
				MaxCore = Corecnt;
				MinLine = Linecnt;
			}
			return;
		}
		//0은 SELL, 1은 Core, 2는 간선
		int row = cores.get(index)[0];
		int col = cores.get(index)[1];
		
		for(int d = 0; d < 4; d++) {
			int len = 0;
			int nx = row + dx[d];
			int ny = col + dy[d];
			if(isConnect(nx,ny,d)) {
				while(nx >= 0 && nx < n  && ny >= 0 && ny < n) {//간선 설치				
					map[nx][ny] = 2;
					len++;
					nx += dx[d];
					ny += dy[d];
				}
				dfs(index + 1, Corecnt + 1, Linecnt + len);
				while(nx != row || ny != col) { //백트래킹
					nx -= dx[d];
					ny -= dy[d];
					if(map[nx][ny] != 1) {
						map[nx][ny] = 0;						
					}
				}
			}
		}
		dfs(index+1,Corecnt,Linecnt);
	}
	public static boolean isConnect(int r, int c,int d) {
		while (r >= 0 && r < n && c >= 0 && c < n) {
			if(map[r][c] != 0) {
				return false;
			}
			
			r += dx[d];
			c += dy[d];
		}
		return true;
	}
}