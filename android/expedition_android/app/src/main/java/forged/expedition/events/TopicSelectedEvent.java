package forged.expedition.events;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class TopicSelectedEvent {
    public static final String ACTION_TOPIC_SELECTED= "forged.mobile.TOPIC_SELECTED";
    public static final String UPDATE_KEY = "forged.mobile.UPDATE_KEY";

    public static IntentFilter createFilter() {
        return new IntentFilter(TopicSelectedEvent.ACTION_TOPIC_SELECTED);
    }

    public static void broadcast(final Context context, final Bundle b) {
        final Intent intent = new Intent(ACTION_TOPIC_SELECTED);
        intent.putExtras(b);
        LocalBroadcastReceiver.sendLocalBroadcast(context, intent);
    }

    private TopicSelectedEvent() {
        throw new UnsupportedOperationException();
    }
}
