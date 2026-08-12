import java.io.*;
import java.util.*;

public class Solution {
	static long m; // 1,000,000,000,000,000,000
	static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long answer = 0;
			int n;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Long.parseLong(st.nextToken());

			arr = new long[n];
			st = new StringTokenizer(br.readLine());
			long start, end;
			start = 1;
			end = Long.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				arr[i] = Long.parseLong(st.nextToken());
				end = Math.max(end, arr[i]);
			}

			while (start <= end) {
				long mid = (start + end) / 2;

				if (func(mid)) {
					answer = mid;
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static boolean func(long mid) {
		long sum = 0;
		for (long num : arr) {
			sum += num / mid;

			if (sum >= m)
				return true;
		}
		return false;
	}
}
