import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        String[][] record = new String[records.length][3];
        Map<String,Integer> in = new HashMap<>();
        Map<String,Integer> total = new HashMap<>();
        
        for(int i = 0; i < records.length; i++){ //1차원 배열을 2차원 배열에 저장
            StringTokenizer st = new StringTokenizer(records[i]);
            record[i][0] = st.nextToken();
            record[i][1] = st.nextToken();
            record[i][2] = st.nextToken();
            if(record[i][2].equals("IN")){
                in.put(record[i][1], toNum(record[i][0]));
            }
            else{ //Out
                int outTime = toNum(record[i][0]);
                int inTime = in.get(record[i][1]);
                in.remove(record[i][1]);
                int parkTime = outTime - inTime;
                
                total.put(record[i][1],total.getOrDefault(record[i][1], 0) + parkTime);
            }
        }
        if(!in.isEmpty()){
          for(String s : in.keySet()){
              int parkTime = toNum("23:59") - in.get(s);
              
              total.put(s, total.getOrDefault(s, 0) + parkTime);
          }  
        }
        List<String> list = new ArrayList<>(total.keySet());
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            int totalTime = total.get(list.get(i));
            if(totalTime <= fees[0]){
                answer[i] = fees[1];
            }
            else{
                int t = totalTime - fees[0]; //계산해야할 시간
                int per = (int)Math.ceil((double)t / fees[2]);//단위 계산
                answer[i] = fees[1] + per * fees[3];
            }
        }
        
        return answer;
    }
    public static int toNum(String n){
        String[] t = n.split(":");
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);
        
        return (h * 60) + m;
    }
}