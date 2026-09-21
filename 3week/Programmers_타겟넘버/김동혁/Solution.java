class Solution {

    int[] numbers;
    int target;
    int answer;

    void dfs(int depth, int sum){

        if(depth==numbers.length){
            if(sum == target) answer++;
            return;
        }

        dfs(depth+1,sum+numbers[depth]);
        dfs(depth+1,sum-numbers[depth]);
    }

    public int solution(int[] numbers, int target) {
        answer = 0;
        this.numbers = numbers;
        this.target = target;

        dfs(0,0);

        return answer;
    }
}