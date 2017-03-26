package hihocoder;


import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static Map<String, String> greyAncestor = new HashMap<String, String>();
    static Map<String, Integer> color = new HashMap<String, Integer>();
    static Map<String, List<String>> tree = new HashMap<String, List<String>>();
    static Map<String, List<Pair<String, Integer>>> queries = new HashMap<String, List<Pair<String,Integer>>>();
    static List<String> answers = new ArrayList<String>();
    
    static class Pair<E1, E2> {
        E1 first;
        E2 second;
        Pair(E1 e1, E2 e2) {
            first = e1;
            second = e2;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String root = in.next();
        List<String> children = new ArrayList<String>();
        children.add(in.next());
        tree.put(root, children);
        for (int i = 1; i < n; i++) {
            String father = in.next();
            String child = in.next();
            children = tree.get(father);
            if (children == null) {
                children = new ArrayList<String>();
                tree.put(father, children);
            }
            children.add(child);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            String n1 = in.next();
            String n2 = in.next();
            List<Pair<String, Integer>> q1 = queries.get(n1);
            if (q1 == null) {
                q1 = new ArrayList<Pair<String, Integer>>();
                queries.put(n1, q1);
            }
            q1.add(new Pair<String, Integer>(n2, i));
            List<Pair<String, Integer>> q2 = queries.get(n2);
            if (q2 == null) {
                q2 = new ArrayList<Pair<String, Integer>>();
                queries.put(n2, q2);
            }
            q2.add(new Pair<String, Integer>(n1, i));
        }
        for (int i = 0; i < m; i++) {
            answers.add("");
        }
        dfs(root, null);
        for (int i = 0; i < m; i++) {
            System.out.println(answers.get(i));
        }
    }
    
    static void dfs(String node, String parent) {
        color.put(node, 0);
        List<Pair<String, Integer>> qs = queries.get(node);
        if (qs != null) {
            for (Pair<String, Integer> q : qs) {
                String p = q.first;
                Integer c = color.get(p);
                if (c == null) {
                    continue;
                }
                if (c == 0) {
                    answers.set(q.second, p);
                } else {
                    answers.set(q.second, find(p));
                }
            }
        }
        List<String> children = tree.get(node);
        if (children != null) {
            for (String c : children) {
                dfs(c, node);
            }
        }
        greyAncestor.put(node, parent);
        color.put(node, 1);
    }
    
    static String find(String node) {
        String p = greyAncestor.get(node);
        if (p == null) {
            return node;
        }
        String ret = find(p);
        greyAncestor.put(node, ret);
        return ret;
    }
}