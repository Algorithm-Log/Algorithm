import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			char[] numbers = br.readLine().toCharArray();
			
			Set<String> set = new HashSet<>();
			Deque<Character> que = new ArrayDeque<>();
			for(int i = 0; i < n; i++) {
				que.offer(numbers[i]);
			}
			
			int len = n / 4;
			StringBuilder s;
			for(int l = 0; l < len; l++) {
				Deque<Character> temp = new ArrayDeque<>(que);
				
				// len만큼 나눠서 set 넣기
				for(int i = 0; i <= n-len; i+=len) {
					s = new StringBuilder();
					for(int j = 0; j < len; j++) {
						s.append(temp.poll());
					}
					set.add(s.toString());
				}
					
				// 한칸씩 이동
				char c = que.pollLast();
				que.offerFirst(c);
			}
			
			int[] arr = new int[set.size()];
			int idx = 0;
			for(String num : set) {
				arr[idx++] = Integer.parseInt(num, 16);
			}
			
			Arrays.sort(arr);
			
			sb.append('#').append(tc).append(' ').append(arr[set.size() - k]).append('\n');
		}
		System.out.print(sb.toString());
	}
}
