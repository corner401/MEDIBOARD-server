package medicalboard.backend.controller;

import medicalboard.backend.DTO.StarDTO;
import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.entity.Statistics;
import medicalboard.backend.mapper.UserMapper;
import medicalboard.backend.service.DashboardService;
import medicalboard.backend.service.StarService;
import medicalboard.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StarController {


    @Autowired
    private StarService starService;

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/api/stat/star")
    public ResponseEntity<Object> addStar(@RequestBody StarDTO starDTO) {

        starService.addStar(starDTO);
        dashboardService.addStar(starDTO);

        return ResponseEntity.status(HttpStatus.OK).body("즐겨찾기 등록됨");

    }

    /*
    rank
     */
    @GetMapping("/topThreeStats/{job}")
    public List<Statistics> getTopThreeFavoriteStatsByJob(@PathVariable String job) {
        return dashboardService.getTop3FavoriteStatsByJob(job);
    }
}
