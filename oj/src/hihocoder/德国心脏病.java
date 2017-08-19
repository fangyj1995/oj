package hihocoder;

import java.text.NumberFormat;
import java.util.*;

public class 德国心脏病 {
	static class Card{
		String op;
		int[] fruits;
		int animal;
		public Card(String op, int[] fruits) {
			super();
			this.op = op;
			this.fruits = fruits;
		}
		public Card(String op, int animal) {
			super();
			this.op = op;
			this.animal = animal;		
		}
	}
	static Scanner in = new Scanner(System.in);
	static int init = 10000000;
	public static void main(String[] args) {
		int n = in.nextInt();
		int k = in.nextInt();
		int[] fruits = new int[5];
		int[] animals = new int[3];
		int player = 0;
		Stack<Card>[] cards = (Stack<Card>[])new Stack[n];
		for(int i = 0; i <n; i++) {
			cards[i] = new Stack<Card>();
		}
		int[] cnt = new int[n];
		Arrays.fill(cnt, init);
		//printCards(cnt);
		for(int i = 0; i < k; i++){
			String op = in.next();
			if(op.equals("Fruit")){
				//System.out.println("player " + player +"Fruit");
				int num = in.nextInt();
				int[] tempFruits = new int[5];
				for(int j = 0; j < num; j++){
					tempFruits[in.nextInt()]++;
				}
				Card c = new Card(op,tempFruits);
				for(int j = 0; j < 5; j++){
					fruits[j] += tempFruits[j];
				}
				if(!cards[player].isEmpty()) {
					if(cards[player].peek().op.equals("Fruit")){
						for(int j = 0; j < 5; j++){
							fruits[j] -= cards[player].peek().fruits[j];
						}
					}
					else animals[cards[player].peek().animal]--;
				}
				cards[player].push(c);	
				cnt[player]--;
				player = (player+1) % n;
			}
			else if(op.equals("Animal")) {
				//System.out.println("player " + player +"Animal");
				int animal = in.nextInt();
				Card c = new Card(op,animal);
				animals[animal]++;
				if(!cards[player].isEmpty()) {
					if(cards[player].peek().op.equals("Fruit")){
						for(int j = 0; j < 5; j++){
							fruits[j] -= cards[player].peek().fruits[j];
						}
					}
					else animals[cards[player].peek().animal]--;
				}
				cards[player].push(c);
				cnt[player]--;
				player = (player+1) % n;
			}
			else if(op.equals("Ring")) {
				int ringer = in.nextInt();
				//System.out.print("player " + ringer +"ring ");				
				if(fruit5(fruits) || monkey(fruits,animals) 
						|| ele(fruits,animals) || pig(fruits,animals)) {
					int count = 0;
					for(int j = 0; j < n; j++) {
						cnt[ringer] += cards[j].size();
						count += cards[j].size();
						if(!cards[j].isEmpty()) {
							if(cards[j].peek().op.equals("Fruit")){
								for(int f = 0; f < 5; f++){
									fruits[f] -= cards[j].peek().fruits[f];
								}
							}
							else animals[cards[j].peek().animal]--;
						}		
						cards[j].removeAllElements();
					}
					//System.out.println("success for "+ count);
				}
				else {
					//System.out.println("fail");
					for(int j = 0; j < n; j++) {
						if(j != ringer) {
							cnt[j]++;
							cnt[ringer]--;
						}
					}
				}
				player = ringer;
			}
			//printCards(cnt);
		}
		printCards(cnt);
	}
	public static void printCards(int[] cnt){
		for(int i = 0; i < cnt.length; i++) {
			System.out.println(cnt[i]);
		}
		//System.out.println("-----------");
	}
	//0、1、2、3分别表示香蕉、草莓、樱桃、柠檬
	//0、1、2分别代表猴子、大象、猪
	public static boolean fruit5(int[] fruits) {
		for(int i = 0; i < 5; i++) {
			if(fruits[i] == 5) return true;
		}
		return false;
	}
	public static boolean monkey(int[] fruits, int[] animals) {
		if(animals[0] == 0) return false;
		if(fruits[3] > 0) return false;
		for(int i = 0; i < 5; i++) {
			if(fruits[i] > 0) return true;
		}
		return false;
	}
	public static boolean ele(int[] fruits,int[] animals) {
		if(animals[1] == 0) return false;
		if(fruits[1] > 0) return false;
		for(int i = 0; i < 5; i++) {
			if(fruits[i] > 0) return true;
		}
		return false;
	}
	public static boolean pig(int[] fruits,int[] animals) {
		if(animals[2] == 0) return false;
		for(int i = 0; i < 5; i++) {
			if(fruits[i] > 0) return true;
		}
		return false;
	}
}



