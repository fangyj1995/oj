package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Run {
	static class TreeNode {
	    int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public static void main(String[] args) {
		Run run = new Run();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        two.right = one;
        run.lowestCommonAncestor(two,two,one);
        System.out.println(lca.val);
	}
	static class Res{
        boolean hasP;
        boolean hasQ;
    }
    static TreeNode lca;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        lca(root,p,q);
        return lca;
    }
    public Res lca(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return new Res();
        Res leftRes = lca(root.left,p,q);
        Res rightRes = lca(root.right,p,q);
        
        Res res = new Res();
        res.hasP = (root == p || leftRes.hasP || rightRes.hasP);
        res.hasQ = (root == q || leftRes.hasQ || rightRes.hasQ);
        if(res.hasP && res.hasQ && lca == null){
            lca = root;
        }
        System.out.println(root.val +" ,p "+ res.hasP +" ,q "+ res.hasQ);
        return res;
    }
}
