import java.io.*;
import java.util.*;

public class Solution {

	// 터널 타입마다의 방향 설정
	
	public static class Node{
		int r;
		int c;
		int time;
		
		public Node(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int[][] arr;
	static boolean[][] visited;
	static int R, C;
	static Deque<Node> que;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr = new int[R][C];
			visited = new boolean[R][C];
			
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			
			int remitTime = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			que = new LinkedList<>();
			que.offer(new Node(startR, startC, 1));
			visited[startR][startC] = true;
			
			int cnt = 0;
			while(!que.isEmpty()) {
				Node node = que.poll();
				int r = node.r;
				int c = node.c;
				int time = node.time;
				
				if(time > remitTime) continue;
				cnt++;
				
				dirFunc(r, c, time);
			}
			
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void dirFunc(int r, int c, int time) {
		
		switch(arr[r][c]) {
		case 1: // 상, 하, 좌, 우
			dir(new int[] {-1, 1, 0, 0}, new int[] {0, 0, -1, 1}, r, c, time);
			break;
		case 2: // 상, 하
			dir(new int[] {-1, 1}, new int[] {0, 0}, r, c, time);
			break;
		case 3: // 좌, 우
			dir(new int[] {0, 0}, new int[] {-1, 1}, r, c, time);
			break;
		case 4: // 상, 우
			dir(new int[] {-1, 0}, new int[] {0, 1}, r, c, time);
			break;
		case 5: // 하, 우
			dir(new int[] {1, 0}, new int[] {0, 1}, r, c, time);
			break;
		case 6: // 하, 좌
			dir(new int[] {1, 0}, new int[] {0, -1}, r, c, time);
			break;
		case 7: // 상, 좌
			dir(new int[] {-1, 0}, new int[] {0, -1}, r, c, time);
			break;
		}
	}
	
	public static void dir(int[] dr, int[] dc, int r, int c, int time) {
		for(int i = 0; i < dr.length; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || arr[nr][nc] == 0 || visited[nr][nc]) continue;
			
			boolean check = false;
			// 가는 방향도 지금 위치해있는 방향에 길이 뚫려있는지 확인해아함.
			switch(arr[nr][nc]) {
			case 1:
				check = true;
				break;
			case 2: // 상, 하
				if(nr-1 == r && nc == c || nr+1 == r && nc == c) check = true;
				break;
			case 3: // 좌, 우
				if(nr == r && nc+1 == c || nr == r && nc-1 == c) check = true;
				break;
			case 4: // 상, 우
				if(nr-1 == r && nc == c || nr == r && nc+1 == c) check = true;
				break;
			case 5: // 하, 우
				if(nr+1 == r && nc == c || nr == r && nc+1 == c) check = true;
				break;
			case 6: // 하, 좌
				if(nr+1 == r && nc == c || nr == r && nc-1 == c) check = true;
				break;
			case 7: // 상, 좌
				if(nr-1 == r && nc == c || nr == r && nc-1 == c) check = true;
				break;
			}
			if(!check) continue; 
			
			visited[nr][nc] = true;
			que.offer(new Node(nr, nc, time + 1));
		}
	}
}
