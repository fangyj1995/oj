import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/*
 * 涉及时间的计算
 * 由相差的秒数转化为相差的时间：
 * 		int diff = getTime(end)-getTime(start);
		int hh = diff/(3600);  
		int mm = (diff-hh*3600)/60;
		int ss = (diff - hh*3600 - mm*60);	
 */
public class PerformanceLog {
	static class Func{
		String name;
		String time;
		String action;
		public Func(String name, String time, String action) {
			super();
			this.name = name;
			this.time = time;
			this.action = action;
		}		
	}
	static DecimalFormat nf = new DecimalFormat("00");
	public static void main(String[] args) throws IOException, ParseException {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Stack<Func> stack = new Stack<>();
		Queue<String> queue = new LinkedList<>();
		Map<String,String> map = new HashMap<>();
		String lastTime = null;
		for(int i = 0 ; i < n ; i++){
			scan.nextLine();
			String name = scan.next();
			String time = scan.next();
			String action = scan.next();
			if(lastTime != null&&!checkTime(lastTime , time)){
				System.out.println("Incorrect performance log");
				return;
			}
			if(stack.isEmpty()){
				if(action.equals("START")){
					stack.push(new Func(name,time,action));
					queue.offer(name);
				}
				else{
					System.out.println("Incorrect performance log");
					return;
				}
			}
			else if(stack.peek().name.equals(name)){
					if(stack.peek().action.equals("START")&&action.equals("END"))
					{
						Func f = stack.pop();
						String totaltime = calTime(f.time,time);
						if(totaltime == null) {
							System.out.println("Incorrect performance log");
							return;
						}
						map.put(name, totaltime);
					}
			}
			else if(action.equals("START")){
				stack.push(new Func(name,time,action));
				queue.offer(name);
			}
			else{
				System.out.println("Incorrect performance log");
				return;
			}
		}
		if(!stack.isEmpty()){
			System.out.println("Incorrect performance log");
			return;
		}
		for(String func:queue){
			System.out.println(func+" "+map.get(func));
		}
	}
	private static boolean checkTime(String lastTime, String time) throws ParseException{
		int diff = getTime(lastTime)-getTime(time);
		return diff <= 0;
	}
	private static int getTime(String time){
		String[] t = time.split(":");
		int hh = Integer.parseInt(t[0]);
		int mm = Integer.parseInt(t[1]);
		int ss = Integer.parseInt(t[2]);
		return hh*3600+mm*60+ss;
	}
	private static String calTime(String start, String end) throws ParseException {
		int diff = getTime(end)-getTime(start);
		if(diff < 0) return null;
		int hh = diff/(3600);  
		int mm = (diff-hh*3600)/60;
		int ss = (diff - hh*3600 - mm*60);	
		return nf.format(hh)+":"+nf.format(mm)+":"+nf.format(ss);
	}
	
}
