import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class IndividualIncomeTax {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int tax;
	static int salary = 3500;
	public static void main(String[] args) throws IOException, ParseException {
		tax = nextInt();
		solve();
		System.out.println(salary);
	}
	private static void solve() {
		if(tax <= 45){
			salary += Math.floor(tax/0.03);
		}
		else if(tax <= 345){
			salary += 1500;
			salary += Math.floor((tax-45)/0.1);
		}
		else if(tax <= 1245){
			salary += 4500;
			salary += Math.floor((tax-345)/0.2);
		}
		else if(tax <= 7745){
			salary += 9000;
			salary += Math.floor((tax-1245)/0.25);
		}
		else if(tax <= 13745){
			salary += 35000;
			salary += Math.floor((tax-7745)/0.3);
		}
		else if(tax <= 22495){
			salary += 55000;
			salary += Math.floor((tax-13745)/0.35);
		}
		else{
			salary += 80000;
			salary += Math.floor((tax-22495)/0.45);
		}
	}

}

