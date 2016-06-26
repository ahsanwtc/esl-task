package me.aboutjaved.Service;

import me.aboutjaved.ESLGaming.Cup;
import me.aboutjaved.ESLGaming.CupList;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;


/**
 * Interface for the behaviour of the services to implement.
 *
 * Created by jsan on 6/25/2016.
 */

public interface CupListInterface {
    ListenableFuture<ResponseEntity<Map<String, CupList>>> getCups();
    ListenableFuture<ResponseEntity<Cup>> getCup(int cupId, int limit);
}
