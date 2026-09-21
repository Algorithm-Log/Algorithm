import java.io.*;
import java.util.*;

public class Solution {
	static int N, X, M, note[][];
	static int max;
	static int[] result;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			note = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				note[i][0] = Integer.parseInt(st.nextToken());
				note[i][1] = Integer.parseInt(st.nextToken());
				note[i][2] = Integer.parseInt(st.nextToken());
			}
			
			max = -1;
			// 경우의 수를 대입할 배열
			int[] arr = new int[N+1];
			// 최댓값 갱신될 때마다 arr 배열을 result로 복사
			result = new int[N+1];
			dfs(arr, 1);
			
			sb.append('#').append(tc).append(' ');
			if(max == -1) {
				sb.append(-1);
			}else {
				for(int i = 1; i <= N; i++) {
					sb.append(result[i]).append(' ');
				}
			}
			sb.append('\n');
        }
        System.out.print(sb.toString());
	}
	
	private static void dfs(int[] arr, int depth) {
		// 다 채워지면
		if(depth == N+1) {
			boolean check = true;
			for (int i = 0; i < M; i++) {
				int l = note[i][0];
				int r = note[i][1];
				int s = note[i][2];
				
				int sum = 0;
				for (int j = l; j <= r; j++) {
					sum += arr[j];
				}
				if(sum != s) {
					check = false;
					break;
				}
			}
			
			if(check) {
				int sum = 0;
				for (int i = 1; i <= N; i++) {
					sum += arr[i];
				}
				
				if(max < sum) {
					max = sum;
					result = arr.clone();
				}
			}
			return;
		}
		
		// arr를 0~X까지 다 채워보기
		for(int i = 0; i <= X; i++) {
			arr[depth] = i;
			dfs(arr, depth+1);
		}
	}
}
