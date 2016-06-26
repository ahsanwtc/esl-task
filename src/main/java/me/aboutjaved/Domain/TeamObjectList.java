package me.aboutjaved.Domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by jsan on 6/25/2016.
 */
public class TeamObjectList {

    private final Map<String, TeamObject> teamObjects;

    @JsonCreator
    public TeamObjectList(@JsonProperty("teamObjects") Map<String, TeamObject> teamObjects) {
        this.teamObjects = teamObjects;
    }
}
