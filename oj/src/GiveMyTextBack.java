import java.util.LinkedList;
import java.util.Scanner;

public class GiveMyTextBack {
	public static void textBack(String s){
        LinkedList<Character> queue = new LinkedList<>();
        queue.offer(Character.toUpperCase(s.charAt(0)));
        for(int i = 1 ; i < s.length() ; i++){
            char last = queue.getLast();
            char cur = Character.toLowerCase(s.charAt(i));
            if(cur == ' ' && (last == ' ' || last == '.')){
                continue;
            }
            else if((cur == ',' || cur == '.')&& last == ' '){
                queue.removeLast();
            }
            else if(last == '.'){
            	queue.offer(' ');
                cur = Character.toUpperCase(cur);
            }
            queue.offer(cur);
        }
        for(Character c : queue){
            System.out.print(c);
        }
        System.out.println();       
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
        	String s = scan.nextLine();
            textBack(s);
        }     
        //System.out.println(textBack(s));
    }
}
