package Server.Controller;

import Server.Model.*;
import java.util.ArrayList;
import java.util.List;

public class Data
{
    // Objects of the Data class are used to store data transferred between the Client and the Server.
    public String clientUsername = null;
    public ArrayList<String> usernameList = null;
    public String dataString = null;
    public String postID = null;
    public String text = null;
    public User user = null;
    public Post post = null;
    public List<Post> posts = null;
    public Notification notification = null;
    public Message message = null;
    public PersonalChat personalChat = null;
    public GroupChat groupChat = null;
    public ArrayList<PersonalChat> personalChatList = null;
    public ArrayList<GroupChat> groupChatList = null;
    public boolean flag;

    public Data() {}

    public Data(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    //used by all Requests and Responses with just ONE String of data other than the client's username. (e.g. login)
    public Data(String clientUsername, String dataString) {
        this.clientUsername = clientUsername;
        this.dataString = dataString;
    }

    //only used by the server so there's no need to add the client's username. (e.g. returning search results)
    public Data(ArrayList<String> usernameList) {
        this.usernameList = new ArrayList<>(usernameList);
    }

    public Data(String clientUsername, String postID, String text) {
        this.clientUsername = clientUsername;
        this.postID = postID;
        this.text = text;
    }

    //only used by the server so there's no need to add the client's username. (e.g. showing a user's profile)
    public Data(User user) {
        this.user = user;
    }

    public Data(String clientUsername, Post post) {
        this.clientUsername = clientUsername;
        this.post = post;
    }

    //only used by the server so there's no need to add the client's username. (e.g. posts in the Timeline)
    public Data(List<Post> posts) {
        this.posts = new ArrayList<>(posts);
    }

    public Data(Notification notification) {
        this.notification = notification;
    }

    public Data(PersonalChat chat) {
        this.personalChat = chat;
    }

    public Data(GroupChat chat) {
        this.groupChat = chat;
    }

    public Data(ArrayList<PersonalChat> personalChatList, ArrayList<GroupChat> groupChatList) {
        this.personalChatList = new ArrayList<>(personalChatList);
        this.groupChatList = new ArrayList<>(groupChatList);
    }

    public Data(String chatID, Message message) {
        this.dataString = chatID;
        this.message = message;
    }

    public Data(boolean flag) {
        this.flag = flag;
    }

    public Data(String clientUsername, boolean flag) {
        this.clientUsername = clientUsername;
        this.flag = flag;
    }

}
