import java.io.*;
import java.util.*;

public class Solution {
	static int[][] arr;
	static PriorityQueue<int[]> pq;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			int n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

			/* 붙어있는 문자열을 int[][]에 값 넣기 방법 1
			for (int i = 0; i < n; i++) {
				String[] s = br.readLine().split("");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(s[j]);
				}
			}
			*/
			
			// 붙어있는 문자열을 int[][]에 값 넣기 방법 2
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = s.charAt(j) - '0';
				}
			}

			int[][] dist = new int[n][n];
			
			/* dist에 MAX 값 넣기 방법 1
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			*/
			
			// dist에 MAX 값 넣기 방법 2
			for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
			dist[0][0] = 0;
			
			pq.offer(new int[] { 0, 0, 0 });
			boolean[][] visited = new boolean[n][n];
			visited[0][0] = true;
			
			int answer = 0;
			while (!pq.isEmpty()) {
				int[] p = pq.poll();
				int x = p[0];
				int y = p[1];
				int cost = p[2];

				/* 현재 문제에서는 필요없음.
				다익스트라에서 효율을 높이는 방법 1
				if(cost > dist[x][y]) continue;
				
				다익스트라에서 효율을 높이는 방법 2
				if(visited[x][y]) continue;
				visited[x][y] = true;
				*/
				if (x == n - 1 && y == n - 1) {
					answer = cost;
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						continue;
					
					int nextCost = cost + arr[nx][ny];
					if (dist[nx][ny] > nextCost) {
						dist[nx][ny] = nextCost;
						pq.offer(new int[] { nx, ny, cost + arr[nx][ny] });
					}
				}

			}
			sb.append(answer).append('\n');
		}
		System.out.print(sb.toString());
	}

}
