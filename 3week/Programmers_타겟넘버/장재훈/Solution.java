class Solution {
    int[] numbers;
    int target;
    int answer;
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        findTargetNumber(0, 0);
        
        return answer;
    }
    
    // DFS
    public void findTargetNumber(int depth, int sum) {
        if (depth == numbers.length) {
            if (sum == target) answer++;
            return;
        }
        
        // 해당 depth에서 값을 더하는 경우
        findTargetNumber(depth+1, sum+numbers[depth]);
        // 해당 depth에서 값을 빼는 경우
        findTargetNumber(depth+1, sum-numbers[depth]);
    }
}