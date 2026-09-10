import java.util.*;
import java.io.*;

class Solution
{
	static int n,b,min;
	static int[] arr;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++){
				arr[i] = Integer.parseInt(st.nextToken());
			}
			min = Integer.MAX_VALUE;
			dfs(0,0);
			System.out.println("#" + test_case + " " + min);
		}
	}
	public static void dfs(int index, int sum){
		if(index == n){
			if(sum >= b){
				min = Math.min(min, (sum-b));
				return ;
			}
			return ;
		}
		//더하는 경우
		// if(!visited[index]){
		// 	visited[index] = true;
		// 	dfs(index + 1, sum + arr[index]);
		// 	visited[index] = false;
		// }

		dfs(index + 1, sum + arr[index]);
		//더하지 않는경우
			dfs(index + 1, sum);
	}
}