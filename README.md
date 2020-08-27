# Generic-Segment-Tree
Create a segment tree in just one line of code

```SegmentTree<Integer> st=new SegmentTree<>(n,Integer.MIN_VALUE,Integer.MIN_VALUE,(p,q)->Math.max(p,q));``` creates a segment tree for range max queries of integers
```SegmentTree<Long> st=new SegmentTree<>(n,0,0,(p,q)->p+q);``` creates a segment tree for range sum queries of Long 
