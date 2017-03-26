package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2017/1/17.
 */
public class _17LetterCombinationsofaPhoneNumber {
    char[][] map=new char[][]
            {
                    {},//0
                    {},//1
                    {'a','b','c'},//2
                    {'d','e','f'},//3
                    {'g','h','i'},//4
                    {'j','k','l'},//5
                    {'m','n','o'},//6
                    {'p','q','r','s'},//7
                    {'t','u','v'},//8
                    {'w','x','z','y'}//9
            };
    List<String> res=new ArrayList<String>();
    StringBuilder str=new StringBuilder("");
    public List<String> letterCombinations(String digits) {//2345
        if(digits.length()==0) return res;
        dfs(digits,0);
        return res;
    }
    private void dfs(String digits,int depth){
        if(depth==digits.length()){
            res.add(str.toString());
            return;
        }
        int num=digits.charAt(depth)-'0';
        for(char c:map[num]){
            str.append(c);
            dfs(digits,depth+1);
            str.deleteCharAt(str.length()-1);
        }
    }
}
class Solution {
    char[][] map=new char[][]
            {
                    {},//0
                    {},//1
                    {'a','b','c'},//2
                    {'d','e','f'},//3
                    {'g','h','i'},//4
                    {'j','k','l'},//5
                    {'m','n','o'},//6
                    {'p','q','r','s'},//7
                    {'t','u','v'},//8
                    {'w','x','z','y'}//9
            };
    public List<String> letterCombinations(String digits) {//2345
        List<String> res=new ArrayList<String>();
        if(digits.length()==0) return res;
        res.add("");
        for(int i=0;i<digits.length();i++){
            List<String> sub=new ArrayList<String>();
            int num=digits.charAt(i)-'0';
            for(String s:res){
                for(char c:map[num]){
                    sub.add(s+c);
                }
            }
            res=sub;
        }
        return res;
    }
}