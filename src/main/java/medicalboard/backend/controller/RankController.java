package medicalboard.backend.controller;

import medicalboard.backend.entity.Statistics;
import medicalboard.backend.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {

    @Autowired
    RankService rankService;

    /*
    rank
    */
    @GetMapping("/stat/rank/{job}")
    public List<Statistics> getTopThreeFavoriteStatsByJob(@PathVariable String job) {
        List<Statistics> statList= rankService.getTop3FavoriteStatsByJob(job);

        return statList;
    }
}
