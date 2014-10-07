package forged.expedition.data;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by visitor15 on 10/6/14.
 */
public class JsonMapper {

    protected HashMap<String, String> jsonMap;

    public JsonMapper() { init(); }

    private void init() {
        jsonMap = new HashMap<String, String>();
    }

    public void addToJsonModel(final String key, final String value) {
        jsonMap.put(key, value);
    }

    public String getValue(final String key) {
        return jsonMap.get(key);
    }

    public String getKeyForValue(final String value) {
        Set<String> keySet = jsonMap.keySet();

        for(String key : keySet) {
            if(jsonMap.get(key).equals(value)) {
                return key;
            }
        }

        return "";
    }
}
