package de.t0biii.Robots_Anonymous;

import sx.blah.discord.util.DiscordException;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args )
    {
        Instance bot;
        Instance2 bot2;
        Instance3 bot3;
        Instance4 bot4;
        bot = new Instance(""); //TalkBot1 
        bot2 = new Instance2(""); //TalkBot2
        bot3 = new Instance3(""); //TalkBot3
        bot4 = new Instance4(""); //TalkBot4
      
      try{
      	bot.login();
      	bot2.login();
      	bot3.login();
      	bot4.login();
      } catch (DiscordException e){
   //   	log.warn("Bot could not start", e);
      }
    }
}
