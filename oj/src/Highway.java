import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Highway {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}   
	static class Car {
		int start;
		int end;
		int v;
		double ans;
		double time;//前面的车经过本车终点的最晚时间
		
		public Car(int start, int end, int v) {
			super();
			this.start = start;
			this.end = end;
			this.v = v;
		}
	}
	static int n;
	static Car[] carx;
	static Car[] ori;
	static Car[] cary ;
	static DecimalFormat df = new DecimalFormat( "#.00 ");      
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		carx = new Car[n];
		cary = new Car[n];
		ori = new Car[n];
		for(int i = 0 ; i < n ; i++){
			Car c = new Car(nextInt(),nextInt(),nextInt());
			carx[i] = c;
			cary[i] = c;
			ori[i] = c;
		}
		Arrays.sort(carx, new Comparator<Car>(){
			@Override
			public int compare(Car o1, Car o2) {
				return o1.start - o2.start;
			}			
		});
		Arrays.sort(cary, new Comparator<Car>(){
			@Override
			public int compare(Car o1, Car o2) {
				return o1.end - o2.end;
			}			
		});
		for(int i = n - 1 ; i >= 0 ; i--){//从最前面的车开始处理
			double t = 0.00;//i按起点排序
			int cur = carx[i].start;
			double v = carx[i].v;
			for(int j = 0 ; j <= n - 1 ; j++){//j按终点排序
				if(cur < cary[j].end){//把i开到j的终点，在j中记录i到达的时间
					//把i开到j的终点，在j中记录i到达的时间
					t += (cary[j].end - cur)/v;		
					t = Math.max(cary[j].time, t);
					cary[j].time = t;		 			
					cur = cary[j].end;
					if(cur == carx[i].end){//i开到了自己的终点，结束
						carx[i].ans = t;
						break;
					}
				}			
			}			
		}
		for(int i = 0 ; i < n; i++){
			System.out.println(String.format("%.2f", ori[i].ans));
		}
	}

	
}
