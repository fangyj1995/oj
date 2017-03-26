import java.util.*;

/**
 * Created by fangyj on 2017/2/19.
 */
public class MagicBox{
    static int[] xyz = new int[3];
    static int[] count = new int[3];
    static int max = 0;
    static int cnt = 0;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        xyz[0] = scan.nextInt();
        xyz[1] = scan.nextInt();
        xyz[2] = scan.nextInt();
        Arrays.sort(xyz);
        scan.nextLine();
        String str = scan.nextLine();
        System.out.println(str+"　"+str.length());
        for(int i = 0 ; i < str.length(); i++){
            char c = str.charAt(i);
            update(c);
        }
        System.out.println(max);
    }
    public static void update(char c){
        System.out.println(c);
        switch(c){
            case 'R':count[0]++;break;
            case 'Y':count[1]++;break;
            case 'B':count[2]++;break;
        }
        cnt++;
        max = Math.max(max , cnt);
        if(checkDiff()) {
            cnt = 0;
            count = new int[3];
        }
        System.out.println("cnt： "+cnt+", max: "+max);
    }
    public static boolean checkDiff(){
        int diff[] = new int[3];
        diff[0] = Math.abs(count[0] - count[1]);
        diff[1] = Math.abs(count[1] - count[2]);
        diff[2] = Math.abs(count[2] - count[0]);
        Arrays.sort(diff);
        System.out.println(Arrays.toString(diff));
        return Arrays.equals(diff, xyz);
    }
}
