package Client.Model;

import Client.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class User
{
    private String username, password, bioText;
    private ArrayList<String> followers, following;
    private ArrayList<Post> posts;
    private String profilePicture;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
        bioText = "";
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getFollowers() { return followers; }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public void addFollowing(String username) {
        following.add(username);
    }

    public void addFollower(String username) {
        followers.add(username);
    }

    public void removeFollowing(String username) {
        following.remove(username);
    }

    public void removeFollowers(String username) {
        followers.remove(username);
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public String getBioText() {
        return bioText;
    }

    public void setBioText(String bioText) {
        this.bioText = bioText;
    }

    public List<Post> getPosts() { return posts; }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public byte[] getProfilePicture() {
        if (profilePicture != null) {
            return Base64.getDecoder().decode(profilePicture);
        }
        else {
            return null;
        }

    }

    public void setProfilePicture(String filePath) {
        try {
            File savedFile = new File(filePath);

            if (savedFile.exists())
            {
                FileInputStream in = new FileInputStream(savedFile);
                byte[] bytes = new byte[(int) savedFile.length()];
                in.read(bytes);
                this.profilePicture = new String(Base64.getEncoder().encode(bytes), "UTF-8");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getServerFilePath() { return Utils.DIR_SERVER_PROFILE_PICTURES + username + ".jpg"; }

    public void addPost(Post post) { this.posts.add(post); }

    public DBObject createLoginDBObject() {
        return new BasicDBObject(Utils.KEY.USERNAME, username)
                .append(Utils.KEY.PASSWORD, password);
    }

    public DBObject createFollowDBObject() {
        return new BasicDBObject(Utils.KEY.USERNAME, username)
                .append(Utils.KEY.FOLLOWING, following)
                .append(Utils.KEY.FOLLOWERS, followers);
    }

    public DBObject createBioDBObject() {
        return new BasicDBObject(Utils.KEY.USERNAME, username)
                .append(Utils.KEY.BIO, bioText);
    }

    public static User parseLoginDBObject(DBObject object) {
        User user = new User();
        user.setUsername( (String) object.get(Utils.KEY.USERNAME) );
        user.setPassword( (String) object.get(Utils.KEY.PASSWORD) );
        return user;
    }

    public static User parseFollowDBObject(DBObject object) {
        User user = new User();
        user.setUsername( (String) object.get("username") );
        user.setFollowing( (ArrayList<String>)object.get(Utils.KEY.FOLLOWING) );
        user.setFollowers( (ArrayList<String>)object.get(Utils.KEY.FOLLOWERS) );
        return user;
    }

    public static User parseBioDBObject(DBObject object) {
        User user = new User();
        user.setUsername( (String) object.get(Utils.KEY.USERNAME) );
        user.setBioText( (String) object.get(Utils.KEY.BIO) );
        return user;
    }

}
