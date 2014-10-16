package forged.expedition.topics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visitor15 on 10/6/14.
 */
public abstract class AbstractTopic<T> implements Topic<T> {

    protected String author_key = "";
    protected String children = "";
    protected String child_data = "";
    protected String creation_date = "";
    protected String description = "";
    protected String domain_slug = "";
    protected String do_not_publish = "";
    protected String edit_slug = "";
    protected String extended_slug = "";
    protected String gplus_url = "";
    protected String has_user_authored_content_types = "";
    protected String hide = "";
    protected String icon_src = "";
    protected String id = "";
    protected String internal_id = "";
    protected String slug = "";
    protected String ka_url = "";
    protected String key = "";
    protected String kind = "";
    protected String node_slug = "";
    protected String old_key_name = "";
    protected String relative_url = "";
    protected String standalone_title = "";
    protected String tags = "";
    protected String title = "";
    protected String topics_page_url = "";
    protected String translated_title = "";
    protected String translated_standalone_title = "";
    protected String url = "";
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
}
