package is333;

import com.restfb.*;
import com.restfb.json.JsonObject;
import com.restfb.types.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.IOException;


public class Facebook {
    FacebookClient facebookClient = null;

    private void createClient(String token) {
        this.facebookClient = new DefaultFacebookClient(token);
        System.out.println(this.facebookClient);
    }
    private void getMyInfo() {
        JsonObject results = facebookClient.fetchObjects(Arrays.asList("me"),
                JsonObject.class, Parameter.with( "fields","name, id, home, birthday, email"));
        System.out.println(results);
    }

    private ArrayList<String> printPostContent  ()  {
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
        ArrayList<String> feedList= new ArrayList<String>();
        for (List<Post> myFeedPage : myFeed) {
            for (int i=0; i < myFeedPage.size(); i++) {
                String feedInfo= i+1 + ": " + myFeedPage.get(i).getCreatedTime() + ": " + myFeedPage.get(i).getStory() + myFeedPage.get(i).getMessage();
                feedList.add(feedInfo);
            }

        }
        return feedList;

    }
    public static void main(String[] args) throws Exception {
        Facebook fb = new Facebook();
        fb.createClient("EAAOd1NNkPrMBAFEff9a09froFOU7v0fOxfStFZBx7s3rDi2HXgXkaXeQJ2WfT2E8lk7uC6qW2yHiOvIRJzo1k1QDRiPfZAaQ4zC5C8UckSDBq47zqlMamrG4kCxsaK25gi3fFmjoUTM8AdqZB79FxbfdDZBXGX08nnm4buiBBAZDZD");
        fb.getMyInfo();
        List<String> feed =fb.printPostContent();

        PrintWriter out = new PrintWriter("fbInfo1.txt");
        for(int i = 0; i< feed.size(); i++)
        {
            out.println(feed.get(i));
        }
        out.close();
    }
}
