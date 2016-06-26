package me.aboutjaved.Service;

import me.aboutjaved.ESLGaming.Cup;
import me.aboutjaved.ESLGaming.CupList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Map;

/**
 * Service class witch implements the interface and override the call methods.
 *
 * Created by jsan on 6/25/2016.
 */
@Service
public class CupListService implements CupListInterface {

    /**
     * Url to the original resource to get the list of cups.
     */
    private final String CUPS_URL = "http://play.eslgaming.com/api/leagues?types=cup&states=finished&limit.total=25&path=/play/worldoftanks/europe/";

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    /**
     * A function to call and get the list of cups. As the API call is asynchronous, a ListenableFuture is being
     * returned.
     *
     * @return ListenableFuture<ResponseEntity<Map<String, CupList>>> HasMap containing id of the cup as key and
     * object as CupList
     *
     * @see CupList
     */
    @Override
    public ListenableFuture<ResponseEntity<Map<String, CupList>>> getCups() {
        return asyncRestTemplate
                .exchange(CUPS_URL, HttpMethod.GET, HttpEntity.EMPTY,
                        new ParameterizedTypeReference<Map<String, CupList>>() { });
    }

    /**
     * A function to get data for a particular cup. As the API call is asynchronous, a ListenableFuture is being
     * returned.
     *
     * @param cupId Id of the cup to be fetched.
     * @param limit Limit the results, the number of participants in the cup.
     * @return ListenableFuture<ResponseEntity<Cup>> A Cup object.
     */
    @Override
    public ListenableFuture<ResponseEntity<Cup>> getCup(int cupId, int limit) {
        String url = "http://play.eslgaming.com/api/leagues/" + cupId + "/ranking?limit=" + limit;
        return asyncRestTemplate.getForEntity(url, Cup.class);
    }


}
