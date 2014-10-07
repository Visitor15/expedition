package forged.expedition.data;

/**
 * Created by visitor15 on 9/22/14.
 */
public interface JsonData<T> {

    public JsonData toJsonData();

    public T fromJson(final JsonData data);
}
