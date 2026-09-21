class Solution {
    public int solution(String s) {
        char[] arr = s.toCharArray();
        int size = arr.length;

        boolean[][] ok = new boolean[size][size];

        // 길이 1 + 길이 2 초기화
        for (int i = 0; i < size; i++) {
            ok[i][i] = true;

            if (i + 1 < size && arr[i] == arr[i + 1]) {
                ok[i][i + 1] = true;
            }
        }

        // 각 위치를 중심으로 확장
        for (int i = 0; i < size; i++) {

            // 홀수 길이 팰린드롬
            for (int left = i - 1, right = i + 1;
                 left >= 0 && right < size;
                 left--, right++) {

                if (arr[left] == arr[right]
                        && ok[left + 1][right - 1]) {

                    ok[left][right] = true;
                }
            }

            // 짝수 길이 팰린드롬
            for (int left = i, right = i + 1;
                 left >= 0 && right < size;
                 left--, right++) {

                if (arr[left] == arr[right]
                        && ok[left + 1][right - 1]) {

                    ok[left][right] = true;
                }
            }
        }

        int answer = 1;

        for (int left = 0; left < size; left++) {
            for (int right = left; right < size; right++) {

                if (ok[left][right]) {
                    int len = right - left + 1;
                    answer = Math.max(answer, len);
                }
            }
        }

        return answer;
    }
}