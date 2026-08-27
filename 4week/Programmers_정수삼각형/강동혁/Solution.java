//int[][] 을 만들어서 
// 위에 두개 노드 에서 값을 내려받을려면
// 값을 받으려는 노드의 course[인덱스] + course[인덱스-1]
// 근데 인덱스가 0이거나 col(길이) 값과 같으면 예외가 발생하니
// 0일 때,col 일떄, 둘다 아닐때 로 구간을 3가지로 나눠서
// 각 노드까지 도달할 수 있는 최대 경로 비용만 저장한다
// 제일 아래에 있는 노드에서 최댓값을 구한후 return 
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int col = triangle.length;
        int[][] course = new int[triangle.length][];
    

        for(int i=0;i<col;i++){
            course[i]=new int[i+1];
        }

        course[0][0]=triangle[0][0];

        for(int y=1;y<col;y++){
            for(int x=0;x<=y;x++){

                if(x==0){
                    // 제일 왼쪽
                    course[y][x]=triangle[y][x]+course[y-1][x];

                }else if(x==y){
                    // 제일 오른쪽 
                    course[y][x]=triangle[y][x]+course[y-1][x-1];

                }else{
                    // 아닐때
                    int temp=triangle[y][x]+course[y-1][x];

                    // 둘중에 누가 더큰지
                    if(temp>triangle[y][x]+course[y-1][x-1]){

                        course[y][x]=temp;

                    }else{

                        course[y][x]=triangle[y][x]+course[y-1][x-1];

                    }
                }
                //System.out.println(course[y][x]);
            }
        }

        for(int i=0;i<col;i++){
            answer = Math.max(course[col-1][i],answer); 
        }

        return answer;


    }
}