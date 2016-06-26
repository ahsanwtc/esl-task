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

@RestController
class AsyncController {

    @Autowired
    private CupListService cupListService;

    @RequestMapping("/")
    DeferredResult<?> esl() {
        //final DeferredResult<List<CupList>> result = new DeferredResult<>();
        final DeferredResult<Map<String, TeamResult>> result = new DeferredResult<>();

        ListenableFuture<ResponseEntity<Map<String, CupList>>> future = cupListService.getCups();
        future.addCallback(new ListenableFutureCallback<ResponseEntity<Map<String, CupList>>>() {
            @Override
            public void onFailure(Throwable throwable) {
                result.setErrorResult(throwable.getMessage());
            }

            @Override
            public void onSuccess(ResponseEntity<Map<String, CupList>> cupListResponseEntity) {
                log("Success");
                List<CupList> cupList = new ArrayList<CupList>();

                Map<String, CupList> map = cupListResponseEntity.getBody();
                List<Cup> c = new ArrayList<Cup>();

                Map<String, TeamResult> teamResultMap = new HashMap<>();
                //Map<String, Boolean> requesMap = new HashMap<>();
                for(Map.Entry<String, CupList> entry: map.entrySet()) {
                    cupList.add(entry.getValue());
                    ListenableFuture<ResponseEntity<Cup>> newFuture = cupListService.getCup(entry.getValue().getId(),
                            entry.getValue().getContestant().getCheckedIn());
                    //requesMap.put(Integer.toString(entry.getValue().getId()), false);

                    newFuture.addCallback(new ListenableFutureCallback<ResponseEntity<Cup>>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            //result.setErrorResult(throwable.getMessage());
                            log("Error: " + throwable.getMessage());
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
