package bj.g5.b3649;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;

		// EOF까지 테스트 케이스 반복
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) continue;   // 빈 줄 방어

			int x = Integer.parseInt(line);
			int n = Integer.parseInt(br.readLine().trim());
			int[] arr = new int[n];
			int targetValue = x * 10000000;

			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(br.readLine().trim());
			}
			Arrays.sort(arr);

			int start = 0;
			int end = n - 1;
			boolean check = false;

			while (start < end) {
				int num = arr[start] + arr[end];
				if (num == targetValue) {
					sb.append("yes ").append(arr[start]).append(' ').append(arr[end]).append('\n');
					check = true;
					break;
				} else if (num > targetValue) {
					end--;
				} else {
					start++;
				}
			}

			if (!check) sb.append("danger\n");
		}

		System.out.print(sb);
	}
}