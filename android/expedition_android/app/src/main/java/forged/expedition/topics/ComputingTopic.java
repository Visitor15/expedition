package forged.expedition.topics;

import java.util.List;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public class ComputingTopic extends AbstractTopic<ComputingTopic> {

    public static final String COMPUTING_TOPIC_ID = "computing";

    public ComputingTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return COMPUTING_TOPIC_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public ComputingTopic fromJson(JsonData data) {
        return null;
    }
}
