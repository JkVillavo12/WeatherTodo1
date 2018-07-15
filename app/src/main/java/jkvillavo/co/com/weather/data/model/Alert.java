
package jkvillavo.co.com.weather.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alert {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("regions")
    @Expose
    private List<String> regions = null;
    @SerializedName("severity")
    @Expose
    private String severity;
    @SerializedName("time")
    @Expose
    private Double time;
    @SerializedName("expires")
    @Expose
    private Double expires;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getExpires() {
        return expires;
    }

    public void setExpires(Double expires) {
        this.expires = expires;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
