import java.io.*;
import java.text.ParseException;
import java.util.*;
public class ConstraintChecker {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static class Con{
		String one;
		String two;
		boolean equal;//true为<=
		public String toString(){
			return one + (equal?"<=":"<")+ two;
		}
		boolean satisfied(Map<Character,Integer> map){
			int n1 = 0;
			int n2 = 0;
			
			if(map.containsKey(one.charAt(0))){
				n1 = map.get(one.charAt(0));
			}
			else{
				n1 = Integer.parseInt(one);
			}
			
			if(map.containsKey(two.charAt(0))){
				n2 = map.get(two.charAt(0));
			}
			else{
				n2 = Integer.parseInt(two);
			}
			
			if(equal){
				return n1 <= n2;
			}
			else 
				return n1 < n2;
		}
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		int n = Integer.parseInt(reader.readLine());
		LinkedList<Con> list = new LinkedList<Con>();	
		int varCnt = 0;
		int[] vars = new int[26];
		
		for(int i = 0 ; i < n ; i++){							
			String con = reader.readLine();
			String[] tokens = con.split("<");
			for(int j = 0 ; j < tokens.length; j++){
				if((tokens[j].length() == 1 && !Character.isDigit(tokens[j].charAt(0))))
				{
					char c = tokens[j].charAt(0);
					if(vars[c-'A'] == 0){
						varCnt++;	
						vars[c-'A'] = 1;
					}
				}
				else if((tokens[j].length() == 2 && tokens[j].charAt(0) == '=' && !Character.isDigit(tokens[j].charAt(1))))
				{
					char c = tokens[j].charAt(1);
					if(vars[c-'A'] == 0){
						varCnt++;	
						vars[c-'A'] = 1;
					}
				}
			}
			for(int j = 0 ; j < tokens.length-1 ; j++){
				String a = tokens[j];
				String b = tokens[j+1];			
				Con c = new Con();//j,j+1
				if(a.charAt(0) == '=')
					c.one = a.substring(1);
				else 
					c.one = a;
				if(b.charAt(0) == '=')
				{
					c.two = b.substring(1);
					c.equal = true;
				}
				else 
					c.two = b;			
				list.add(c);
			}
		}
		int m = Integer.parseInt(reader.readLine());
	
		for(int j = 0 ; j < m ;j++){
			HashMap<Character,Integer> map = new HashMap<>();
			
			for(int k = 0 ; k < varCnt ; k++){
				char var = (char)reader.read();
				int val = Integer.parseInt(reader.readLine().trim());
				map.put(var, val);
			}
			
			boolean satisfied = true;
			for(int k = 0 ; k < list.size() ; k++){
				if(!list.get(k).satisfied(map)){
					satisfied = false;
					break;
				}
			}
			if(satisfied)
				System.out.println("Yes");
			else
				System.out.println("No");
		}

	}

}

