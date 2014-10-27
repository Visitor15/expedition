package forged.expedition.topics;

import java.util.List;

import forged.expedition.R;
import forged.expedition.data.JsonData;

/**
 * Created by nchampagne on 10/22/14.
 */
public class TalksAndInterviewsTopic extends AbstractTopic<TalksAndInterviewsTopic> {

    public static final String TALKS_AND_INTERVIEWS_TOPIC_ID = "talks-and-interviews";

    public static final int DEFAULT_COLOR = R.color.orange_overlay;

    public TalksAndInterviewsTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return TALKS_AND_INTERVIEWS_TOPIC_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public TalksAndInterviewsTopic fromJson(JsonData data) {
        return null;
    }

    @Override
    public int getDefaultColorId() {
        return DEFAULT_COLOR;
    }
}
