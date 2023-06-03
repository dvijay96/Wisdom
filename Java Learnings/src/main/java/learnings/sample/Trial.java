package learnings.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Trial {

	public static void main(String[] args) {
		Trial obj = new Trial();

		String[][] arr = { { "John", "johnsmith@mail.com", "john_newyork@mail.com" },
				{ "John", "johnsmith@mail.com", "john00@mail.com" }, { "Mary", "mary@mail.com" },
				{ "John", "johnnybravo@mail.com" } };

		List<List<String>> accounts = new ArrayList<>();

		for (String[] str : arr) {
			List<String> account = new LinkedList<>();
//			for (String s : str) {
//				account.add(s);
//			}
			Collections.addAll(account, str);
			accounts.add(account);
		}

		obj.accountsMerge(accounts).forEach(System.out::println);
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		Map<String, Integer> map = new LinkedHashMap<>();

		int n = accounts.size();

		DSU ds = new DSU(n);

		for (int i = 0; i < n; i++) {
			List<String> account = accounts.get(i);
			for (int j = 1; j < account.size(); j++) {
				if (map.containsKey(account.get(j))) {
					ds.union(map.get(account.get(j)), i);
				} else {
					map.put(account.get(j), i);
				}
			}
		}

		TreeSet<String>[] mails = new TreeSet[n];

//		Arrays.fill(mails, new TreeSet<>()); bug

		for (int i = 0; i < n; i++)
			mails[i] = new TreeSet<String>();

		for (Map.Entry<String, Integer> mail : map.entrySet()) {
			int val = mail.getValue();

			int pos = ds.findParent(val);

			mails[pos].add(mail.getKey());
		}

		List<List<String>> merged = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			if (!mails[i].isEmpty()) {
				List<String> account = new ArrayList<>();
				account.add(accounts.get(i).get(0));
				account.addAll(mails[i]);
				merged.add(account);
			}
		}

		return merged;
	}
}

class DSU {

	private int[] parent;
	private int[] rank;

	public DSU(int n) {

		parent = new int[n + 1];
		rank = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}

	public int findParent(int x) {
		if (x == parent[x])
			return x;
		parent[x] = findParent(parent[x]);
		return parent[x];
	}

	public void union(int u, int v) {
		int pu = findParent(u);
		int pv = findParent(v);

		if (pu == pv)
			return;

		if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else if (rank[pv] < rank[pu]) {
			parent[pv] = pu;
		} else {
			parent[pv] = pu;
			rank[pu]++;
		}
	}
}
