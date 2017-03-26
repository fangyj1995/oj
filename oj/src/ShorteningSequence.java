import java.util.*;
import java.io.*;
public class ShorteningSequence{
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
    
    public static void main(String[] args) throws IOException{
        int n = nextInt();
        if(n == 1)
        {
            System.out.println(1);
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(nextInt());
        for(int i = 1 ; i <= n - 1; i++ ){
            int a = nextInt();
            if(stack.empty()){
            	stack.push(a);
            }
            else if((stack.peek()+a)%2 == 1){
                stack.pop();
            }
            else
                stack.push(a);
        }
        System.out.println(stack.size());
    }
    
}
