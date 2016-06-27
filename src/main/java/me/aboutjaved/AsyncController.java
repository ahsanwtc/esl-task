package me.aboutjaved;

import me.aboutjaved.ESLGaming.Cup;
import me.aboutjaved.ESLGaming.CupList;
import me.aboutjaved.ESLGaming.Ranking;
import me.aboutjaved.ESLGaming.TeamResult;
import me.aboutjaved.Service.CupListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;

import static java.lang.String.format;

/**
 * Controller class
 */

@RestController
class AsyncController {

    /**
     * Injection of CupListService service.
     */
    @Autowired
    private CupListService cupListService;

    /**
     * Listens to the HTTP GET and schedules the API calls asynchronously. The calls are asynchronous, a
     * DeferredResult is being returned.
     *
     * Web Service entry point @ "/". http://localhost:8080
     *
     *
     * @return DeferredResult
     */
    @RequestMapping("/")
    DeferredResult<?> esl() {
        final DeferredResult<Map<String, TeamResult>> result = new DeferredResult<>();

        /* calling the service to fetch the list of the cups. */
        ListenableFuture<ResponseEntity<Map<String, CupList>>> future = cupListService.getCups();
        /* adding callbacks to trigger when request completes. */
        future.addCallback(new ListenableFutureCallback<ResponseEntity<Map<String, CupList>>>() {
            @Override
            public void onFailure(Throwable throwable) {
                result.setErrorResult(throwable.getMessage());
            }

            @Override
            public void onSuccess(ResponseEntity<Map<String, CupList>> cupListResponseEntity) {
                log("Success");
                /* list to keep track of single cup requests. */
                List<CupList> cupList = new ArrayList<CupList>();

                /* response map of the initial call. */
                Map<String, CupList> map = cupListResponseEntity.getBody();

                /* end map which will be sent in the response. */
                Map<String, TeamResult> teamResultMap = new HashMap<>();
                for(Map.Entry<String, CupList> entry: map.entrySet()) {
                    /* adding cup to the list to mark it as running. */
                    cupList.add(entry.getValue());

                    /* calling the service to fetch a cup. */
                    ListenableFuture<ResponseEntity<Cup>> newFuture = cupListService.getCup(entry.getValue().getId(),
                            entry.getValue().getContestant().getCheckedIn());


                    newFuture.addCallback(new ListenableFutureCallback<ResponseEntity<Cup>>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            log("Error: " + throwable.getMessage());
                            /* removing cup from the list to mark it as done. */
                            cupList.remove(entry.getValue());
                        }

                        @Override
                        public void onSuccess(ResponseEntity<Cup> responseEntity) {
                            log(responseEntity.getHeaders().toString());
                            Cup cup = responseEntity.getBody();
                            List<Ranking> ranking = cup.ranking();
                            for(Ranking r: ranking) {
                                String key = Integer.toString(r.getTeam().getId());
                                /* find if team is already in the map */
                                if(teamResultMap.containsKey(key)) {
                                    TeamResult tr = teamResultMap.get(key);
                                    tr.setCupsPlayed(tr.getCupsPlayed() + 1);
                                    if(r.getPosition() < tr.getBestPosition())
                                        tr.setBestPosition(r.getPosition());
                                    if(r.getPosition() > tr.getWorstPosition())
                                        tr.setWorstPosition(r.getPosition());
                                    System.out.println(format("Changing team: %s, BestPosition: %s, WorstPosition: %s", tr.getId(), tr.getBestPosition(), tr.getWorstPosition()));
                                    teamResultMap.put(key, tr);
                                }
                                else { /* create a new team to keep track ot its progress */
                                    System.out.println(format("Creating new team: %s, Position: %s", r.getTeam().getId(), r.getPosition()));
                                    teamResultMap.put(key, new TeamResult(r.getTeam().getId(), r.getPosition(), r.getPosition(), 1));
                                }
                            }
                            /* removing cup from the list to mark it as done. */
                            cupList.remove(entry.getValue());
                        }
                    });
                }

                /* wait till all the requests are done */
                while (!cupList.isEmpty()) {
                    try {
                        /* check after 10ms */
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /* set the result which will mark it done and send the response to the client. */
                result.setResult(teamResultMap);

                System.out.print("-----------------------------------------------------------------------------");
                    for (Map.Entry<String, TeamResult> tr: teamResultMap.entrySet())
                        System.out.println(tr.toString());
            }
        });
        return result;
    }

    public static void log(Object message) {
        System.out.println(format("%s %s ",Thread.currentThread().getName(), message));
    }



}
