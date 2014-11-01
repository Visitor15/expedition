package forged.expedition.topics;

import com.google.gson.annotations.SerializedName;

import forged.expedition.R;
import forged.expedition.data.YouTubeDownloadUrl;

/**
 * Created by nchampagne on 11/1/14.
 */
public class VideoTopic extends GenericTopic {
    @SerializedName("translated_youtube_id")
    public String translated_youtube_id = "";
    @SerializedName("license_logo_url")
    public String license_logo_url = "";
    @SerializedName("has_questions")
    public String has_questions = "";
//    @SerializedName("download_urls")
    public YouTubeDownloadUrl download_urls = new YouTubeDownloadUrl();

    public VideoTopic() {

    }

    @Override
    public int getDefaultColorId() {
        return R.color.half_baked;
}

    public String getTranslatedYouTubeID() {
        return translated_youtube_id;
    }

    public String getRelativeUrl() {
        return relative_url;
    }

    public String getLicenseLogoUrl() {
        return license_logo_url;
    }

    public Boolean getHasQuestions() {
        return has_questions.equals("true");
    }

    public String getCreationDate() {
        return creation_date;
    }

    public YouTubeDownloadUrl getDownloadUrls() {
        return download_urls;
    }

    public Boolean hasDownloadUrls() {
        return download_urls != null;
    }

    @Override
    public String getYouTubeId() {
        return getTranslatedYouTubeID();
    }
}
