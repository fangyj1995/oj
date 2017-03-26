import java.util.*;

public class Coordinates {
	public  static void coordinates(int p , int q){
        List<Integer> pd = new LinkedList<Integer>();
        List<Integer> qd = new LinkedList<Integer>();
        int divisor = 1;
        while(divisor <= p){
            if(p%divisor == 0) pd.add(divisor);
            divisor++;
        }
        divisor = 1;
        while(divisor <= q){
            if(q%divisor == 0) qd.add(divisor);
            divisor++;
        }
        for(Integer _p : pd){
            for(Integer _q : qd)
                System.out.println(_p + " " +_q);
        }
    }
    public  static void main(String[] args){
        int p,q;
        Scanner scan = new Scanner(System.in);
        p = scan.nextInt();
        q = scan.nextInt();
        scan.close();
        coordinates(p,q);
    }
}
