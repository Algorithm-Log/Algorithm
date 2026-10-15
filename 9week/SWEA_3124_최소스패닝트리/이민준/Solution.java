import java.io.*;
import java.util.*;

// Prim으로 구현
public class Solution1014 {
	static int N, M;
	static List<List<int[]>> nodeList;
	static boolean[] visited;
	static PriorityQueue<int[]> pq;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 노드 개수
			M = Integer.parseInt(st.nextToken()); // 간선 개수
			visited = new boolean[N+1];
			nodeList = new ArrayList<>();     	  // 인접 리스트로 구현
			
			// 인접리스트 구현하기 위한 사전작업
			for (int i = 0; i <= N; i++) {
				nodeList.add(new ArrayList<>());
			}
			
			pq = new PriorityQueue<>((a,b) -> a[1]-b[1]); // 가중치 기준 오름차순
			
			// 입력
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				// 무방향이기에 양쪽으로 진행
				nodeList.get(a).add(new int[] {b, weight});
				nodeList.get(b).add(new int[] {a, weight});
			}
			
			// 첫번째 노드로 시작
			pq.offer(new int[] {1,0});
			
			// 다 연결되면 탈출하기 위한 변수
			int cnt = 0;
			// 가중치 최대값: 1,000,000, 노드 개수: 100,000, 도합: 100,000,000,000이므로 long으로 진행
			long result = 0; 
			while(true) {
				int arr[] = pq.poll();
				int node = arr[0];
				int weight = arr[1]; // 가중치는 최대가 1,000,000이라 int로 구현
				
				// 우선순위 큐이기에 방문했는지 확인 진행
				if(visited[node]) continue;
				visited[node] = true;
				cnt++;
				result += weight; // 묵시적 형변환
				
				// 다 연결되었다면 탈출
				if(cnt == N) break;
				
				// 현재 노드에 연결되어있는 노드만 확인, 인접행렬로 하면 시간초과.
				for(int[] nextNode : nodeList.get(node)) {
					// 다음 노드가 이미 방문되어있다면 생략
					if(visited[nextNode[0]]) continue;
					// 다음 노드와 가중치 기입
					pq.offer(new int[] {nextNode[0], nextNode[1]});
				}
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
}
