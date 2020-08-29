class SegmentTree<T>
{
	int n;
	T tree[],init,invalid;
	Merger<T> merger;
	SegmentTree(int n,T init,T invalid, Merger<T> merger)
	{
		this.n=n;
		this.init=init;
		this.invalid=invalid;
		this.merger=merger;
		tree=(T[])new Object[4*n];
		build(1,1,n);
	}
	void build(int ti, int tl, int tr)
	{
		if(tl==tr)
			tree[ti]=init;
		else
		{
			int tm=(tl+tr)/2;
			build(2*ti,tl,tm);
			build(2*ti+1,tm+1,tr);
			tree[ti]=merger.merge(tree[2*ti],tree[2*ti+1]);
		}
	}
	void update(int ti,int tl,int tr,int pos,T val)
	{
		if(tl==tr)
			tree[ti]=val;
		else
		{
			int tm=(tl+tr)/2;
			if(pos<=tm)
				update(2*ti,tl,tm,pos,val);
			else
				update(2*ti+1,tm+1,tr,pos,val);
			tree[ti]=merger.merge(tree[2*ti],tree[2*ti+1]);
		}
	}
	T query(int ti,int tl,int tr,int l,int r)
	{
		if(r<l || tl>r || tr<l)
			return invalid;
		if(l<=tl && tr<=r)
			return tree[ti];
		int tm=(tl+tr)/2;
		T q1=query(2*ti,tl,tm,l,Math.min(tm,r));
		T q2=query(2*ti+1,tm+1,tr,Math.max(tm+1,l),r);
		return merger.merge(q1,q2);
	}
}
interface Merger<T>
{
	T merge(T a,T b);
}
