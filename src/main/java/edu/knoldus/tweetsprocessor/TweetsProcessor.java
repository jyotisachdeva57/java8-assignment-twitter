package edu.knoldus.tweetsprocessor;

import twitter4j.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

public class TweetsProcessor {


  private QueryResult result;
  private QueryResult resultAvg;
  private Twitter token = Token.returnToken();


  public CompletableFuture<List<String>> returnTweets(String hashTag) {
    return CompletableFuture.supplyAsync(() -> {
      Query query = new Query(hashTag);

      try {
        result = token.search(query);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
      List<Status> tweets = result.getTweets();
      query.setCount(100);

      return tweets.stream().map(Status::getText).collect(toList());
    });
  }


  public CompletableFuture<Integer> returnCount(String hashTag) {
    return CompletableFuture.supplyAsync(() -> {
      Query query = new Query(hashTag);
      query.setCount(100);

      try {
        result = token.search(query);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
      return result.getTweets().size();
    });
  }


  public CompletableFuture<Double> returnAverageRetweetCount(String hashTag) {
    return CompletableFuture.supplyAsync(() -> {
      Query query = new Query(hashTag);
      query.setCount(100);
      int sum = 0;
      try {
        result = token.search(query);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
      for (Status status : result.getTweets()) {
        sum += status.getRetweetCount();
      }
      return sum / (double) result.getTweets().size();
    });
  }

  public CompletableFuture<Double> returnAverageCount(String hashTag) {
    return CompletableFuture.supplyAsync(() -> {
      Query query = new Query(hashTag);
      query.setCount(100);
      query.setSince(java.time.LocalDate.now().minusDays(10).toString());
      try {
        result = token.search(query);
        QueryResult resultAvg = token.search(query);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
      int avg = result.getTweets().size();
      query.setUntil(java.time.LocalDate.now().toString());
      int total = resultAvg.getTweets().size();
      System.out.println(avg / (double) total);
      return avg / (double) total;
    });
  }


  public CompletableFuture<Double> returnAverageFavouriteCount(String hashTag) {
    return CompletableFuture.supplyAsync(() -> {
      Query query = new Query(hashTag);
      query.setCount(100);
      int sum = 0;
      try {
        result = token.search(query);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
      for (Status status : result.getTweets()) {
        sum += status.getFavoriteCount();
      }
      return sum / (double) result.getTweets().size();
    });
  }


}