# Generic-Segment-Tree
Create a segment tree in just one line of code

Segment tree for range max queries of integers
```
SegmentTree<Integer> st=new SegmentTree<>(n,Integer.MIN_VALUE,Integer.MIN_VALUE,(p,q)->Math.max(p,q));
```

Segment tree for range sum queries of Long 
```
SegmentTree<Long> st=new SegmentTree<>(n,0,0,(p,q)->p+q);
```
