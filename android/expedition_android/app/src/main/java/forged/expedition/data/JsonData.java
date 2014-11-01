package forged.expedition.data;

import java.io.Serializable;

/**
 * Created by visitor15 on 9/22/14.
 */
public interface JsonData<T> extends Serializable {

    public JsonData toJsonData();

    public T fromJson(final JsonData data);
}
