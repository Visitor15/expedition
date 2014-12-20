package forged.expedition.topics;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visitor15 on 10/6/14.
 */
public abstract class AbstractTopic<T> implements Topic<T> {

    @SerializedName("author_key")
    public String author_key = "";

    @SerializedName("children")
    public String children = "";

    @SerializedName("child_data")
    public String child_data = "";

    @SerializedName("creation_date")
    public String creation_date = "";

    @SerializedName("description")
    public String description = "";

    @SerializedName("domain_slug")
    public String domain_slug = "";

    @SerializedName("do_not_publish")
    public String do_not_publish = "";

    @SerializedName("edit_slug")
    public String edit_slug = "";

    @SerializedName("extended_slug")
    public String extended_slug = "";

    @SerializedName("gplus_url")
    public String gplus_url = "";

    @SerializedName("has_user_authored_content_types")
    public String has_user_authored_content_types = "";

    @SerializedName("hide")
    public String hide = "";

    @SerializedName("icon_src")
    public String icon_src = "";

    @SerializedName("id")
    public String id = "";

    @SerializedName("internal_id")
    public String internal_id = "";

    @SerializedName("slug")
    public String slug = "";

    @SerializedName("ka_url")
    public String ka_url = "";

    @SerializedName("key")
    public String key = "";

    @SerializedName("kind")
    public String kind = "";

    @SerializedName("node_slug")
    public String node_slug = "";

    @SerializedName("old_key_name")
    public String old_key_name = "";

    @SerializedName("relative_url")
    public String relative_url = "";

    @SerializedName("standalone_title")
    public String standalone_title = "";

    @SerializedName("tags")
    public String tags = "";

    @SerializedName("title")
    public String title = "";

    @SerializedName("topics_page_url")
    public String topics_page_url = "";

    @SerializedName("translated_title")
    public String translated_title = "";

    @SerializedName("translated_standalone_title")
    public String translated_standalone_title = "";

    @SerializedName("url")
    public String url = "";

    @SerializedName("web_url")
    public String web_url = "";

    public List<Topic> childTopics;

    public List<TopicKeys> topicKeys;

    public AbstractTopic() {};

    private void init() {
        childTopics = new ArrayList<Topic>();
    }

    public void addChildTopic(Topic t) {
        childTopics.add(t);
    }

    public boolean hasChildren() {
        return childTopics.size() > 0;
    }

    public abstract List<TopicKeys> getTopicKeys();

    public String getDisplayName() {
        return title;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    @Override
    public void setTopicId(String id) {
        this.id = id;
    }

    @Override
    public void setDisplayTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTopicKind() {
        return this.kind;
    }

    @Override
    public String getYouTubeId() {
        return "";
    }
}
