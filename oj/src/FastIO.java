import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

public class FastIO {
	BufferedReader br;
	StringTokenizer st;
	PrintWriter out;

	public FastIO(File in, File o) {
		try {
			br = new BufferedReader(new FileReader(in));
			out = new PrintWriter(new FileWriter(o));
		} catch (Exception e) {
			e.printStackTrace();
		}
		eat("");
	}

	public FastIO() {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		eat("");
	}

	public void eat(String s) {
		st = new StringTokenizer(s);
	}

	public String nextLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			while(true);
			//throw new IOError(e);
		}
	}

	public boolean hasNext() {
		while (!st.hasMoreTokens()) {
			String s = nextLine();
			if (s == null)
				return false;
			eat(s);
		}
		return true;
	}

	public String next() {
		hasNext();
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public void print(String ans) {
		out.print(ans);
		out.flush();
	}

	public void print(int ans) {
		out.print(ans);
		out.flush();
	}

	public void print(long ans) {
		out.print(ans);
		out.flush();
	}

	public void print(double ans) {
		out.print(ans);
		out.flush();
	}

	public void println(String ans) {
		out.println(ans);
		out.flush();
	}

	public void println(int ans) {
		out.println(ans);
		out.flush();
	}

	public void println(long ans) {
		out.println(ans);
		out.flush();
	}

	public void println(double ans) {
		out.println(ans);
		out.flush();
	}

	public void println() {
		println("");
		out.flush();
	}

	public void printf(String arg0, Object... arg1) {
		out.printf(arg0, arg1);
		out.flush();
	}

	public void println(int[] ans) {
		for (int i = 0; i < ans.length; i++)
			print(ans[i] + " ");
		println();
	}

	public void println(long[] ans) {
		for (int i = 0; i < ans.length; i++)
			print(ans[i] + " ");
		println();
	}

	public void println(double[] ans) {
		for (int i = 0; i < ans.length; i++)
			print(ans[i] + " ");
		println();
	}

	public void println(Object... ans) {
		for (Object cur : ans)
			print(cur.toString() + " ");
		println();
	}

	public void println(Object ans[][]) {
		for (int i = 0; i < ans.length; i++) {
			println(ans[i]);
		}
	}

	public void println(int ans[][]) {
		for (int i = 0; i < ans.length; i++) {
			println(ans[i]);
		}
	}

	public void println(long ans[][]) {
		for (int i = 0; i < ans.length; i++) {
			println(ans[i]);
		}
	}

	public void println(double ans[][]) {
		for (int i = 0; i < ans.length; i++) {
			println(ans[i]);
		}
	}

	public void println(List<Object> ans) {
		for (Object i : ans) {
			print(i.toString() + " ");
		}
		println();
	}

	public void close() {
		out.flush();
		out.close();
	}
}