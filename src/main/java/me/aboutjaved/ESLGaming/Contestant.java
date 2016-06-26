package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsan on 6/25/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contestant {

    @JsonProperty("signedUp")
    private int signedUp;

    @JsonProperty("max")
    private int max;

    @JsonProperty("checkedIn")
    private int checkedIn;

    public int getCheckedIn() {
        return checkedIn;
    }

    public int getMax() {
        return max;
    }

    public int getSignedUp() {
        return signedUp;
    }

    public void setCheckedIn(int checkedIn) {
        this.checkedIn = checkedIn;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setSignedUp(int signedUp) {
        this.signedUp = signedUp;
    }
}