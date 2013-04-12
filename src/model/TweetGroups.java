package model;

import java.util.ArrayList;

import twitter4j.Status;

public class TweetGroups {
	ArrayList<Twtgrp> groups;
	public TweetGroups(){
		//this is where you load the file and parse out information
	}
	
	public void parseTimeLine(Status[] stati){
		//parse out stati into different groups
	}
	
	public String[] checkUser(String user){
		//check ALL the groups for the user
		//returns the array of group names the user is in
		return null;
	}
	
	public String[] getGroupNames(){
		//returns the names of all groups
		return null;
	}
	
	public void addToGroup(String group, String user){
		//adds the user to the group specified
	}
	
	public void removeFromGroup(String group, String user){
		//removes the user from the group
	}
	
	public void createGroup(String groupName){
		//creates a group with the specified name
	}
	
	public void destroyGroup(String group){
		//destroys the specified group
	}
	
	public void save(){
		//saves all information with groups and whatnot
		
		
	}
	public Status[] getStati(){
		//gets the statuses
		//convert status to array
		//have a comparable method in tweetGroups class and even in the main class
		return null;
	}
	
	public String[] getUsers(){
		//returns the users in group
		return null;
	}
	
	
	
	
	
private class Twtgrp{
	private String groupName;
	private ArrayList<String> users;
	private ArrayList<Status> stati;
	
	Twtgrp(String groupName){
		//instantiates the name for the list and sets the parameter to the variable
	}
	
	private void addUser(String user){
		//adds the user to the group
	}
	
	private void removeUser(String user){
		//removes a user from the group
	}
	
	private boolean checkUser(String user){
		//checks to see if the user is in the group(array list)
		return false;
	}
	
	private Status[] getStati(){
		//gets the statuses
		//convert status to array
		//have a comparable method in tweetGroups class and even in the main class
		return null;
	}
	
	private String[] getUsers(){
		//returns the users in group
		return null;
	}
	
	private void addStatus(Status status){
		//adds a status to the array list
	
	}
	

}

}