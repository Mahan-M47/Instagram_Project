package Server.Controller;

import Server.Model.GroupChat;
import Server.Model.PersonalChat;
import Server.Model.Post;
import Server.Model.User;
import Server.Utils;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainManager
{
//    All of the Main processes of the Client are handled by this class.
    public synchronized static Response process(Request req, AtomicBoolean state)
    {
        Data dat = req.getData();
        User user;
        Post post;
        boolean flag;

        switch ( req.getTitle() )
        {
            case Utils.REQ.SIGNUP:
                if (! DatabaseManager.checkIfUserExists(dat.clientUsername) )
                {
                    user = new User(dat.clientUsername, dat.dataString);
                    DatabaseManager.adduser(user);
                    flag = true;
                }
                else flag = false;
                return new Response(Utils.REQ.SIGNUP, new Data(dat.clientUsername, flag) );


            case Utils.REQ.LOGIN:
                flag = DatabaseManager.checkLogin(dat.clientUsername, dat.dataString);
                return new Response(Utils.REQ.LOGIN, new Data(dat.clientUsername, flag) );


            case Utils.REQ.SEARCH:
                ArrayList<String> results = DatabaseManager.searchUser( dat.dataString );
                return new Response(Utils.REQ.SEARCH, new Data(results) );


            case Utils.REQ.PROFILE:
                user = DatabaseManager.assembleUser(dat.clientUsername);
                return new Response(Utils.REQ.PROFILE, new Data(user) );


            case Utils.REQ.MY_PROFILE:
                user = DatabaseManager.assembleUser(dat.clientUsername);
                return new Response(Utils.REQ.MY_PROFILE, new Data(user) );


            case Utils.REQ.FOLLOW:
                DatabaseManager.follow(dat.clientUsername, dat.dataString);
                NotificationManager.followNotification(dat.clientUsername, dat.dataString);
                user = DatabaseManager.assembleUser(dat.dataString);
                return new Response(Utils.REQ.FOLLOW, new Data(user));


            case Utils.REQ.UNFOLLOW:
                DatabaseManager.unfollow(dat.clientUsername, dat.dataString);
                user = DatabaseManager.assembleUser(dat.dataString);
                return new Response(Utils.REQ.FOLLOW, new Data(user));


            case Utils.REQ.BIO:
                DatabaseManager.setBio(dat.user);
                if (dat.user.getProfilePicture() != null) {
                    DatabaseManager.setProfilePicture(dat.user);
                }
                break;


            case Utils.REQ.CREATE_POST:
                DatabaseManager.createPost(dat.post);
                NotificationManager.postNotification(dat.clientUsername);
                break;


            case Utils.REQ.DELETE_POST:
                DatabaseManager.deletePost(dat.dataString);
                user = DatabaseManager.assembleUser(dat.clientUsername);
                return new Response(Utils.REQ.DELETE_POST, new Data(user));


            case Utils.REQ.LIKE:
                post = DatabaseManager.like(dat.clientUsername, dat.dataString);
                NotificationManager.likeNotification(dat.clientUsername, post);
                break;


            case Utils.REQ.UNLIKE:
                DatabaseManager.unlike(dat.clientUsername, dat.dataString);
                break;


            case Utils.REQ.COMMENT:
                post = DatabaseManager.comment(dat.text, dat.postID);
                NotificationManager.commentNotification(dat.clientUsername, post);
                break;


            case Utils.REQ.TIMELINE:
                ArrayList<Post> posts = DatabaseManager.assembleTimeline(dat.clientUsername);
                return new Response(Utils.REQ.TIMELINE, new Data(posts));


            case Utils.REQ.PERSONAL_CHAT:
                String chatID = DatabaseManager.checkIfPersonalChatExists(dat.clientUsername, dat.dataString);
                PersonalChat chat;

                if ( chatID == null ) {
                    chat = DatabaseManager.createPersonalChat(dat.clientUsername, dat.dataString);
                }
                else chat = DatabaseManager.getPersonalChat(chatID);

                return new Response(Utils.REQ.PERSONAL_CHAT, new Data(chat));


            case Utils.REQ.GROUP_CHAT:
                GroupChat groupChat = DatabaseManager.createGroupChat(dat.clientUsername);
                return new Response(Utils.REQ.GROUP_CHAT, new Data(groupChat));


            case Utils.REQ.ALL_CHATS:
                ArrayList<PersonalChat> personalChats = DatabaseManager.getAllPersonalChats(dat.clientUsername);
                ArrayList<GroupChat> groupChats =DatabaseManager.getAllGroupChats(dat.clientUsername);
                return new Response(Utils.REQ.ALL_CHATS, new Data(personalChats, groupChats));


            case Utils.REQ.MESSAGE:
                ArrayList<String> members = DatabaseManager.addMessage(dat.dataString, dat.message);
                NotificationManager.messageNotification(dat.message.getSender(), members);
                break;


            case Utils.REQ.ADD_MEMBER:
                if ( DatabaseManager.checkIfUserExists(dat.clientUsername) )
                {
                    GroupChat updatedChat = DatabaseManager.addMember(dat.dataString, dat.clientUsername);
                    return new Response(Utils.REQ.ADD_MEMBER, new Data(updatedChat));
                }
                else return new Response(Utils.REQ.ADD_MEMBER, new Data(false));


            case Utils.REQ.LOGOUT:
                removeClient(dat.clientUsername);
                break;


            case Utils.REQ.TERMINATE:
                state.set(false);
                break;
        }

        return null;

        //each Request.title corresponds to a case in the switch statement which summons a method from Database Manager
        //to process the Request. Some of these methods return a Response object which is sent back to the client.
    }


    //Nethods for managing Active Clients
    public static List<ActiveClient> activeClients = new ArrayList<>();

    public synchronized static void addClient(ActiveClient client) {
        activeClients.add(client);
        System.out.println("User \"" + client.getUsername() + "\" Logged In.");
    }

    public synchronized static void removeClient(String username)
    {
        for (ActiveClient client : activeClients)
        {
            if ( client.getUsername().equals(username) ) {
                activeClients.remove(client);
                System.out.println("User \"" + client.getUsername() + "\" Logged Out.");
                break;
            }
        }
    }

    public synchronized static void removeClient(BlockingQueue<Response> queue)
    {
        for (ActiveClient client : activeClients)
        {
            if ( client.getQueue().equals(queue) ) {
                activeClients.remove(client);
                System.out.println("User \"" + client.getUsername() + "\" Logged Out.");
                break;
            }
        }
    }

}
