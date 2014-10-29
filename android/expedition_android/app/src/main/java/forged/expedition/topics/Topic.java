package forged.expedition.topics;

import forged.expedition.data.JsonData;

/**
 * Created by visitor15 on 9/29/14.
 */
public interface Topic<T> extends JsonData<T> {

    public String getTopicId();

    public String getDisplayName();

    public int getDefaultColorId();

    public void setTopicId(String id);

    public void setDisplayTitle(String title);

}
