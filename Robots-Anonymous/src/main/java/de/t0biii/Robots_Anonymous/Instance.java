package de.t0biii.Robots_Anonymous;


import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotThought;
import com.google.code.chatterbotapi.ChatterBotType;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("unused")
public class Instance {
	 public static void main(String[] args){
		 
	 }
   // private static final Logger log = LoggerFactory.getLogger(Instance.class);

    private volatile IDiscordClient client;
    private String email;
    private String password;
    private String token;
    private final AtomicBoolean reconnect = new AtomicBoolean(true);

    public Instance(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Instance(String token) {
        this.token = token;
    }

    @SuppressWarnings("deprecation")
	public void login() throws DiscordException {
        if (token == null) {
            client = new ClientBuilder().withLogin(email, password).login();
        } else {
            client = new ClientBuilder().withToken(token).login();
        }
        client.getDispatcher().registerListener(this);
    }

    @EventSubscriber
    public void onReady(ReadyEvent e) {
  //      log.info("*** Discord bot armed ***");
        e.getClient().updatePresence(false, Optional.of("Thinking"));
    }
	
    

    @EventSubscriber
    public void onDisconnect(DiscordDisconnectedEvent event) {
        CompletableFuture.runAsync(() -> {
            if (reconnect.get()) {
  //              log.info("Reconnecting bot");
                try {
                    login();
                } catch (DiscordException e) {
  //                 log.warn("Failed to reconnect bot", e);
                }
            }
        });
    }

    @EventSubscriber
    public void onMessage(MessageReceivedEvent e) {
    	IUser iuser = e.getMessage().getAuthor();
    	String message = e.getMessage().getContent().toLowerCase();
    	String s = null;
    	try { 	
          if(e.getMessage().getChannel().getID().contains( "263473469931520002") || iuser.getID().contains("263470559822610432") || iuser.getID().contains("149510921524346880")){
        	  ChatterBotFactory factory = new ChatterBotFactory();
              ChatterBot bot1 = null;
              try {
                   bot1 = factory.create(ChatterBotType.CLEVERBOT);
               } catch (Exception e4) {
                   e4.printStackTrace();
               }
               ChatterBotSession bot1session = bot1.createSession();
               try {
                   s = bot1session.think(message);
               } catch (Exception e1) {
                   e1.printStackTrace();
               }

        	  String b =  ":space_invader:  "+s+" :space_invader:  ";
        	  try {
				Thread.sleep(3*1000L);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        	  e.getClient().getChannelByID("263473469931520002").sendMessage(b);
          }	   		
    		
		
		} catch (MissingPermissionsException | HTTP429Exception  | DiscordException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      //  log.debug("Got message");
    }

    public void terminate() {
        reconnect.set(false);
        try {
            client.logout();
        } catch (HTTP429Exception | DiscordException e) {
          //  log.warn("Logout failed", e);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}