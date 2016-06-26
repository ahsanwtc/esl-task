package me.aboutjaved.Service;

import me.aboutjaved.ESLGaming.Cup;
import me.aboutjaved.ESLGaming.CupList;
import me.aboutjaved.ESLGaming.TeamResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jsan on 6/25/2016.
 */
@Service
public class CupListService implements CupListInterface {

    private final String CUPS_URL = "http://play.eslgaming.com/api/leagues?types=cup&states=finished&limit.total=25&path=/play/worldoftanks/europe/";

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Override
    public ListenableFuture<ResponseEntity<Map<String, CupList>>> getCups() {
        return asyncRestTemplate
                .exchange(CUPS_URL, HttpMethod.GET, HttpEntity.EMPTY,
                        new ParameterizedTypeReference<Map<String, CupList>>() { });
    }

    @Override
    public ListenableFuture<ResponseEntity<Cup>> getCup(int cupId, int limit) {
        String url = "http://play.eslgaming.com/api/leagues/" + cupId + "/ranking?limit=" + limit;
        return asyncRestTemplate.getForEntity(url, Cup.class);
    }


}
