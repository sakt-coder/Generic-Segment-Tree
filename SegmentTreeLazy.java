class SegmentTree<T,L>
{
  int n;
  T tree[],init;
  L lazy[],lazyinit;
  Merger<T> merger;
  Updater<T,L> updater;
  Appender<L> appender;
  SegmentTree(int n,T init,L lazyinit, Merger<T> merger,Updater<T,L> updater,Appender<L> appender)
  {
    this.n=n;
    this.init=init;
    this.lazyinit=lazyinit;
    this.merger=merger;
    this.updater=updater;
    this.appender=appender;
    tree=(T[])new Object[4*n];
    lazy=(L[])new Object[4*n];
    build(1,1,n);
  }
  void build(int ti, int tl, int tr)
  {
    if(tl==tr)
    {
      tree[ti]=init;
      lazy[ti]=lazyinit;
    }
    else
    {
      int tm=(tl+tr)/2;
      build(2*ti,tl,tm);
      build(2*ti+1,tm+1,tr);
      tree[ti]=merger.merge(tree[2*ti],tree[2*ti+1],tl,tr);
    }
  }
  void update(int l,int r,L val)
  {
    update(1,1,n,l,r,val);
  }
  void update(int ti,int tl,int tr,int l,int r,L val)
  {
    if(r<l || tl>r || tr<l)
      return;
    if(l<=tl && tr<=r)
    {
      tree[ti]=updater.update(tree[ti],val,tl,tr);
      lazy[ti]=appender.append(lazy[ti],val,tl,tr);
      return;
    }
    int tm=(tl+tr)/2;
    push(ti,tl,tr);
    update(2*ti,tl,tm,l,Math.min(tm,r),val);
    update(2*ti+1,tm+1,tr,Math.max(tm+1,l),r,val);
    tree[ti]=merger.merge(tree[2*ti],tree[2*ti+1],tl,tr);
  }
  void push(int ti,int tl,int tr)
  {
    int tm=(tl+tr)/2;
    tree[2*ti]=updater.update(tree[2*ti],lazy[ti],tl,tm);
    tree[2*ti+1]=updater.update(tree[2*ti+1],lazy[ti],tm+1,tr);
    lazy[2*ti]=appender.append(lazy[2*ti],lazy[ti],tl,tm);
    lazy[2*ti+1]=appender.append(lazy[2*ti+1],lazy[ti],tm+1,tr);
    lazy[ti]=lazyinit;
  }
  T query(int l,int r)
  {
    return query(1,1,n,l,r);
  }
  T query(int ti,int tl,int tr,int l,int r)
  {
    if(r<l || tl>r || tr<l)
      return init;
    if(l<=tl && tr<=r)
      return tree[ti];
    int tm=(tl+tr)/2;
    push(ti,tl,tr);
    T q1=query(2*ti,tl,tm,l,Math.min(tm,r));
    T q2=query(2*ti+1,tm+1,tr,Math.max(tm+1,l),r);
    return merger.merge(q1,q2,tl,tr);
  }
}
interface Merger<T>
{
  T merge(T a,T b,int tl,int tr);
} 
interface Updater<T,L>
{
  T update(T a,L b,int tl,int tr);
}
interface Appender<L>
{
  L append(L a,L b,int tl,int tr);
}
