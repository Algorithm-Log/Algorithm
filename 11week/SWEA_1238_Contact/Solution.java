import java.util.*;
import java.io.*;

class Solution
{
  static int[] visited;
  static List<Integer>[] list;
  static Queue<Integer> q;
  
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());
      
      visited = new int[101];
      list = new List[101];
      q = new LinkedList<>();
      
      for (int i = 0; i < 101; i++) {
        list[i] = new ArrayList<>();
      }
      
      Arrays.fill(visited, 0);
      
      st = new StringTokenizer(br.readLine());
      for(int i = 0; i < n/2; i++){
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        list[from].add(to);
      }
      bfs(start);
      int maxL = 0;
      int answer = 0;
      
      for(int i = 1; i < 101; i++) {
    	  if(visited[i] > maxL) {
    		  maxL = visited[i];
    		  answer = i;
    	  }
    	  else if(visited[i] == maxL) {
    		  answer = Math.max(answer, i);
    	  }
      }
      StringBuilder sb = new StringBuilder();
      sb.append("#" + test_case + " " + answer);
      System.out.println(sb);
		}
	}
  public static void bfs(int start){
	  q.offer(start);
	  visited[start] = 1;
	  
    while (!q.isEmpty()) {
      int now = q.poll();
      	for(int next : list[now]) {
      		if(visited[next] != 0) {
      			continue;
      		}
      		visited[next] = visited[now] + 1;
      		q.offer(next);
      	}
    }
  }
}