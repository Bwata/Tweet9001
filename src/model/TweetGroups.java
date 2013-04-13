package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import twitter4j.Status;

public class TweetGroups {
	ArrayList<Twtgrp> groups;
	private String user;
	public TweetGroups(String user) throws IOException{
		//this is where you load the file and parse out information
		//user is concatenated onto the group name so it can be used for multiple users
		this.user = user;
		String fileName = user+"Groups.txt";
		String currentLine;
		String[] parsedLine;
		ArrayList<String[]> parsedArrayList = new ArrayList<String[]>();
		BufferedReader br = new  BufferedReader (new FileReader(fileName));
		while((currentLine = br.readLine()) != null){
			parsedLine = currentLine.split(", ");
			parsedArrayList.add(parsedLine);
		}
		for(int i = 0; i<parsedArrayList.size(); i++){
			String groupMember;
			Twtgrp newGroup = new Twtgrp(parsedArrayList.get(i)[0]);
			for(int j = 1; i<parsedArrayList.get(i).length; j++){
				groupMember = parsedArrayList.get(i)[j];
				newGroup.addUser(groupMember);
			}
			groups.add(newGroup);
		}
		br.close();
	}
	
	public void parseTimeLine(Status[] stati, String groupName){
		//parse out stati into different groups
		//creating a list of all users in a group
		ArrayList<Status> filteredStatuses = new ArrayList<Status>();
		String[] groupMembers = new String[1];
		String screenName;
		
		for(int i = 0; i<groups.size(); i++){
			if(groups.get(i).groupName.equals(groupName)){
				groupMembers = new String[groups.get(i).getUsers().length];
			}
		}
		//checking to see if the list of statuses contains any of the users in the group
		for(int i = 0; i<stati.length; i++){
			screenName = stati[i].getUser().getScreenName();
				if(Arrays.asList(groupMembers).contains(screenName))
					filteredStatuses.add(stati[i]);
		}
		//replacing the original stati with the filtered stati
		stati = (Status[])filteredStatuses.toArray();
	}
	
	public String[] checkUser(String user){
		//check ALL the groups for the user
		//returns the array of group names the user is in
		ArrayList<String> userGroups = new ArrayList<String>();
		for(int i = 0; i<groups.size(); i++){
			if(Arrays.asList(groups.get(i).getUsers()).contains(user)){
				userGroups.add(groups.get(i).groupName);
			}
		}
		return (String[])userGroups.toArray();
	}
	
	public String[] getGroupNames(){
		ArrayList<String> groupNames = new ArrayList<String>();
		for(int i = 0; i<groups.size(); i++){
			groupNames.add(groups.get(i).groupName);
		}
		return (String[])groupNames.toArray();
	}
	
	public void addToGroup(String group, String user){
		//adds the user to the group specified
		for(int i = 0; i<groups.size(); i++){
			if(groups.get(i).groupName.equals(group)){
				groups.get(i).addUser(user);
			}
		}
	}
	
	public void removeFromGroup(String group, String user){

		//removes the user from the group
		for(int i = 0; i<groups.size(); i++){
			//makes the array of users into a list to use contains and remove on it.
			if(Arrays.asList(groups.get(i).getUsers()).contains(user)){
				Arrays.asList(groups.get(i).getUsers()).remove(user);
			}
		}
	}
	
	public void createGroup(String groupName){
		//creates a group with the specified name
		 Twtgrp newGroup = new Twtgrp(groupName);
		groups.add(newGroup);
		
	}
	
	public void destroyGroup(String group){
		for(int i = 0; i<groups.size(); i++){
			if(groups.get(i).groupName.equals(group)){
				groups.remove(i);
			}
		}
	}
	
	public void save() throws IOException{
		//saves all information with groups and whatnot
		File file = new File(user+"groups.txt");
		//if the file doesn't already exist, create it.
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i = 0; i<groups.size(); i++){
			bw.write(groups.get(i).groupName+", "+groups.get(i).getUsers());
		}
		bw.close();
		
		
	}
	public Status[] getStati(String group){
		//gets the statuses
		//convert status to array
		//have a comparable method in tweetGroups class and even in the main class
		
		for(int i = 0; i<groups.size(); i++){
			if(groups.get(i).groupName.equals(group)){
				return null;
			}
		}
		return groups.get(1).getStati();
	}
	
	public String[] getUsers(String group){
		//returns the users in group
		for(int i = 0; i<groups.size(); i++){
			if(groups.get(i).groupName.equals(group)){
				return groups.get(i).getUsers();
			}
		}
		return null;
	}
	
	
	
	
	
private class Twtgrp{
	private String groupName;
	private ArrayList<String> users;
	private ArrayList<Status> stati;
	
	Twtgrp(String groupName){
		//instantiates the name for the list and sets the parameter to the variable
		this.groupName = groupName;
	}
	
	private void addUser(String user){
		//adds the user to the group
		users.add(user);
	}
	
	private void removeUser(String user){
		//removes a user from the group
		users.remove(user);
	}
	
	private boolean checkUser(String user){
		//checks to see if the user is in the group(array list)
		if( users.contains(user))
			return true;
		else
			return false;
	}
	
	private Status[] getStati(){
		//gets the statuses
		//convert status to array
		//have a comparable method in tweetGroups class and even in the main class
		
		return (Status[]) stati.toArray();
	}
	
	private String[] getUsers(){
		//returns the users in group
		return (String[]) users.toArray();
	}
	
	private void addStatus(Status status){
		//adds a status to the array list
		stati.add(status);
	
	}
	

}

}