package forged.expedition.topics;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visitor15 on 10/6/14.
 */
public abstract class AbstractTopic<T> implements Topic<T> {

    @SerializedName("author_key")
    protected String author_key = "";

    @SerializedName("children")
    protected String children = "";

    @SerializedName("child_data")
    protected String child_data = "";

    @SerializedName("creation_date")
    protected String creation_date = "";

    @SerializedName("description")
    protected String description = "";

    @SerializedName("domain_slug")
    protected String domain_slug = "";

    @SerializedName("do_not_publish")
    protected String do_not_publish = "";

    @SerializedName("edit_slug")
    protected String edit_slug = "";

    @SerializedName("extended_slug")
    protected String extended_slug = "";

    @SerializedName("gplus_url")
    protected String gplus_url = "";

    @SerializedName("has_user_authored_content_types")
    protected String has_user_authored_content_types = "";

    @SerializedName("hide")
    protected String hide = "";

    @SerializedName("icon_src")
    protected String icon_src = "";

    @SerializedName("id")
    protected String id = "";

    @SerializedName("internal_id")
    protected String internal_id = "";

    @SerializedName("slug")
    protected String slug = "";

    @SerializedName("ka_url")
    protected String ka_url = "";

    @SerializedName("key")
    protected String key = "";

    @SerializedName("kind")
    protected String kind = "";

    @SerializedName("node_slug")
    protected String node_slug = "";

    @SerializedName("old_key_name")
    protected String old_key_name = "";

    @SerializedName("relative_url")
    protected String relative_url = "";

    @SerializedName("standalone_title")
    protected String standalone_title = "";

    @SerializedName("tags")
    protected String tags = "";

    @SerializedName("title")
    protected String title = "";

    @SerializedName("topics_page_url")
    protected String topics_page_url = "";

    @SerializedName("translated_title")
    protected String translated_title = "";

    @SerializedName("translated_standalone_title")
    protected String translated_standalone_title = "";

    @SerializedName("url")
    protected String url = "";

    @SerializedName("web_url")
    protected String web_url = "";

    protected List<Topic> childTopics;

    protected List<TopicKeys> topicKeys;

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
}
