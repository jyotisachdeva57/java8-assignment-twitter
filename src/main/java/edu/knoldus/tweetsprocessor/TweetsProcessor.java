package edu.knoldus.tweetsprocessor;

import twitter4j.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TweetsProcessor {
    private QueryResult result;
    private Twitter token = Token.returnToken();


    public CompletableFuture<List<String>> returnLatestTweetsWithLimit(String hashTag) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            query.resultType(Query.RECENT);
            query.setCount(100);
            try {
                result = token.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            return tweets.stream().map(Status::getText).collect(toList());
        });
    }

    public CompletableFuture<List<String>> returnTweetsFromNewerToOlder(String hashTag) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            query.resultType(Query.RECENT);
            query.setCount(100);
            try {
                result = token.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            tweets.sort(Comparator.comparing(status -> status.getCreatedAt().getTime()));
            return tweets.stream().map(Status::getText).collect(toList());
        });
    }


    public CompletableFuture<List<Integer>> returnReTweetsFromHigherToLower(String hashTag) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            query.resultType(Query.RECENT);
            query.setCount(100);
            try {
                result = token.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            tweets.sort((first, second) ->
                    second.getRetweetCount() - first.getRetweetCount());
            return tweets.stream().map(Status::getRetweetCount).collect(toList());
        });
    }


    public CompletableFuture<List<Integer>> returnLikesFromHigherToLower(String hashTag) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            query.resultType(Query.RECENT);
            query.setCount(100);
            try {
                result = token.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            tweets.sort((first, second) ->
                    second.getFavoriteCount() - first.getFavoriteCount());
            return tweets.stream().map(Status::getFavoriteCount).collect(toList());
        });
    }


    public CompletableFuture<List<String>> returnTweetsWithinSpanOf15Minutes(String hashTag) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, -15);
            String yesterday = sdf.format(calendar.getTime());
            query.setSince(yesterday);
            query.setCount(100);
            try {
                result = token.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            tweets.sort(Comparator.comparing(status -> status.getCreatedAt().getTime()));
            return tweets.stream().map(Status::getText).collect(toList());
        });
    }

    public CompletableFuture<List<String>> returnTweetsForEnteredDate(String hashTag) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            query.setUntil("2017-01-01");
            query.setCount(100);
            try {
                result = token.search(query);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            List<Status> tweets = result.getTweets();
            tweets.sort(Comparator.comparing(status -> status.getCreatedAt().getTime()));
            return tweets.stream().map(Status::getText).collect(toList());
        });
    }


}