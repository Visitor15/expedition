package forged.expedition.topics;

import java.util.List;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/6/14.
 */
public class MathTopic extends AbstractTopic<MathTopic> {

    public MathTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public MathTopic fromJson(JsonData data) {
        return null;
    }

    @Override
    public String getTopicId() {
        return "math";
    }
}
