package other;

import java.util.LinkedList;
import java.util.Queue;

public class ConstructBinaryTree {
	static int[] pre;//preOrder
	static int[] in;//InOrder
	static class TreeNode{
		int val;
		TreeNode l;
		TreeNode r;
		public TreeNode(int val, TreeNode l, TreeNode r) {
			super();
			this.val = val;
			this.l = l;
			this.r = r;
		}		
	}
	static TreeNode construct(){
		assert(pre.length == in.length);
		return construct(0,pre.length-1,0,in.length-1);
	}
	static TreeNode construct(int ps, int pe, int is, int ie) {
		if(pe < ps || ie < is) return null;
		
		int rootVal = pre[ps];
		int rootIndex = findRoot(rootVal,is,ie);
		
		int leftLen = rootIndex - is;
		int rightLen = ie - rootIndex;
		
		TreeNode left = null, right = null;
		if(leftLen > 0)   left = construct(ps+1, ps + leftLen, is, rootIndex - 1);
		if(rightLen > 0) right = construct(pe - rightLen + 1, pe, rootIndex + 1, ie);
		TreeNode node = new TreeNode(rootVal,left,right);
		
		return node;
	}
	static int findRoot(int rootVal, int is, int ie) {
		for(int i = is; i <= ie ; i++)
			if(rootVal == in[i]) return i;
		return -1;
	}
	public static void main(String[] args) {
		pre = new int[]{1,2,4,7,3,5,6,8};
		in = new int[]{4,7,2,1,5,3,8,6};
		TreeNode root = construct();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()){			
			TreeNode n = q.poll();			
			if(n != null){
				System.out.println(n.val);
				q.offer(n.l);
				q.offer(n.r);
			}
		}
	}

}
