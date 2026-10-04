import java.io.*;
import java.util.*;

public class Solution {
	static int n, b, min;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken()); // 점원 수
			b = Integer.parseInt(st.nextToken()); // 선반 높이
			
			// 점원 키 받을 배열
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)  // 점원 키 입력
				arr[i] = Integer.parseInt(st.nextToken());
			
			min = Integer.MAX_VALUE;
			dfs(0, 0);
			
			sb.append('#').append(tc).append(' ').append(min-b).append('\n');
		}
		System.out.print(sb);
	}
	public static void dfs(int depth, int sum) {
		// 선반 높이보다 높으면서 최소값
		if(sum >= b) min = Math.min(min, sum);
		
		// 배열의 길이 넘으면 return
		if(depth == n) return;
		
		// 해당 위치의 키를 더한 경우
		dfs(depth+1, sum + arr[depth]);
		// 더하지 않은 경우
		dfs(depth+1, sum);
	}
}
