package Server;

public class Utils
{
    //Network constants
    public static final int PORT = 6000;
    public static final int BLOCKING_QUEUE_CAPACITY = 20;
    public static final long MONITOR_SLEEP_TIMER = 1000;

    //Key Bytes Was generated using the keyGenerator class in another program. the Key Bytes for Client and Server are identical.
    public static final byte[] ENCRYPTION_KEY_BYTES = { -85 ,-83 ,-6 ,82 ,93 ,125 ,-36 ,-35 ,-104 ,33 ,4 ,96, 49 ,-75,
            -125, 113, -96, 114, -51, -109, 18, 33, -98, 27, 18, -79, -39, 65, 71, 76, 42, -116 };


    //Post types
    public static final String POST_IMAGE = ".jpg";
    public static final String POST_VIDEO = ".mp4";


    //File directories
    public static final String DIR_SERVER_PROFILE_PICTURES = "src/main/java/Server/Resources/Profile_Pictures/";
    public static final String DIR_SERVER_POSTS = "src/main/java/Server/Resources/";
    public static final String DIR_CLIENT_POST_VIDEOS = "src/main/java/Client/Resources/Post_Videos/";


    //Request titles
    public static class REQ {
        public static final String LOGIN = "login";
        public static final String SIGNUP = "signup";
        public static final String SEARCH = "search";
        public static final String MY_PROFILE = "showMyProfile";
        public static final String PROFILE = "showProfile";
        public static final String FOLLOW = "follow";
        public static final String UNFOLLOW = "unfollow";
        public static final String BIO = "setBio";
        public static final String LOGOUT = "logout";
        public static final String LIKE = "like";
        public static final String UNLIKE = "unlike";
        public static final String COMMENT = "comment";
        public static final String CREATE_POST = "createPost";
        public static final String DELETE_POST = "deletePost";
        public static final String TIMELINE = "getTimeline";
        public static final String NOTIFICATION = "notification";
        public static final String PERSONAL_CHAT = "getPersonalChat";
        public static final String GROUP_CHAT = "getGroupChat";
        public static final String ALL_CHATS = "getAllChats";
        public static final String MESSAGE = "addMessage";
        public static final String ADD_MEMBER = "addMember";
        public static final String TERMINATE = "terminate";
    }


    //Collection Names
    public static class DB {
        public static final String DATABASE_NAME = "Instagram";
        public static final String LOGIN = "Login";
        public static final String FOLLOW = "Follow";
        public static final String BIO = "Bio";
        public static final String POST = "Post";
        public static final String PERSONAL_CHAT = "PersonalChat";
        public static final String GROUP_CHAT = "GroupChat";
        public static final String CHAT_ID = "ChatID";
    }


    //MongoDB Keys
    public static class KEY {
        public static final String USERNAME = "Username";
        public static final String PASSWORD  = "Password";
        public static final String BIO = "Bio";
        public static final String FOLLOWING = "Following";
        public static final String FOLLOWERS = "Followers";
        public static final String POST_TYPE = "PostType";
        public static final String CAPTION = "Caption";
        public static final String POST_ID = "PostID";
        public static final String COMMENTS = "Comments";
        public static final String LIKED_BY = "LikedBy";
        public static final String DATE = "Date";
        public static final String CHAT_ID = "ChatID";
        public static final String CHAT_ID_LIST = "ChatIDList";
        public static final String MEMBERS = "Members";
        public static final String MESSAGE_LIST = "MessageList";
    }
}
