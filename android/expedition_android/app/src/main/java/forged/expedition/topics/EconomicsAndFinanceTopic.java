package forged.expedition.topics;

import java.util.List;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public class EconomicsAndFinanceTopic extends AbstractTopic<EconomicsAndFinanceTopic> {

    public static final String ECONOMICS_AND_FINANCE_ID = "economics-finance-domain";

    public EconomicsAndFinanceTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return ECONOMICS_AND_FINANCE_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public EconomicsAndFinanceTopic fromJson(JsonData data) {
        return null;
    }
}
