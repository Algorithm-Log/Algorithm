import java.util.*;
import java.io.*;

class Solution
{
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override //정렬시 비교 기준
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static int[] parent;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			List<Edge> edge = new ArrayList<>();
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edge.add(new Edge(a,b,c));
			}
			Collections.sort(edge); // cost 기준으로 정렬
			parent = new int[v + 1];
			for(int i = 1; i <= v; i++) {
				parent[i] = i;
			}
			long answer = 0;
			int cnt = 0;
			for(int i = 0; i < edge.size(); i++) {
				Edge now = edge.get(i);
				if(find(now.from) != find(now.to)) {
					union(now.from, now.to);
					answer += now.cost;
					cnt++;
				}
				if(cnt == v+1)	break;
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] = a;
		}
	}
	public static int find(int x) {
		if(parent[x] == x) { //자기 자신이라면
			//그냥 그대로 돌려줘야함
			return x;
		}
		parent[x] = find(parent[x]);
		return parent[x];
	}
}