import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= tc; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			b = Long.parseLong(st.nextToken());
			height = new Integer[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) height[i] = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[n];
			long result = Long.MAX_VALUE;
			
			Arrays.sort(height, Collections.reverseOrder());  // 빠른 탐색을 위한 내림차순 정렬
			
			for (int i = 0; i < visited.length; i++) {
				result = Math.min(result,  dfs(0, i));
			}
			System.out.println("#" + testcase + " " + (result - b));
		}
	}
	
	static long b;
	static Integer[] height;
	
	static long dfs(long s, int idx) {
	    if (s >= b) return s;
	    if (idx == height.length) return Long.MAX_VALUE;

	    long take = dfs(s + height[idx], idx + 1);   // idx번째를 뽑는 경우
	    long skip = dfs(s, idx + 1);                  // idx번째를 안 뽑는 경우

	    return Math.min(take, skip);
	}
}
