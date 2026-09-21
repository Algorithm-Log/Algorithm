package com.ssafy.algorithm;

class Solution {

    int[] diffs;
    int[] times;
    long limit;

    boolean possible(int level) {

        // 첫 번째 퍼즐은 diffs[0] = 1이고
        // level >= 1이므로 무조건 한 번에 성공
        long totalTime = times[0];

        for (int i = 1; i < diffs.length; i++) {

            int diff = diffs[i];
            int timeCur = times[i];
            int timePrev = times[i - 1];

            if (diff > level) {

                // 틀리는 횟수
                int fail = diff - level;

                // 틀릴 때마다 현재 퍼즐 + 이전 퍼즐 시간
                // 마지막에는 현재 퍼즐을 한 번 더 풀어 성공
                totalTime += (long) fail * (timeCur + timePrev) + timeCur;

            }
            else {

                // 숙련도가 충분하면 한 번에 성공
                totalTime += timeCur;
            }

            // 이미 제한 시간을 넘었다면 더 계산할 필요 없음
            if (totalTime > limit) {
                return false;
            }
        }

        return true;
    }

    public int solution(int[] diffs, int[] times, long limit) {

        this.diffs = diffs;
        this.times = times;
        this.limit = limit;

        int maxDiff = 0;

        for (int diff : diffs) {
            maxDiff = Math.max(maxDiff, diff);
        }

        // 숙련도의 탐색 범위
        int left = 1;
        int right = maxDiff;

        // maxDiff는 무조건 가능한 숙련도
        int answer = maxDiff;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (possible(mid)) {

                // 현재 숙련도는 가능
                answer = mid;

                // 더 낮은 숙련도도 가능한지 탐색
                right = mid - 1;

            } else {

                // 숙련도가 부족하므로 높여야 함
                left = mid + 1;
            }
        }

        return answer;
    }
}