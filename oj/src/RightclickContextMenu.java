import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class RightclickContextMenu {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	static int n;
	static class Section{
		List<Row> rows;
		int rowNum;
		int height;		
	}
	static class Row{
		int height = 1;
		List<Section> sections;
		public Row(){
			sections = new ArrayList<>();
		}
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		Row root;
		int k = nextInt();
		if(k != 0)
			root = initRowPanel(k);
		else
			root = new Row();
		
		System.out.println(root.height);
	}
	public static Row initRowPanel(int k) throws IOException{
		int cnt = 0;
		Row resRow = new Row();
		Section sec = new Section();
		int rowNum = 0;
		while(cnt < k){
			int id = nextInt();
			if(id != 0){
				cnt++;
				rowNum++;
			}
			else{
				sec.rowNum = rowNum;
				resRow.sections.add(sec);
				sec = new Section();
				rowNum = 0;
			}
		}
		sec.rowNum = rowNum;
		resRow.sections.add(sec);
		
		for(Section section: resRow.sections){			
			section.rows = new ArrayList<>(sec.rowNum);
			for(int i = 0 ; i < section.rowNum; i++){//输入
				int kk = nextInt();
				if(kk!=0) section.rows.add(initRowPanel(kk));
				else      section.rows.add(new Row());			
			}
			Collections.sort(section.rows,new Comparator<Row>(){//对一个Section内的Row排序
				@Override
				public int compare(Row o1, Row o2) {
					return -(o1.height - o2.height);
				}		
			});	
			int secHeight = 0;
			for(int i = 0 ; i < section.rowNum; i++){//计算将Row排序后的Section高度
				Row row = section.rows.get(i);		
				if(i + row.height > secHeight)
					secHeight = i+row.height;
			}
			section.height = secHeight;
		}
		Collections.sort(resRow.sections,new Comparator<Section>(){//对一个Row内的Section排序
			@Override
			public int compare(Section o1, Section o2) {
				return -((o1.height - o1.rowNum) - (o2.height - o2.rowNum));
			}		
		});		
		int rowHeight = 0;cnt = 0;
		for(Section section: resRow.sections){//计算将Section排序后的Row高度
			if(cnt + section.height > rowHeight){
				rowHeight = cnt + section.height;				
			}
			cnt += section.rowNum;
		}
		resRow.height = rowHeight;
		return resRow;
	}
	
}

