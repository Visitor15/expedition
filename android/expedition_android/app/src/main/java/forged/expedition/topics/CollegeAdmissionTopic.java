package forged.expedition.topics;

import java.util.List;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 10/22/14.
 */
public class CollegeAdmissionTopic extends AbstractTopic<CollegeAdmissionTopic> {

    public static final String COLLEGE_ADMISSION_TOPIC_ID = "college-admissions";

    public CollegeAdmissionTopic() {

    }

    @Override
    public List<TopicKeys> getTopicKeys() {
        return null;
    }

    @Override
    public String getTopicId() {
        return COLLEGE_ADMISSION_TOPIC_ID;
    }

    @Override
    public JsonData toJsonData() {
        return null;
    }

    @Override
    public CollegeAdmissionTopic fromJson(JsonData data) {
        return null;
    }
}
