package hihocoder;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class 闰秒 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
    
	static DateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static String[] times = new String[]{
			"1972-06-30 23:59:60", "1972-12-31 23:59:60",
			"1973-12-31 23:59:60",
			"1974-12-31 23:59:60",
			"1975-12-31 23:59:60",
			"1976-12-31 23:59:60",
			"1977-12-31 23:59:60",
			"1978-12-31 23:59:60",
			"1979-12-31 23:59:60",
			"1981-06-30 23:59:60",
			"1982-06-30 23:59:60",
			"1983-06-30 23:59:60",
			"1985-06-30 23:59:60",
			"1987-12-31 23:59:60",
			"1989-12-31 23:59:60",
			"1990-12-31 23:59:60",
			"1992-06-30 23:59:60",
			"1993-06-30 23:59:60",
			"1994-06-30 23:59:60",
			"1995-12-31 23:59:60",
			"1997-06-30 23:59:60",
			"1998-12-31 23:59:60",
			"2005-12-31 23:59:60",
			"2008-12-31 23:59:60",
			"2012-06-30 23:59:60",
			"2015-06-30 23:59:60",
			"2016-12-31 23:59:60",
	};
 	public static void main(String[] args) throws IOException, ParseException{
 		String s = reader.readLine();
 		String e = reader.readLine();
 		Date start = df.parse(s);
 		Date end = df.parse(e); 		
 		long seconds = (end.getTime()-start.getTime())/1000;
 		for(int i = 0 ; i < times.length; i++){
 			if(times[i].compareTo(s) >= 0 && times[i].compareTo(e) < 0) 
 				seconds++;
 		}
 		System.out.println(seconds);
	}


	
	
}
