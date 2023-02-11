package com.problems.heaps;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Twitter {

	public static void main(String[] args) {
		Twitter obj = new Twitter();

		obj.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
		List<Integer> newsFeed = obj.getNewsFeed(1); // User 1's news feed should return a list with 1 tweet id ->
														// [5]. return [5]
		System.out.println("News feed of 1:- " + newsFeed);

		obj.follow(1, 2); // User 1 follows user 2.
		obj.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
		newsFeed = obj.getNewsFeed(1); // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet
										// id
		// 6 should precede tweet id 5 because it is posted after tweet id 5.
		System.out.println("News feed of 1:- " + newsFeed);

		obj.unfollow(1, 2); // User 1 unfollows user 2.
		newsFeed = obj.getNewsFeed(1); // User 1's news feed should return a list with 1 tweet id -> [5], since user
										// 1
		// is no longer following user 2.
		System.out.println("News feed of 1:- " + newsFeed);

	}

	private static Integer globalTime = 0;

	private Map<Integer, User> userMap;

	class User {
		private int userId;
		private Deque<Tweet> tweets;
		private Set<Integer> followee;

		User(int userId) {
			this.userId = userId;
			this.tweets = new LinkedList<>();
			this.followee = new HashSet<>();
			this.followee.add(userId);
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public Deque<Tweet> getTweets() {
			return tweets;
		}

		public void setTweets(Deque<Tweet> tweets) {
			this.tweets = tweets;
		}

		public Set<Integer> getFollowee() {
			return followee;
		}

		public void setFollowee(Set<Integer> followee) {
			this.followee = followee;
		}

		public void addFollowee(int followeeId) {
			this.followee.add(followeeId);
		}

		public void removeFollowee(int followeeId) {
			this.followee.remove(followeeId);
		}

		public void addTweet(Tweet tweet) {
			this.tweets.addFirst(tweet);
		}
	}

	class Tweet {
		private int tweetId;
		private int tweetTime;

		Tweet(int tweetId) {
			this.tweetId = tweetId;
			this.tweetTime = globalTime++;
		}

		public int getTweetId() {
			return tweetId;
		}

		public void setTweetId(int tweetId) {
			this.tweetId = tweetId;
		}

		public int getTweetTime() {
			return tweetTime;
		}

		public void setTweetTime(int tweetTime) {
			this.tweetTime = tweetTime;
		}

	}

	public Twitter() {
		this.userMap = new HashMap<>();
	}

	public void postTweet(int userId, int tweetId) {
		if (!userMap.containsKey(userId)) {
			userMap.put(userId, new User(userId));
		}
		userMap.get(userId).addTweet(new Tweet(tweetId));
	}

	public List<Integer> getNewsFeed(int userId) {
		List<Integer> feeds = new LinkedList<>();

		User user = userMap.get(userId);

		if (user == null)
			return feeds;

//		PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t1.tweetTime - t2.tweetTime);

		TreeSet<Tweet> tweets = new TreeSet<>((t1, t2) -> t2.getTweetTime() - t1.getTweetTime());

		Set<Integer> followees = user.getFollowee();

//		List<Tweet> tweets = followees.stream().flatMap(f -> userMap.get(f).tweets.stream())
//				.collect(Collectors.toList());

		for (Integer followee : followees) {
			tweets.addAll(userMap.get(followee).getTweets());
		}
		int limit = 10;
		
		Iterator<Tweet> itr = tweets.iterator();
		
		while(itr.hasNext() && limit>0) {
			feeds.add(itr.next().getTweetId());
			limit--;
		}

		return feeds;
//		for (int i = 0; i < limit && i < tweets.size(); i++) {
//			pq.add(tweets.get(i));
//		}
//
//		for (int i = limit; i < tweets.size(); i++) {
//			if (tweets.get(i).tweetTime > pq.peek().tweetTime) {
//				pq.poll();
//				pq.add(tweets.get(i));
//			}
//		}
////		for (Integer followee : followees) {
////			pq.addAll(userMap.get(followee).tweets);
////		}
//
////		int limit = 10;
//
//		while (!pq.isEmpty()) {
//			feeds.add(0, pq.poll().tweetId);
////			limit--;
//		}
//		return feeds;
	}

	public void follow(int followerId, int followeeId) {
		if (!userMap.containsKey(followerId)) {
			userMap.put(followerId, new User(followerId));
		}
		if (!userMap.containsKey(followeeId)) {
			userMap.put(followeeId, new User(followeeId));
		}
		userMap.get(followerId).addFollowee(followeeId);
	}

	public void unfollow(int followerId, int followeeId) {
		if (!userMap.containsKey(followerId)) {
			return;
		}
		userMap.get(followerId).removeFollowee(followeeId);
	}
}
