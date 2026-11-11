import java.util.*;
import java.io.*;

public class Solution {
	private static int length;
	private static int[][] graph;
	private static int[] depth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int testcase = 1; testcase <= 10; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			length = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			graph = new int[length/2][2];
			
			for (int i = 0; i < length/2; i++) {
				graph[i][0] = Integer.parseInt(st.nextToken());
				graph[i][1] = Integer.parseInt(st.nextToken());
			}
			
			depth = new int[101];
			Arrays.fill(depth, -1);
			bfs(start);
			
			int maxDepth = -1;
			int answer = 0;
			for (int i = 0; i < depth.length; i++) {
				if(depth[i] >= maxDepth) {
					maxDepth = depth[i];
					answer = Math.max(answer, i);
				}
			}
			
			System.out.println("#" + testcase + " " + answer);
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		depth[start] = 0;
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			
			for (int i = 0; i < length/2; i++) {
				if (graph[i][0] == from && depth[graph[i][1]] == -1) {	// 새로 방문하고자 하는 노드가 아직 미방문 노드일때만
					depth[graph[i][1]] = depth[from] + 1; // 현재 있는 노드보다 깊이 +1
					queue.offer(graph[i][1]);
				}
			}
		}
	}
}