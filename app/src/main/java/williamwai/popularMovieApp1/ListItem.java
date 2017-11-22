package williamwai.popularMovieApp1;

/**
 * Created by williamwai on 11/15/17.
 */

public class ListItem {

    private String head;
    private String desc;
    private String rating;
    private String release;
    private String imageUrl;


    public ListItem(String head, String desc, String rating, String release, String imageUrl) {
        this.head = head;
        this.desc = desc;
        this.rating = rating;
        this.release = release;
        this.imageUrl = imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return rating;
    }

    public String getRelease() {
        return release;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setHead(String head) { this.head = head; }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRelease(String rating) {
        this.release = release;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
