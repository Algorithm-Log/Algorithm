import java.io.*;
import java.util.*;

public class Solution {
	static int N, map[][];
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1,-1, 0, 0};
	static int[][] dist;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[][] visited = new boolean[N][N];
			PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
			pq.offer(new int[] {0, 0, 0});
			dist[0][0] = 0;
			
			int result = 0;
			while(!pq.isEmpty()) {
				int[] arr = pq.poll();
				int r = arr[0];
				int c = arr[1];
				int v = arr[2];
				
				if(r == N-1 && c == N-1) {
					result = v;
					break;
				}
				
				if (visited[r][c]) continue;
				visited[r][c] = true;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
					
					int nextV = v;
					if(map[r][c] < map[nr][nc]) { // 가는 방향이 숫자가 더 높을 때
						nextV += (map[nr][nc] - map[r][c])*2;
					}else if(map[r][c] == map[nr][nc]) { // 가는 방향과 숫자가 같을 때
						nextV++;
					}// 가능 방향이 숫자가 더 작을 때는 v의 변화가 없기에 생략
					
					if(dist[nr][nc] > nextV) { // 값이 더 작을 때만 offer
						pq.offer(new int[] {nr, nc, nextV});
						dist[nr][nc] = nextV;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
}
