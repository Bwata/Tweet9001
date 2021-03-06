package model;
/*****************************************************************
The primary class for creating tweet groups

started February 10, 2013
@author Thomas Verstraete, Tyler Hutek, Rui Takagi, Andrew Jarvis
@version Winter 2013
 *****************************************************************/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import twitter4j.Status;
/*****************************************************************
The primary class for Tweet groups. This manages all Twtgrps.
 *****************************************************************/
public class TweetGroups {
	/** The hashmap for the tweet groups. */
	private HashMap<String, Twtgrp> groups;
/** The user of the groups. */
	private String user;

	/*****************************************************************
	 * The constructor for TweetGroups.
	 * This also loads the current text file into the project
	 * 
	 * @param user
	 *            the user of the set of groups
	 * @throws IOException IOEception;
	 *****************************************************************/
	public TweetGroups(String user) throws IOException {
		// this is where you load the file and parse out information
		// user is concatenated onto the group name so it can be used for
		// multiple users
		
		groups = new HashMap<String, Twtgrp>();
		
		this.user = user;
		BufferedReader br;
		String fileName = user + "Groups.txt";
		String currentLine;
		String[] parsedLine;
		ArrayList<String[]> parsedArrayList = new ArrayList<String[]>();
		try {
		br = new BufferedReader(new FileReader(fileName));
		 } catch (java.io.FileNotFoundException e) {
			File file = new File(fileName);
			
			file.createNewFile();
			br = new BufferedReader(new FileReader(fileName));
		}
		while ((currentLine = br.readLine()) != null) {
			parsedLine = currentLine.split(", ");
			parsedArrayList.add(parsedLine);
		}
		for (int i = 0; i < parsedArrayList.size(); i++) {
			String groupMember = "";
			Twtgrp newGroup = new Twtgrp(parsedArrayList.get(i)[0]);
			for (int j = 1; j < parsedArrayList.get(i).length; j++) {
				groupMember = parsedArrayList.get(i)[j];
				newGroup.addUser(groupMember);
			}
			groups.put(parsedArrayList.get(i)[0], newGroup);
		}
		br.close();
	}


	/*****************************************************************
	 * This parses out the timeline so all groups are created.
	 * 
	 * @param stati
	 *            the array of statuses for the home timeline
	 * 
	 *****************************************************************/
	public void parseTimeLine(Status[] stati) {
		String[] possibleGroups;
		
		
		for (Twtgrp group: groups.values()) {
			group.resetStati();
		}

		// look at one status in a for loop
		for (int i = 0; i < stati.length; i++) {
			possibleGroups = checkUser(stati[i].getUser().getScreenName());
			// possibleGroups contains an array of all groups the status belongs
			// in

			// for loop of all groups
			// I would want to check all existing groups and if the existing
			// group
			// matches a group in the array, we would add it.
//			
//			for (int j = 0; j < possibleGroups.length; j++) {
//			}
			

			for (int j = 0; j < possibleGroups.length; j++) {
				
				if (possibleGroups[j] != null) {
				
				
				
				Twtgrp addGroup = groups.get(possibleGroups[j]);
				
				
				groups.get(possibleGroups[j]).addStatus(stati[i]);
				
				}
				
//				// seeing if our list of possible groups contains the selected
//				// group
//				Twtgrp addGroup = groups.get(possibleGroups[j]);
//				if (addGroup != null) {
//					addGroup.addStatus(stati[i]);
//				}
			}
		}
	}

	/*****************************************************************
	 * Checks to see what groups a user is in.
	 * 
	 * @param user
	 *            the user to check for
	 * @return String[] an array of strings for the groups
	 *****************************************************************/
	public String[] checkUser(String user) {
		// check ALL the groups for the user
		// returns the array of group names the user is in
		ArrayList<String> userGroups = new ArrayList<String>();
		String[] groupNames;
		groupNames = getGroupNames();
		
		for (Twtgrp group: groups.values()) {
			if (group.checkUser(user)) {
				userGroups.add(group.groupName);
			}
		}


		
		String[] list = new String[1];
		
		list = userGroups.toArray(list);
		
		return (list);
		
		
		
//		for (int i = 0; i < groups.size(); i++) {
//
//			if (Arrays.asList(groups.get(groupNames[i]).getUsers()).contains(
//					user)) {
//				userGroups.add(groupNames[i]);
//			}
//		}
		
		
		
		//return (String[]) userGroups.toArray();
	}

	/*****************************************************************
	 * this gets the name of all groups.
	 * 
	 * @return String[] an array of all group names.
	 *****************************************************************/
	public String[] getGroupNames() {
		ArrayList<String> groupNames = new ArrayList<String>();
		for (Twtgrp name : groups.values()) {
			groupNames.add(name.groupName);
		}
		
		String[] list = new String[1];
		
		list = groupNames.toArray(list);
		
		return (list);
	}

