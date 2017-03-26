
public class UnionFind {
	int[] id ;
	int[] size;
	public UnionFind(int n){
		id = new int[n+1];
		size = new int[n+1];
		for(int i = 0 ; i < n ; i++){
			id[i] = i;
			size[i] = 1;
		}
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
	public  void union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		assert( (id[pRoot] == pRoot) &(id[qRoot] == qRoot));
		if(size[pRoot]>size[qRoot]){//q并入p
			id[qRoot] = pRoot;
			size[qRoot]++;
		}
		else{//p并入q
			id[pRoot] = qRoot;
			size[pRoot]++;
		}
	}
}
