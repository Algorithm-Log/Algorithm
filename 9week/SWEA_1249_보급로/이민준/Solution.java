import java.io.*;
import java.util.*;

public class Solution1013 {
	static int N, map[][], memo[][];
	static final int MAX = Integer.MAX_VALUE;
	static PriorityQueue<int[]> que;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // map 크기
			map = new int[N][N];
			memo = new int[N][N];				 // 다익스트라 위한 자료구조
			visited = new boolean[N][N]; 		 // 방문처리 진행
			
			que = new PriorityQueue<>((a,b) -> a[2] - b[2]); // 가중치 기준 오름차순
			
			// 다익스트라 알고리즘 수행을 하기 위해 배열에 모두 Integer.MAX_VALUE 넣음
			for (int i = 0; i < N; i++) {
				Arrays.fill(memo[i], MAX);
			}
			
			// 지도 입력
			for (int i = 0; i < N; i++) {
				String[] str = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			que.offer(new int[] {0, 0, 0}); // 행, 열, 가중치합
			memo[0][0] = 0;
			
			int result = 0;
			while(!que.isEmpty()) {
				int[] arr = que.poll();
				int r = arr[0];
				int c = arr[1];
				int weight = arr[2];
				
				// 오른쪽 구석 도착하면 나감
				if(r == N-1 && c == N-1) {
					result = weight;
					break;
				}
				
				// 우선순위 큐에서 나왔을 때 이 값은 최소값으로 확정. 그래서 나올 때 방문처리
				visited[r][c] = true;
				
				// 4방면 진행
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// map 나가는지 확인
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					// 방문한 곳인지 확인
					if(visited[nr][nc]) continue;
					
					// map에 벗어나지도 않고 방문하지 않은 곳이라면, 지금 가중치와 갈 곳의 가중치를 더함 
					int sum = weight + map[nr][nc];
					
					// 더한 가중치가 memo의 가중치보다 작다면 갱신
					if(sum < memo[nr][nc]) {
						memo[nr][nc] = sum;
					}
					
					// 더한 가중치로 갈 곳에 기입
					que.offer(new int[] {nr, nc, sum});
				}
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
