package forged.expedition.networking.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by visitor15 on 9/22/14.
 */
public class HttpConnector {

    private HttpURLConnection httpConnection;

    private URL mUrl;

    public HttpConnector() {}

    public String postForReponse(String url) {
        String response = "";
        try {
            mUrl = new URL(url);

            httpConnection = (HttpURLConnection) mUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Content-Language", Locale.getDefault().getISO3Language());

            httpConnection.setUseCaches(false);
            httpConnection.setDoInput(true);

            InputStream inStream = httpConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            String line;
            StringBuffer strResponse = new StringBuffer();

            while((line = reader.readLine()) != null) {
                strResponse.append(line);
            }

            reader.close();

            response = strResponse.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(httpConnection != null) {
                httpConnection.disconnect();
            }
        }

        return response;
    }
}
