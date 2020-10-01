class SparseTable<T> {
	int n, logn;
	T[] arr;
	T[][] dp;
	Merger<T> merger;

	SparseTable(int n, T arr[], Merger<T> merger) {
		this.n = n;
		logn = (int)Math.ceil(Math.log(n));
		this.arr = arr;
		dp = (T[][]) new Object[n + 1][logn + 1];
		this.merger = merger;
		build();
	}

	void build() {
		for(int i = 1; i <= n; i++)
			dp[i][0] = arr[i];
		for(int j = 1; j <= logn; j++)
			for(int i = 1; i <= n; i++)
				dp[i][j] = i + (1 << (j - 1)) > n ? dp[i][j - 1] : merger.merge(dp[i][j - 1], dp[i + (1 << (j - 1))][j - 1]);
	}

	T query(int l, int r) {
		int length = r - l + 1;
		int log = (int)Math.log(length);
		return merger.merge(dp[l][log], dp[r - (1 << log) + 1][log]);
	}
}

interface Merger<T> {
	T merge(T a, T b);
}
