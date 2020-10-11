# Generic-Segment-Tree
Create a segment tree in just one line of code

SegmentTree code in every question is mostly the same, except for a few changes:-
```
1. No of elements n
2. Initial value / Invalid value(when query is invalid)
3. Function to merge tree[l..mid] and tree[mid+1..r] to form tree[l..r] , Merger merge
4. The class type of elements of tree
```

We send all these things as parameter, and rest of the code remains the same

Syntax
-----
```
SegmentTree<T> st=new SegmentTree<>(n,init,(p,q)->some_operation on p and q);
```

Examples
-------

Segment tree for range max queries of integers
```
SegmentTree<Integer> st = new SegmentTree<>(n, Integer.MIN_VALUE, (p, q) -> Math.max(p, q));
```

Segment tree for range sum queries of Long 
```
SegmentTree<Long> st = new SegmentTree<>(n, 0, (p, q) -> p + q);
```

