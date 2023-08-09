package medicalboard.backend.controller;

import medicalboard.backend.entity.Rank;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.repository.RankRepository;
import medicalboard.backend.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RankController {

    @Autowired
    RankService rankService;
    @Autowired
    RankRepository rankRepository;

    /*
    rank
    */
    @GetMapping("api/stat/rank/{job}")
    public List<Statistics> getTopThreeFavoriteStatsByJob(@PathVariable String job) {
        List<Statistics> statList= rankService.getTop3FavoriteStatsByJob(job);

        // Statistics id 가져오기
        List<String> statIdList = new ArrayList<>();
        for( Statistics stat : statList){
            statIdList.add(stat.getId().toString());
        }

        String idList = statIdList.toString().substring(1,8).replace(" ","");

        Rank rank = new Rank(job, idList);
        rankRepository.save(rank);

        return statList;
    }
}
