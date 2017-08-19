
public class UnionFind {
	int[] id ;
	int[] size;
	int cnt = 0;
	public UnionFind(int n){
		id = new int[n];
		size = new int[n];
		for(int i = 0 ; i < n ; i++){
			id[i] = i;
			size[i] = 1;
		}
		cnt = n;
	}
	public  boolean connected(int p, int q) {	
		return findRoot(p) == findRoot(q);
	}
	public  int findRoot(int p){
		while(id[p] != p){
			p = id[p];
		}
		return p;
	}
	public  boolean union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		if(pRoot == qRoot) return false;
		assert( (id[pRoot] == pRoot) &(id[qRoot] == qRoot));
		if(size[pRoot]>size[qRoot]){//q并入p
			id[qRoot] = pRoot;
			size[qRoot]++;
		}
		else{//p并入q
			id[pRoot] = qRoot;
			size[pRoot]++;
		}
		cnt--;
		return true;
	}
}
