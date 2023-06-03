package com.problems.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.problems.graphs.template.DisjointSet;
import com.problems.metadata.Hard;

@Hard
public class AccountsMerge {

//	Given a list of accounts where each element accounts[i] is a list of strings, where the first element 
//	accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//	Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some 
//	common email to both accounts. Note that even if two accounts have the same name, they may belong to different 
//	people as people could have the same name. A person can have any number of accounts initially, but all of their
//	accounts definitely have the same name.
//
//	After merging the accounts, return the accounts in the following format: the first element of each account is 
//	the name, and the rest of the elements are emails in sorted order. The accounts themselves can be 
//	returned in any order.
//
//	 
//
//	Example 1:
//
//	Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//	
//	Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//	
//	Explanation:
//	The first and second John's are the same person as they have the common email "johnsmith@mail.com".
//	The third John and Mary are different people as none of their email addresses are used by other accounts.
//	We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
//	['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
//	
//	Example 2:
//
//	Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
//	
//	Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

	public static void main(String[] args) {
		AccountsMerge obj = new AccountsMerge();

		String[][] arr = { { "John", "johnsmith@mail.com", "john_newyork@mail.com" },
				{ "John", "johnsmith@mail.com", "john00@mail.com" }, { "Mary", "mary@mail.com" },
				{ "John", "johnnybravo@mail.com" } };

		List<List<String>> accounts = new ArrayList<>();

		for (String[] str : arr) {
			List<String> account = new LinkedList<>();
			Collections.addAll(account, str);
			accounts.add(account);
		}

		obj.accountsMerge(accounts).forEach(System.out::println);

		obj.accountsMergeUsingDFS(accounts).forEach(System.out::println);
	}

//	Approach:-
//			-> Problem asks us to eliminate duplicate account details of a person by merging them together.
//			-> Duplicates accounts are when there are two or more accounts with same name and having atleast one common
//				mail id between them. Then we can merge them as one.
//			-> We will assign each account with a number from 0->n-1 as they appear in the account details list.
//			-> Go over each account's mail and map that mail to account id.
//			-> If a mail is already mapped with some previous account id, i.e matching mail between two accounts with same name,
//				merge these acounts by using disjoint set union.
//			-> Now set these mails for each account by thier ultimate parent.
//	
//	TC:- O(N+E) + O(E*4ɑ) + O(N*(ElogE + E))  N = no. of accounts/nodes ; E = no. of mails/edges
//			
//	SC:- O(N)+ O(N) +O(2N) ~ O(N)
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		Map<String, Integer> map = new LinkedHashMap<>();

		int n = accounts.size();

		DisjointSet ds = new DisjointSet(n);

		for (int i = 0; i < n; i++) {
			List<String> account = accounts.get(i);
			for (int j = 1; j < account.size(); j++) {
				if (map.containsKey(account.get(j))) {
					ds.unionByRank(i, map.get(account.get(j)));
				} else {
					map.put(account.get(j), i);
				}
			}
		}

		TreeSet<String>[] mails = new TreeSet[n];

		for (int i = 0; i < n; i++)
			mails[i] = new TreeSet<>();

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

	/************************************************
	 * DFS Appraoch
	 *****************************************/

	private HashSet<String> visited = new HashSet<>();
	private Map<String, List<String>> adjacent = new HashMap<String, List<String>>();

	private void DFS(List<String> mergedAccount, String email) {
		visited.add(email);
		// Add the email vector that contains the current component's emails
		mergedAccount.add(email);

		if (!adjacent.containsKey(email)) {
			return;
		}

		for (String neighbor : adjacent.get(email)) {
			if (!visited.contains(neighbor)) {
				DFS(mergedAccount, neighbor);
			}
		}
	}

	public List<List<String>> accountsMergeUsingDFS(List<List<String>> accountList) {

		for (List<String> account : accountList) {
			int accountSize = account.size();

			// Building adjacency list
			// Adding edge between first email to all other emails in the account
			String accountFirstEmail = account.get(1);
			for (int j = 2; j < accountSize; j++) {
				String accountEmail = account.get(j);

				if (!adjacent.containsKey(accountFirstEmail)) {
					adjacent.put(accountFirstEmail, new ArrayList<String>());
				}
				adjacent.get(accountFirstEmail).add(accountEmail);

				if (!adjacent.containsKey(accountEmail)) {
					adjacent.put(accountEmail, new ArrayList<String>());
				}
				adjacent.get(accountEmail).add(accountFirstEmail);
			}
		}

		// Traverse over all th accounts to store components
		List<List<String>> mergedAccounts = new ArrayList<>();
		for (List<String> account : accountList) {
			String accountName = account.get(0);
			String accountFirstEmail = account.get(1);

			// If email is visited, then it's a part of different component
			// Hence perform DFS only if email is not visited yet
			if (!visited.contains(accountFirstEmail)) {
				List<String> mergedAccount = new ArrayList<>();
				// Adding account name at the 0th index
				mergedAccount.add(accountName);

				DFS(mergedAccount, accountFirstEmail);
				Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
				mergedAccounts.add(mergedAccount);
			}
		}

		return mergedAccounts;
	}
}
