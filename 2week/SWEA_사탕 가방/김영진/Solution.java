import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());


		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			long m = Long.parseLong(st.nextToken());
			
			long[] arr = new long[n];
			
			long ans = 0;
			long left = 1;
			long right = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Long.parseLong(st.nextToken());
				right = Math.max(right, arr[i]);
			}
			
			while(left <= right) {
				long mid = (left + right) / 2;
				
				long sum = 0;
				
				for(int i = 0; i < n; i++) {
					sum += arr[i] / mid;
				}
				if(sum >= m) {
					ans = mid;
					left = mid + 1;
				}
				else right = mid -1;
				
			}
			System.out.println("#" + test_case + " " + ans);
		}
	}
}