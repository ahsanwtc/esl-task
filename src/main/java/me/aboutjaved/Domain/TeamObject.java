package me.aboutjaved.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsan on 6/25/2016.
 */
public class TeamObject {

    @JsonProperty("cupsPlayed")
    private int cupsPlayed;

    @JsonProperty("bestPosition")
    private int bestPosition;

    @JsonProperty("worstPosition")
    private int worstPosition;

    public TeamObject(int worstPosition, int bestPosition, int cupsPlayed) {
        this.worstPosition = worstPosition;
        this.bestPosition = bestPosition;
        this.cupsPlayed = cupsPlayed;
    }
}
