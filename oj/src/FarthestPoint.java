import java.util.Scanner;
/*
 * 注意几点
 * 浮点数相等的比较
 * 比较距离的时候不必开根号
 * doubel取整不要直接强制转换
 */
public class FarthestPoint {

	public static void main(String[] args)  {
		double rx,ry,r;
		Scanner scan = new Scanner(System.in);
		rx = scan.nextDouble();
		ry = scan.nextDouble();
		r = scan.nextDouble();
		int lowx = (int)Math.ceil(rx-r);
		int highx = (int)Math.floor(rx+r);
		int resx = 0;int resy = 0;
		double maxDist = 0;
		for(int x = lowx; x<=highx ;x++){
			double ylength = Math.sqrt(r*r-(x - rx)*(x - rx));
			
			int highy = (int)Math.floor(ry+ylength);
			int lowy = (int)Math.ceil(ry-ylength);	
			
			double disthigh = dist(rx,ry,x,highy);
			double distlow = dist(rx,ry,x,lowy);
			
			int y;
			double dist;
			if(disthigh>distlow){
				y = highy;
				dist = disthigh;
			}
			else if(equals(disthigh,distlow)){
				y = highy;
				dist = disthigh;
			}
			else{
				y = lowy;
				dist = distlow;
			}
			//System.out.println("("+x+","+y+") "+dist);
			if(dist > maxDist)
			{
				maxDist = dist;
				resx = x;
				resy = y;
			}
			else if(equals(dist,maxDist)){
				if(x > resx){
					resx = x;
					resy = y;
				}
				else if(x == resx && y > resy){
					resx = x;
					resy = y;
				}
			}
		}
		System.out.println(resx+" "+resy);
	}
	static double dist(double x1,double y1,int x2,int y2){
		double dist = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
		return dist;
	}
	static boolean equals(double x,double y){
		return Math.abs(x - y) < 0.0001;
	}
}

