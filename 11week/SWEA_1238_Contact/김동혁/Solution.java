import java.io.*;
import java.util.*;



public class Solution {
	
	static List<Integer>[] graph;
	static int[] visited;
	
	static void bfs(int start) {
		Deque<Integer> q = new ArrayDeque<>();
		visited[start] = 1;
		q.offerLast(start);
		
		while(!q.isEmpty()) {
			int node = q.pollFirst();
			
			for(int next : graph[node]) {
				
				if(visited[next]!=0) continue;
				
				visited[next] = visited[node] +1;
				q.offerLast(next);
				
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		
		for (int tc = 1; tc <= 10; tc++) {
			graph = new ArrayList[101];
			
			for (int i = 1; i <=100; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			visited = new int[101];
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
			}
			
			bfs(start);
			
			int max = 0;
			for (int i = 1; i <= 100; i++) {
				max = Math.max(max, visited[i]);
			}
			
			int answer = 0;
			for(int i = 100;i>=1;i--) {
				if(visited[i]==max) {
					answer = i;
					break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			
		}
		
		System.out.print(sb);
		
		
	}
}
