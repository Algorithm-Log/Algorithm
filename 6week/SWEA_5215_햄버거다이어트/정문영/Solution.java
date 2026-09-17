import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= tc; testcase++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			arr = new int[n][2];
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<2; j++) arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr, (a, b) -> a[1] - b[1]);
			
			int result = 0;
			for (int i = 0; i < n; i++) {
				result = Math.max(result, dfs(0, 0, i));
			}
			
			System.out.println("#" + testcase + " " + result);
		}
	}
	
	static int[][] arr;
	static int l;
	static int answer;
	
	static int dfs(int score, int c, int idx) {
		
		if (idx >= arr.length) return score;
		if (c + arr[idx][1] > l) return score;
		
		int take = dfs(score + arr[idx][0], c + arr[idx][1], idx+1);
		int skip = dfs(score, c, idx+1);
		
		return Math.max(take, skip);
	}
}
