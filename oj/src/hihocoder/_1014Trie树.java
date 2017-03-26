package hihocoder;
import java.util.Scanner;

public class _1014Trie树 {//所有字符的版本
	 public static void main(String [] args)
	  {
		  Scanner in=new Scanner(System.in);
		  int N=in.nextInt();
		  Trie t=new Trie();
		  for(int i=0;i<N;i++)
		  {
			  String word=in.next();
			  t.put(word);
		  }
		  int M=in.nextInt();
		  for(int i=0;i<M;i++)
		  {
			  int num=t.get(in.next());
			  System.out.println(num);
		  }
	  }	  
}
class Trie
{
	  private static int R=256;
	  private Node root=new Node();
	  private class Node
	  {
		  private int value;//以这个单词为前缀的单词个数
		  private Node[] next=new Node[R];//字符本身作为数组的索引，避免了查找的开销
	  }
	  public void put(String key)
	  {
		  root=put(root,key,0);
	  }
	  private Node put(Node x,String key,int i)
	  {		 
		  if(x==null) x=new Node();	
		  (x.value)++;
		  if(i==key.length())		
			 return x;//!		   
		  char c=key.charAt(i);		  		 
		  x.next[c]=put(x.next[c],key,i+1);//在子树种插入
		  return x;
	  }
	  public int get(String key)
	  {
		  return get(root,key,0);
	  }
	  private int get(Node x,String key,int i)
	  {		  
		  //System.out.println("i ："+i);
		  if(x==null) 
		  {			 
			  //System.out.println("return 0");
			  return 0;
		  }		  
		  if(i==key.length()) return x.value;
		  char c=key.charAt(i);
		  return get(x.next[c],key,i+1);
	  }
}
