import java.util.*;
import java.io.*;

class Solution
{
	static String str;
	static char[] arr;
	static int change,max;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			arr = str.toCharArray();
			change = Integer.parseInt(st.nextToken());
			max = 0;
			
			dfs(0,0);
			System.out.println("#" + test_case + " " + max);
		}
	}
	static void dfs(int idx, int cnt) {
		if(cnt == change) {
			String str = new String(arr);
			max = Math.max(max, Integer.parseInt(str));
			return;
		}
		for(int i = idx; i < str.length() - 1; i++) {
			for(int j = i + 1; j < str.length(); j++) {
				swap(i,j);
				dfs(i, cnt + 1);
				swap(i,j);
			}
		}
	}
	static void swap(int i , int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}