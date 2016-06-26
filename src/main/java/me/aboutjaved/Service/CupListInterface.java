package me.aboutjaved.Service;

import me.aboutjaved.ESLGaming.Cup;
import me.aboutjaved.ESLGaming.CupList;
import me.aboutjaved.ESLGaming.TeamResult;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;


/**
 * Created by jsan on 6/25/2016.
 */

public interface CupListInterface {
    ListenableFuture<ResponseEntity<Map<String, CupList>>> getCups();

    /*public ListenableFuture<CupList> get() {
        ListenableFuture<ResponseEntity<CupList>> cupList = asyncRestTemplate.getForEntity(QUESTIONS_URL, CupList.class);
        return new RepositoryListDtoAdapter(query, gitHubItems);
    }*/
    ListenableFuture<ResponseEntity<Cup>> getCup(int cupId, int limit);
}
