import java.io.*;
import java.util .*;

// Prim 알고리즘으로 진행
public class Solution1014 {
	static int N, nodes[][];
	static PriorityQueue<long[]> pq;
	static boolean[] visited;
	static double E;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 노드 개수
			nodes = new int[N][2];				 // 노드 담을 자료구조
			pq = new PriorityQueue<>((a,b)-> Double.compare(a[2], b[2])); // 가중치 기준 오름차순
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) { // X값 입력
				nodes[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) { // Y값 입력
				nodes[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 세율 입력
			E = Double.parseDouble(br.readLine());
			
			// 첫번째 노드로 시작. X, Y, 가중치, index번호(방문처리 하기 위함)
			pq.offer(new long[] {nodes[0][0], nodes[0][1], 0, 0});
			
			double result = 0;
			// 연결한 개수가 노드 개수가 되면 탈출하기 위한 변수 cnt
			int cnt = 0;
			while(!pq.isEmpty()) { // 다 돌지 않을 때 -1 출력하려면 !pq.isEmpty(), 아니라면 true 노상관
				long[] arr = pq.poll();
				int x = (int) arr[0];
				int y = (int) arr[1];
				long v = arr[2];
				int idx = (int) arr[3];
				
				// 이미 방문된 곳인지 확인(우선순위 큐이기에 해줘야함)
				if(visited[idx]) continue;
				visited[idx] = true;
				cnt++;
				result += v;
				
				// 연결 다 되었다면 탈출
				if(cnt == N) {
					break;
				}
				
				// 현재 위치에서 방문하지 않는 곳들에 모두 확인
				for (int i = 0; i < N; i++) {
					if(visited[i]) continue;
					
					// 가중치 계산
					long dis = (long) Math.abs(x - nodes[i][0]) * Math.abs(x - nodes[i][0])
								+
							   (long) Math.abs(y - nodes[i][1]) * Math.abs(y - nodes[i][1]);
					
					// 기입
					pq.offer(new long[] {nodes[i][0], nodes[i][1], dis, i});
				}
			}	
			
			sb.append('#').append(tc).append(' ').append(Math.round(result * E)).append('\n');
		}
		System.out.print(sb.toString());
	}
}
