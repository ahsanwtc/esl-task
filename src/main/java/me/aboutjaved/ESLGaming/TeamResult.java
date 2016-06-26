package me.aboutjaved.ESLGaming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for serializing the response.
 *
 * Created by jsan on 6/24/2016.
 */
public class TeamResult {

    /**
     * Id of the Team.
     */
    @JsonIgnore
    private int id;

    /**
     * Number of cups played by the team.
     */
    @JsonProperty("cupsPlayed")
    private int cupsPlayed;

    /**
     * Overall best position achieved by the team.
     */
    @JsonProperty("bestPosition")
    private int bestPosition;

    /**
     * Overall worst position achieved by the team.
     */
    @JsonProperty("worstPosition")
    private int worstPosition;

    /**
     * Constructor
     *
     * @param id
     * @param worstPosition
     * @param bestPosition
     * @param cupsPlayed
     */
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
