/*****************************************************************
The primary class for the Direct Message groups

started March 28, 2013
@author Thomas Verstraete, Tyler Hutek, Rui Takagi, Andrew Jarvis
@version Winter 2013
 *****************************************************************/
package model;

import java.util.ArrayList;
import java.util.TreeSet;

import twitter4j.DirectMessage;
import twitter4j.User;


public class DMGroups {

	ArrayList<DMUser> users;

	/*****************************************************************
	Parses out all direct messages.
	@param sent an array of Direct Messages that were sent.
	@param received an array of Direct messages that were received. 
	 *****************************************************************/
	public DMGroups (DirectMessage[] sent, DirectMessage[] received) {

		//parses out users and messages

		users = new ArrayList<DMUser>();

		//Sent messages
		for (DirectMessage mess: sent) {

			DMUser usr = new DMUser(mess.getRecipient());
			int index1 = users.indexOf(usr);


			
			//Seeing if a user already exists in the list
			if (!users.contains(usr)) {

				usr.addMessage(mess);
				users.add(usr);
			} else {
				users.get(index1).addMessage(mess);
			}
		}

		//Received messages
		for (DirectMessage mess: received) {

			DMUser usr = new DMUser(mess.getSender());
			int index1 = users.indexOf(usr);

			
			//Seeing if a user already exists in the list
			if (!users.contains(usr)) {
				usr.addMessage(mess);
			users.add(usr);
			} else {
				users.get(index1).addMessage(mess);
			}
		}
	}
	
	
	/*****************************************************************
	 Gets the list of users
	 @return rUsers the list of users to return
	 *****************************************************************/
	public User[] getUsers() {
		
		User[] rUsers = new User[users.size()];
		
		for (int i = 0; i < users.size(); i++) {
			rUsers[i] = users.get(i).getUser();
		}
		
		return rUsers;
	}
	
	
	/*****************************************************************
	Gets the messages for the user
	 *****************************************************************/
	public DMMessage[] getMessages(User usr) {
		DMUser dmuser = new DMUser(usr);
		
		int index = users.indexOf(dmuser);
		
		return users.get(index).getMessages();
	}






	/*****************************************************************
	 The Direct Message User class
	 *****************************************************************/
	public class DMUser {

		private User user;
		private TreeSet<DMMessage> messages;

		private DMUser (User user) {
			this.user = user;
			messages = new TreeSet<DMMessage>();
		}
		/*****************************************************************
		Gets the user
		@return user the user that's requested
		 *****************************************************************/
		private User getUser() {
			return user;
		}

		/*****************************************************************
		 *compares two users
		 *returns true if both users are the same
		 *****************************************************************/
		private boolean isUser(User other) {
			return (user.getId() == other.getId());
		}
		/*****************************************************************
		 * Adds a message to the list of direct messages
		 *****************************************************************/
		private void addMessage(DirectMessage mess) {

			messages.add(new DMMessage(mess));
		}
		
		/*****************************************************************
		 * gets a direct message
		 * @return returns the direct messages
		 *****************************************************************/
		private DMMessage[] getMessages () {
			DMMessage[] newDMessage = new DMMessage[1];
			
			return messages.toArray(newDMessage);
		}

		/*****************************************************************
		 * determines if two users are equal
		 * @return true if the users are equal
		 *****************************************************************/
		public boolean equals (Object obj) {
			User usr = ((DMUser) obj).getUser();
			return (user.equals(usr));		
		}
	}


	/*****************************************************************
	 * The direct message class
	 *****************************************************************/
	public class DMMessage implements Comparable{
		DirectMessage message;

		/*****************************************************************
		 * creates a direct message
		 *****************************************************************/
		private DMMessage (DirectMessage message) {
			this.message = message;
		}

		@Override
		/*****************************************************************
		 compares two messages to determine which was created first
		 @return 0 if the two messages are equal, negative if the message
		 is created after the argument, and positive if the message is
		 created before an argument
		 *****************************************************************/
		public int compareTo(Object arg0) {
			DirectMessage other = ((DMMessage) arg0).getMessage();
			return message.getCreatedAt().compareTo(other.getCreatedAt());
		}

		/*****************************************************************
		Gets a direct message
		@return returns a direct message.
		 *****************************************************************/
		public DirectMessage getMessage() {
			return message;
		}
	}
}
