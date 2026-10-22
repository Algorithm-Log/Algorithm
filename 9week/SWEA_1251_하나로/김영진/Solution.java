import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		long cost;
		
		Edge(int from, int to, long cost){
			this.from = from;
			this.to= to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		} 
	}
	
	static int[] parent;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = Integer.parseInt(br.readLine());
			long[] x = new long[n];
			long[] y = new long[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				x[i]= Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}
			double e = Double.parseDouble(br.readLine());
			
			parent = new int[n];
			for(int i = 0; i < n; i++) {
				parent[i] = i;
			}
			
			List<Edge> edge = new ArrayList<>();
			
			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					long dx = x[i] - x[j];
					long dy = y[i] - y[j];
					
					long cost = dx * dx + dy * dy;
					edge.add(new Edge(i,j,cost));
				}
			}
			
			Collections.sort(edge);
			
			long cnt = 0;
			long answer = 0;
			
			for(int i = 0; i < edge.size(); i++) {
				Edge now = edge.get(i);
				if(find(now.to) != find(now.from)) {
					union(now.to,now.from);
					cnt++;
					answer += now.cost;
				}
				if(cnt == n-1)	break;
			}
			System.out.println("#" + test_case + " " + Math.round(answer * e));
		}
	}
	public static int find(int x) {
		if(parent[x] == x) {
	        return x;
	    }

	    parent[x] = find(parent[x]);
	    return parent[x];
	}
	
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] = a;
		}
	}
}