package forged.expedition.topics;

import java.util.List;

import forged.expedition.R;
import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/28/14.
 */
public class GenericTopic extends AbstractTopic {

    public GenericTopic() {
        super();
    }

    public GenericTopic(String id) {
        this.setTopicId(id);
    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return id;
    }

    @Override
    public int getDefaultColorId() {
        return R.color.black_overlay;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public Object fromJson(JsonData data) {
        return null;
    }
}
