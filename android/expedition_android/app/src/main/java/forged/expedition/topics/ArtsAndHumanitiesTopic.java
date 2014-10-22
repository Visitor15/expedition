package forged.expedition.topics;

import java.util.List;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public class ArtsAndHumanitiesTopic extends AbstractTopic<ArtsAndHumanitiesTopic> {

    public static final String ARTS_AND_HUMANITIES_ID = "humanities";

    public ArtsAndHumanitiesTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return ARTS_AND_HUMANITIES_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public ArtsAndHumanitiesTopic fromJson(JsonData data) {
        return null;
    }
}
