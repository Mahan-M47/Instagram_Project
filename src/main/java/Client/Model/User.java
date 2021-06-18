package Client.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private String username, password, bioText;
    private List<String> followers, following;;
    private List<Post> posts;
    private List<Chat> chats;
    private File profilePicture;

    public User() {
    }

    public User(String username, String password, String bioText) {
        this.username = username;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
        chats = new ArrayList<>();

        this.bioText = bioText;

        following.add("Taylor");
        following.add("j");
        followers.add("Taylor");
        followers.add("j");
        followers.add("x");
        followers.add("v");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public String getBioText() { return bioText; }

    public void setBioText(String bioText) { this.bioText = bioText; }

    public DBObject getDBObject() {
        return new BasicDBObject()
                .append("username", getUsername())
                .append("password", getPassword());
    }

    public static User parseUser(DBObject object) {
        User user = new User();
        user.setUsername((String) object.get("username"));
        user.setPassword((String) object.get("password"));
        return user;
    }
}
