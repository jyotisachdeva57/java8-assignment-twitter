package edu.knoldus.tweetsprocessor;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;

public class Token {
  static Twitter returnToken() {
    Config config = ConfigFactory.parseFile(new File("src/main/resources/application.conf"));
    Config conf = ConfigFactory.load(config);
    String consumerKey = conf.getString("consumerKey");
    String consumerSecretKey = conf.getString("consumerSecretKey");
    String accessToken = conf.getString("accessToken");
    String accessTokenSecret = conf.getString("accessTokenSecret");
    ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    configurationBuilder.setDebugEnabled(true)
        .setOAuthConsumerKey(consumerKey)
        .setOAuthConsumerSecret(consumerSecretKey)
        .setOAuthAccessToken(accessToken)
        .setOAuthAccessTokenSecret(accessTokenSecret);
    return new TwitterFactory(configurationBuilder.build()).getInstance();
  }
}








