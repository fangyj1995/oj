import java.util.*;
import java.util.Map.Entry;

public class S_Expression {
	static String[] elements;
	static Stack<Var> stack;
	private static class Var{
		String name;
		ExpVal val;
		public Var(String name, ExpVal val) {
			super();
			this.name = name;
			this.val = val;
		}
	}
	private static class ExpVal{
		int code;
		int val;
		
		public ExpVal(int code) {
			super();
			assert (code >= 1 || code <= 5);
			this.code = code;
		}

		public ExpVal(int code, int val) {
			super();
			assert code == 0;
			this.code = code;
			this.val = val;
		}
		public boolean isError(){
			return code >= 3;
		}
		public boolean isBool(){
			return code == 1 || code == 2;
		}
		public boolean isTrue(){
			assert isBool();
			return code == 1;
		}
		public boolean isFalse(){
			assert isBool();
			return code == 2;
		}
		@Override
		public String toString() {
			String str = null;
			switch(code){
				case 0:
					str = String.valueOf(val);break;
				case 1:
					str = "true";break;
				case 2:
					str = "false";break;
				case 3:
					str = "Type Mismatch";break;
				case 4:
					str = "Unbound Identifier";break;
				case 5:
					str = "Division By Zero";break;
			}
			return str;
		}
		
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		//System.out.println(n);
		scan.nextLine();
		String[] exps = new String[n];
		for(int i = 0 ; i < n ; i++){
			exps[i] = scan.nextLine();						
		}		
		for(int i = 0 ; i < n ;i++){
			stack = new Stack<>();
			elements = exps[i].split("\\s+");
			ExpVal val = getVal(0,elements.length - 1);
			System.out.println(val);
		}
		scan.close();
	}
	private static ExpVal getVal(int s, int e) {
		//System.out.println("当前区间: " + s +" , " + e);
		if(s == e){
			try{//number
				return new ExpVal(0,Integer.parseInt(elements[s]));
			}
			catch(NumberFormatException exp){
				if(elements[s].equals("true"))//bool
					return new ExpVal(1);
				else if(elements[s].equals("false"))//bool
					return new ExpVal(2);
				else
					return findVariable(elements[s]);//variable	
			}									
		}
		assert elements[s].equals("(");//s为(		
		//if a b c 运算
		if(elements[s + 1] .equals("if"))
			return ifabc(s + 1);		
		//let运算
		if(elements[s + 1].equals("let"))
			return let(s + 1);		
		//else, f x y运算
		else return fxy(s + 1);
		
	}
	// let ( x a ) b
	private static ExpVal let(int s) {
		assert elements[s].equals("let");
		int define_s = s + 1;
		int define_e = findRight(define_s);
		//变量名
		String varName = elements[define_s + 1];
		ExpVal val = getVal(define_s + 2, define_e - 1);
		if(val.isError()) return val;
		//System.out.println("push " + varName + " , " + val);
		stack.push(new Var(varName,val));
		
		int b_s = define_e + 1; int b_e = b_s;
		if(elements[b_s].equals("("))	b_e = findRight(b_s);
		ExpVal res = getVal(b_s, b_e);
		Var pop = stack.pop();
		//System.out.println("pop " + pop.name + " , " + pop.val);
		
		return res;
	}
	private static ExpVal ifabc(int s) {
		assert elements[s].equals("if");
		
		int a_s = s + 1;int a_e = a_s;
		if(elements[a_s].equals("("))	a_e = findRight(a_s);
		ExpVal a = getVal(a_s, a_e);
		if(a.isError()) return a;
		if(!a.isBool()) return new ExpVal(3);
		
		// 获取x和y，这里需要注意，先不计算x,y的值，只找出区间即可		
		int b_s = a_e + 1; int b_e = b_s;
		if(elements[b_s].equals("("))	b_e = findRight(b_s);
		int c_s = b_e + 1; int c_e = c_s;
		if(elements[c_s].equals("("))	c_e = findRight(c_s);
		
		if(a.isTrue()) return getVal(b_s, b_e);
		else 		   return getVal(c_s, c_e);
	}
	private static ExpVal fxy(int s) {
		String op = elements[s];		
		ExpVal x ; ExpVal y;
		
		int x_s = s + 1;int x_e = x_s;;//x的起点和终点
		if(elements[x_s].equals("("))// 此时x为一个式子，我们需要去找到该左括号对应的右括号
			x_e = findRight(x_s);
		x = getVal(x_s, x_e);
		if(x.isError()) return x;
			
		int y_s = x_e + 1;int y_e = y_s;//y的起点和终点
		if(elements[y_s].equals("("))// 此时y为一个式子，我们需要去找到该左括号对应的右括号
			y_e = findRight(y_s);
		y = getVal(y_s, y_e);
		if(y.isError()) return y;
		
		if(x.code != 0) return new ExpVal(3);
		if(y.code != 0) return new ExpVal(3);				
		ExpVal res = calFxy(op, x, y);
		return res;
	}
	private static ExpVal calFxy(String op, ExpVal x, ExpVal y) {
		assert x.code != 0 && y.code !=0 ;
		if(op.equals("/") && y.val == 0) return new ExpVal(5);
		switch(op){
		case "+":return  new ExpVal(0, x.val + y.val);
		case "-":return  new ExpVal(0, x.val - y.val);
		case "*":return  new ExpVal(0, x.val * y.val);
		case "/":return  new ExpVal(0, x.val / y.val);
		case ">":return  new ExpVal(x.val > y.val ? 1: 2);
		case "<":return  new ExpVal(x.val < y.val ? 1: 2);
		case "=":return  new ExpVal(x.val == y.val ? 1: 2);
		}
		throw new RuntimeException(op + " , op not right");
	}
	private static int findRight(int x_s) {
		assert elements[x_s].equals("(");
		int cnt = 0;
		for(int i = x_s ;i < elements.length; i++){
			if(elements[i].equals("(")) cnt++;
			if(elements[i].equals(")")) cnt--;
			if(cnt == 0) return i;
		}
		throw new RuntimeException("can not find ) for ( in pos "+ x_s);
	}
	private static ExpVal findVariable(String varName) {
		//System.out.print(varName+"　");	
		for(int i = stack.size() - 1;i >= 0; i--){
			Var var = stack.elementAt(i);
			if(var.name.equals(varName)) {
				//System.out.println(var.val);
				return var.val;
			}
		}
		return new ExpVal(4);
	}

}
