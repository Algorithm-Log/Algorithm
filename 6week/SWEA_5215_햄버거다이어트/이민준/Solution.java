import java.io.*;
import java.util.*;

public class Solution {
	static int kcal, n, max;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			kcal = Integer.parseInt(st.nextToken());
			
			arr = new int[n][2];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			max = 0;
			dfs(0, 0, 0);
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.print(sb);
	}
	public static void dfs(int depth, int sumP, int sumK) {
		// 숫자가 넘었을 때
		// 리턴
		if(sumK > kcal) return;
		
		// 배열 끝까지 돌았을 때
		// 최댓값 비교
		// 리턴
		if(depth >= arr.length) {
			max = Math.max(max, sumP);
			return;
		}
		
		dfs(depth + 1, sumP + arr[depth][0], sumK + arr[depth][1]);
		dfs(depth + 1, sumP, sumK);
	}
}
