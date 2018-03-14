package edu.knoldus.tweetsprocessor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MyApp {
    public static void main(String args[]) throws InterruptedException {

        TweetsProcessor tweetsProcess = new TweetsProcessor();


        CompletableFuture<List<String>> tweets = tweetsProcess.returnLatestTweetsWithLimit("#bell");
        CompletableFuture<String> tweet = tweets.thenApply(name -> "latest tweets " + name);
        tweet.thenAccept(System.out::println);


        CompletableFuture<List<String>> sortedTweets = tweetsProcess.returnTweetsFromNewerToOlder("#bell");
        CompletableFuture<String> resultOfSortedTweets = sortedTweets.thenApply(name -> "sorted tweets " + name);
        resultOfSortedTweets.thenAccept(System.out::println);


        CompletableFuture<List<Integer>> reTweets = tweetsProcess.returnReTweetsFromHigherToLower("#bell");
        CompletableFuture<String> resultReTweets = reTweets.thenApply(name -> "re -tweets " + name);
        resultReTweets.thenAccept(System.out::println);


        CompletableFuture<List<Integer>> numberOfLikes = tweetsProcess.returnLikesFromHigherToLower("#bell");
        CompletableFuture<String> resultantLikes = numberOfLikes.thenApply(name -> "likes sorted wteets " + name);
        resultantLikes.thenAccept(System.out::println);

        CompletableFuture<List<String>> enteredTweets = tweetsProcess.returnTweetsForEnteredDate("#bell");
        CompletableFuture<String> resultantTweets = enteredTweets.thenApply(name -> "tweets of particular date " + name);
        resultantTweets.thenAccept(System.out::println);


        CompletableFuture<List<String>> spanTweets = tweetsProcess.returnTweetsWithinSpanOf15Minutes("#bell");
        CompletableFuture<String> tweetsResult = spanTweets.thenApply(name -> "tweets within span of 15 minutes " + name);
        tweetsResult.thenAccept(System.out::println);


        Thread.sleep(10000);

    }
}
