package forged.expedition;

import forged.expedition.topics.Topic;

/**
 * Created by visitor15 on 9/30/14.
 */
public class KhanAcademy {

    public static enum TopicIdentifier {
        MATH
    }

    public static final String BASE_URL = "https://www.khanacademy.org";

    public static final String TOPIC_TREE_URL = BASE_URL.concat("/api/v1/topictree");

    public static final String SPECIFIC_TOPIC_URL = BASE_URL.concat("/api/v1/topic");

    public static String getTopicUrl(Topic t) {
        return SPECIFIC_TOPIC_URL.concat("/").concat(t.getTopicId());
    }

    public static String getTopicUrl(String topicId) {
        return SPECIFIC_TOPIC_URL.concat("/").concat(topicId);
    }
}