	/*****************************************************************
	 * adds a user to a Twtgrp.
	 * 
	 * @param user
	 *            the user who is being added to a group
	 * @param group
	 *            the group which the user is being added to
	 *****************************************************************/
	public void addToGroup(String group, String user) {
		// adds the user to the group specified
		groups.get(group).addUser(user);
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*****************************************************************
	 * removes a user from a group.
	 * 
	 * @param user
	 *            the user to be removed
	 * @param group
	 *            the group which the user is being removed from
	 *****************************************************************/
	public void removeFromGroup(String group, String user) {
		groups.get(group).removeUser(user);
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*****************************************************************
	 * creates a group when given a name.
	 * 
	 * @param groupName
	 *            the name of the group
	 *****************************************************************/
	public void createGroup(String groupName) {
		// creates a group with the specified name
		// apparently the following line isn't necessary?
		// Twtgrp newGroup = new Twtgrp(groupName);

		groups.put(groupName, new Twtgrp(groupName));
		
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*****************************************************************
	 * Destroyes a group.
	 * 
	 * @param group
	 *            the group to be destroyed
	 *****************************************************************/
	public void destroyGroup(String group) {
		groups.remove(group);
		
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*****************************************************************
	 * Saves the information from all groups to a text file. The format is
	 * "groupName, user, user, user..."
	 * @throws IOException IOException
	 *****************************************************************/
	public void save() throws IOException {
		// saves all information with groups and whatnot
		File file = new File(user + "Groups.txt");
		String groupMembers;
		// if the file doesn't already exist, create it.
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (Twtgrp twtgrp : groups.values()) {
			groupMembers = Arrays.toString(twtgrp.getUsers());
			if (groupMembers != null) {
				//removing the brackets from the arrays
				groupMembers = groupMembers.replace("[", "");
				groupMembers = groupMembers.replace("]", "");
				bw.write(twtgrp.groupName + ", " + groupMembers + "\n");
			} else {
				bw.write(twtgrp.groupName + "\n");
			}
			
		}
		bw.close();

	}

	/*****************************************************************
	 * returns a list of all statuses in a group.
	 * 
	 * @param group
	 *            the name of the group which you want statuses from
	 * @return Status[] an array of statuses from the group
	 *****************************************************************/
	public Status[] getStati(String group) {
		// gets the statuses
		// convert status to array
		// have a comparable method in tweetGroups class and even in the main
		// class

		for (Twtgrp twtgrp : groups.values()) {
			if (twtgrp.groupName.equals(group)) {
				return twtgrp.getStati();
			}
		}
		return null;

	}

	/*****************************************************************
	 * This returns a list of all users in a group.
	 * 
	 * @param group
	 *            the group to get users from
	 * @return String[] a list of all users in a group
	 *****************************************************************/
	public String[] getUsers(String group) {
		// returns the users in group
		for (Twtgrp twtgrp : groups.values()) {
			if (twtgrp.groupName.equals(group)) {
				return twtgrp.getUsers();
			}
		}
		return null;
	}

	private class Twtgrp {
		private String groupName;
		private ArrayList<String> users;
		private ArrayList<Status> stati;

		/*****************************************************************
		 * The constructor for a Twtgrp.
		 * 
		 * @param groupName
		 *            the name of the group
		 *****************************************************************/
		Twtgrp(String groupName) {
			// instantiates the name for the list and sets the parameter to the
			// variable
			this.groupName = groupName;
			users = new ArrayList<String>();
			stati = new ArrayList<Status>();
		}

		/*****************************************************************
		 * adds a user to a Twtgrp.
		 * 
		 * @param user
		 *            the user to be added
		 *****************************************************************/
		private void addUser(String user) {
			// adds the user to the group
			
			if(!users.contains(user) && user!= null);
			users.add(user);
		}

		/*****************************************************************
		 * removes a user from a Twtgrp.
		 * 
		 * @param user the user to be removed from a Twtgrp
		 *****************************************************************/
		private void removeUser(String user) {
			// removes a user from the group
			users.remove(user);
		}

		/*****************************************************************
		 * checks to see if a user is within a group array list.
		 * 
		 * @param user
		 *            the user being searched for
		 * @return true if the user exists. False if not.
		 *****************************************************************/
		private boolean checkUser(String user) {
			// checks to see if the user is in the group(array list)
			if (users.contains(user)){
				return true;
			} else {
				return false;
				}
		}

		/*****************************************************************
		 * gets the statuses of a Twtgrp.
		 * 
		 * @return Status[] the statuses of a group
		 *****************************************************************/
		private Status[] getStati() {
			// gets the statuses
			// convert status to array
			// have a comparable method in tweetGroups class and even in the
			// main class
			
			
			Status[] list = new Status[1];
			
			list = stati.toArray(list);
			
			return (list);

		}

		/*****************************************************************
		 * returns the users in a group.
		 * 
		 * @return String[] the users in a group
		 *****************************************************************/
		private String[] getUsers() {
			// returns the users in group
			
			String[] list = new String[1];
			
			list = users.toArray(list);
			return (list);
		}

		/*****************************************************************
		 * adds a status to a Twtgrp.
		 * 
		 * @param status
		 *            the status which is being added
		 *****************************************************************/
		private void addStatus(Status status) {
			
			
			// adds a status to the array list
			stati.add(status);
		}
		/*****************************************************************
		Resets all of the statuses.
		 *****************************************************************/
		private void resetStati() {
			stati = new ArrayList<Status>();
		}
	}
}