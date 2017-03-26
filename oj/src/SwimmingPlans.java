import java.util.Scanner;

public class SwimmingPlans {
	private static int T,L,R,N,Q;
	private static class Plan{//���е���Ӿ�ƻ�
		int t,l,n,d;
		public Plan(int t, int l, int n , int d){
			this.t = t;//��ʼ��Ӿʱ��
			this.l = l;//��Ӿ����ʱ��
			this.n = n;//Ӿ��
			this.d = d;//����
		}		
	}
	private static class Event implements Comparable<Event>{//steve ����Ӿ�¼�
		int t , n , x;//x: ���¼����������ͣ�1��ʾ�����¼�������-1��ʾ�����¼�������
		Plan plan;
		
		public Event(int t, int n, int x,Plan plan) {
			this.t = t;
			this.n = n;
			this.x = x;
			this.plan = plan;
		}

		@Override
		public int compareTo(Event o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	
	public static void swimmingPlans(Plan[] plans){
		Event[][] events = new Event[2][2 * Q];
		for(Plan plan:plans){
			addEvent(events,plan);			
		}
		
		/*lane[0][i]��ʾ��ǰʱ�̶���Ӿ��i������Ӿ��0���������Ӱ�쵽СHi��Ӿ��������
		 * ͬ��lane[1][i]��ʾ����Ӿ��1�������
		*/
		int[][] lane = new int[2][N];
		int time = T;        // ��ǰʱ��
		int dir = 0;   // ��Ӿ�ķ���
		int[] point = new int[]{0, 0}; // ���������Ѿ���������¼�����
		for(int r = 0 ; r < R ; r++){
			boolean found = false;
			while(point[dir] < 2 * Q){
				Event e = events[dir][point[dir]];
				if(time <= e.t){//��ǰʱ�����¼���ʱ��֮ǰ
					for(int i = 0 ; i < N ; i++){//���ÿ��Ӿ��
						if(lane[dir][i] == 0){//��Ӿ������ռ��
							found = true;
							time += L;
							break;
						}
					}
					if(found) break;
					//not found
					// ��ʱ��ʾ��T~event.t���ʱ���ھ�û�п���ʹ�õ�Ӿ��
		            // ������ֱ�ӽ�ʱ��T������event.t����ִ������¼�����
					time = e.t;
					lane[dir][e.n] += e.x;
					point[dir]++;
				}
				else{//time > e.t  , ��ǰʱ�����¼���ʱ��֮��
					/*������������ʱ��t1��,���Ӿ���÷���Ӱ��������һ
					 * ������������ʱ��t2��,���Ӿ���÷���Ӱ��������һ
					 * */
					lane[dir][e.n] += e.x;//x: ���¼����������ͣ�1��ʾ�����¼�������-1��ʾ�����¼�������
					point[dir]++;
				}			
			}
			if(!found) time += L;
	        // �����foundΪfalse�������������÷������е�event���Ѿ�����
	        // ��ʱֻ��СHiһ���˻�����Ӿ
			dir = 1 - dir;
		}
		System.out.println(time);
	}
	
	
	public static void addEvent(Event[][] events, Plan plan){
		
	}
	
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		for(int i = 0; i < m ;i++){
			T = scan.nextInt();
			L = scan.nextInt();
			R = scan.nextInt();
			N = scan.nextInt();
			Q = scan.nextInt();
			Plan[] plans = new Plan[Q];
			for(int j  = 0 ; j < Q ; j++){
				int t = scan.nextInt();
				int l = scan.nextInt();
				int n = scan.nextInt();
				int d = scan.nextInt();
				plans[j] = new Plan(t,l,n,d);
				swimmingPlans(plans);
			}
			
		}
	}
}
