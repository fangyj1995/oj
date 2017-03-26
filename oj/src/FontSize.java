/**
 * Created by fangyj on 2017/2/20.
 */
import java.util.*;
public class FontSize{
    int n , p , w, h;
    int[] charNum;
    public FontSize(int n, int p, int w, int h){
        this.n = n;
        this.p = p;
        this.w = w;
        this.h = h;
        charNum = new int[n];
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();
        for(int i = 0 ; i < cases; i++){
            int n , p , w, h;
            n = scan.nextInt();
            p = scan.nextInt();
            w = scan.nextInt();
            h = scan.nextInt();
            FontSize main = new FontSize(n, p, w, h);
            for(int j = 0 ; j < n; j++){
                main.charNum[j] = scan.nextInt();
            }
            int res = main.solve();
            System.out.println(res);
        }
    }
    public int solve(){
        int font = 1;
        while(true){
            int maxCol = w/font;
            int maxRow = h/font;
            if(maxCol == 0 || maxRow == 0) return font - 1;
            int curRow = 0;
            for(int i = 0 ; i < n; i++){
                int num = charNum[i];
                curRow += (num/maxCol + (num%maxCol == 0 ? 0 : 1 ));
            }
            if(curRow/maxRow + (curRow%maxRow == 0 ? 0 : 1) > p){
                return font - 1;
            }
            font++;
        }
    }
}