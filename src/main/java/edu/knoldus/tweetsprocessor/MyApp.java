package edu.knoldus.tweetsprocessor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MyApp {
  public static void main(String args[]) {

    TweetsProcessor tweetsProcess = new TweetsProcessor();
    CompletableFuture<Double> averageCount = tweetsProcess.returnAverageCount("#bell");
    CompletableFuture<String> result = averageCount.thenApply(name -> "Average Count: " + name);
    result.thenAccept(System.out::println);


    CompletableFuture<Double> favorCount = tweetsProcess.returnAverageFavouriteCount("#bell");
    CompletableFuture<String> favor = favorCount.thenApply(name -> "Favourite Count" + name);
    favor.thenAccept(System.out::println);

    CompletableFuture<Double> retweetCount = tweetsProcess.returnAverageRetweetCount("#bell");
    CompletableFuture<String> reCount = retweetCount.thenApply(name -> "Retweet Count " + name);
    reCount.thenAccept(System.out::println);

    CompletableFuture<Integer> count = tweetsProcess.returnCount("#bell");
    CompletableFuture<String> countTweet = count.thenApply(name -> "Hello " + name);
    countTweet.thenAccept(System.out::println);

    CompletableFuture<List<String>> tweets = tweetsProcess.returnTweets("#bell");
    CompletableFuture<String> tweet = tweets.thenApply(name -> "Hello " + name);
    tweet.thenAccept(System.out::println);


  }
}
