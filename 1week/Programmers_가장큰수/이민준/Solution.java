import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        int len = numbers.length;
        String[] str = new String[len];
        for(int i = 0; i < len; i++){
            str[i] = String.valueOf(numbers[i]);
            // strArr[i] = Integer.toString(arr[i]);
        }

        // String[] strArr = Arrays.stream(arr)
        //                 .mapToObj(String::valueOf)
        //                 .toArray(String[]::new);

        Arrays.sort(str, (a,b) -> (b+a).compareTo(a+b));
        if(str[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for(String s : str){
            sb.append(s);
        }

        return sb.toString();
    }
}