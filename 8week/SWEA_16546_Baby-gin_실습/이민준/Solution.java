import java.io.*;
import java.util.*;

public class Solution {
	static boolean[] check;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			check = new boolean[10];
			arr = new int[10];
			
			char[] nums = br.readLine().toCharArray();
			for (int i = 0; i < 6; i++) {
				int num = nums[i] - '0';
				arr[num]++;
			}
			
			run();
			for (int i = 1; i < 8; i++) {
				triplet(i);
			}
			
			boolean result = true;
			for(char n : nums) {
				int num = n - '0';
				if(!check[num]) {
					result = false;
					break;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	private static void triplet(int idx) {
		int nextIdx = idx + 1;
		int nextNextIdx = idx + 2;
		
		if(arr[idx] > 0 && arr[nextIdx] > 0 && arr[nextNextIdx] > 0) {
			check[idx] = check[nextIdx] = check[nextNextIdx] = true;
		}
	}
	
	private static void run() {
		for (int i = 0; i < 10; i++) {
			if(arr[i]>=3) check[i] = true;
		}
	}
}
