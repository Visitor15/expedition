package forged.expedition.topics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by visitor15 on 10/6/14.
 */
public abstract class AbstractTopic<T> implements Topic<T> {

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
