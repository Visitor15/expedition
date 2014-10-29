package forged.expedition.topics;

import java.util.List;

import forged.expedition.R;
import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public class ArtsAndHumanitiesTopic extends AbstractTopic<ArtsAndHumanitiesTopic> {

    public static final String ARTS_AND_HUMANITIES_ID = "humanities";

    public static final int DEFAULT_COLOR = R.color.purple_overlay;

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

    @Override
    public int getDefaultColorId() {
        return DEFAULT_COLOR;
    }

    @Override
    public void setTopicId(String id) {
        this.id = id;
    }

    @Override
    public void setDisplayTitle(String title) {
        this.title = title;
    }
}
