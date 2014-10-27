package forged.expedition.topics;

import java.util.List;

import forged.expedition.R;
import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public  class TestPrepTopic extends AbstractTopic<TestPrepTopic> {

    public static final String TEST_PREP_TOPIC_ID = "test-prep";

    public static final int DEFAULT_COLOR = R.color.green_overlay;

    public TestPrepTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return TEST_PREP_TOPIC_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public TestPrepTopic fromJson(JsonData data) {
        return null;
    }

    @Override
    public int getDefaultColorId() {
        return DEFAULT_COLOR;
    }
}
