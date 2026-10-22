import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			char[] text = br.readLine().toCharArray();
			char[] pattern = br.readLine().toCharArray();

			int[] pi = new int[pattern.length];

			// =========================
			// 1. pi 배열 전처리
			// =========================
			int j = 0;

			for (int i = 1; i < pattern.length; i++) {

				while (j > 0 && pattern[i] != pattern[j]) {
					j = pi[j - 1];
				}

				if (pattern[i] == pattern[j]) {
					pi[i] = ++j;
				}
			}

			// =========================
			// 2. KMP 탐색
			// =========================
			int count = 0;

			// pi 만들 때 사용했던 j이므로 다시 초기화
			j = 0;

			for (int i = 0; i < text.length; i++) {

				// 현재 문자가 다르면
				// pi 배열을 이용해서 j를 뒤로 이동
				while (j > 0 && text[i] != pattern[j]) {
					j = pi[j - 1];
				}

				// 현재 문자가 같다면
				if (text[i] == pattern[j]) {

					// 패턴의 마지막 문자까지 일치했다면
					if (j == pattern.length - 1) {

						count++;

						// 다음 패턴 탐색을 위해 이동
						// 겹치는 패턴도 찾을 수 있음
						j = pi[j];

					} else {
						j++;
					}
				}
			}

			sb.append('#')
			  .append(tc)
			  .append(' ')
			  .append(count)
			  .append('\n');
		}

		System.out.print(sb);
	}
}