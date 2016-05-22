package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Facilities {
    public String whiteboard;
    public String projector;
    public String internet;
    public String wifi;
    public String intercom;
    public String teleconferencing;
    public String videoconferencing;

    public Facilities(String whiteboard, String projector, String internet, String wifi, String intercom, String teleconferencing, String videoconferencing) {
        this.whiteboard = whiteboard;
        this.projector = projector;
        this.internet = internet;
        this.wifi = wifi;
        this.intercom = intercom;
        this.teleconferencing = teleconferencing;
        this.videoconferencing = videoconferencing;
    }
    private static Gson gson = new GsonBuilder().create();

    public String toJsonString() {
        String jsonStr = gson.toJson(this);
        return jsonStr;
    }
    
}
