package forged.expedition.topics;

import java.util.List;

import forged.expedition.R;
import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public class PartnerContentTopic extends AbstractTopic<PartnerContentTopic> {

    public static final String PARTNER_CONTENT_ID = "partner-content";

    public static final int DEFAULT_COLOR = R.color.green_overlay;

    PartnerContentTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return PARTNER_CONTENT_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public PartnerContentTopic fromJson(JsonData data) {
        return null;
    }

    @Override
    public int getDefaultColorId() {
        return DEFAULT_COLOR;
    }
}
