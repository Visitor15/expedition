package forged.expedition.topics;

import java.util.List;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 12/20/14.
 */
public class NewAndNoteworthyTopic extends AbstractTopic<NewAndNoteworthyTopic> {
    public static final String ID = "new-and-noteworthy";

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return ID;
    }

    @Override
    public int getDefaultColorId() {
        return 0;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public NewAndNoteworthyTopic fromJson(JsonData data) {
        return null;
    }
}
