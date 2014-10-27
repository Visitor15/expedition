package forged.expedition.topics;

import java.util.List;

import forged.expedition.R;
import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/15/14.
 */
public class ScienceTopic extends AbstractTopic<ScienceTopic> {

    public static final String SCIENCE_ID = "science";

    public static final int DEFAULT_COLOR = R.color.green_overlay;

    public ScienceTopic() {

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
    public ScienceTopic fromJson(JsonData data) {
        return null;
    }

    @Override
    public String getTopicId() {
        return "science";
    }

    @Override
    public int getDefaultColorId() {
        return DEFAULT_COLOR;
    }
}
