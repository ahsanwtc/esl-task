package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsan on 6/26/2016.
 */
public class TeamResult {
    @JsonIgnore
    private int id;

    @JsonProperty("cupsPlayed")
    private int cupsPlayed;

    @JsonProperty("bestPosition")
    private int bestPosition;

    @JsonProperty("worstPosition")
    private int worstPosition;

    public TeamResult(int id, int worstPosition, int bestPosition, int cupsPlayed) {
        this.id = id;
        this.worstPosition = worstPosition;
        this.bestPosition = bestPosition;
        this.cupsPlayed = cupsPlayed;
    }

    public int getId() {
        return id;
    }

    public int getWorstPosition() {
        return worstPosition;
    }

    public int getBestPosition() {
        return bestPosition;
    }

    public int getCupsPlayed() {
        return cupsPlayed;
    }

    public void setWorstPosition(int worstPosition) {
        this.worstPosition = worstPosition;
    }

    public void setBestPosition(int bestPosition) {
        this.bestPosition = bestPosition;
    }

    public void setCupsPlayed(int cupsPlayed) {
        this.cupsPlayed = cupsPlayed;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TeamResult { " +
                "id = " + id +
                ", cupsPlayed = " + cupsPlayed +
                ", bestPosition = " + bestPosition +
                ", worstPosition = " + worstPosition +
                '}';
    }
}
