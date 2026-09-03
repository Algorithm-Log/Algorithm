class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                arr[i][j] = num++;
            }
        }
        
        for(int i = 0; i < queries.length; i++){
            answer[i] = change(queries[i][0],queries[i][1],queries[i][2],queries[i][3],arr);
        }
        
        return answer;
    }
    public static int change(int st_x, int st_y, int end_x, int end_y, int[][] arr){
        st_x--;
        st_y--;
        end_x--;
        end_y--;
        
        int tmp = arr[st_x][st_y];
        int min = tmp;
        
        //왼쪽변, 밑에서 위로
        for(int i = st_x; i < end_x; i++){
            arr[i][st_y] = arr[i+1][st_y];
            min = Math.min(min, arr[i][st_y]);
        }
        //아래변, 오른쪽에서 왼쪽
        for(int i = st_y; i < end_y ; i++){
            arr[end_x][i] = arr[end_x][i + 1];
            min = Math.min(min, arr[end_x][i]);
        }
        //오른쪽변, 위에서 밑으로
        for(int i = end_x; i > st_x ; i--){
            arr[i][end_y] = arr[i - 1][end_y];
            min = Math.min(arr[i][end_y], min);
        }
        //윗변, 왼쪽에서 오른쪽
        for(int i = end_y; i > st_y+1; i--){
            arr[st_x][i] = arr[st_x][i-1];
            min = Math.min(min, arr[st_x][i]);
        }
        arr[st_x][st_y + 1] = tmp;

return min;
    }
}