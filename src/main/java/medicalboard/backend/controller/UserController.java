package medicalboard.backend.controller;

import medicalboard.backend.DTO.DashboardDTO;
import medicalboard.backend.DTO.UserDTO;
import medicalboard.backend.entity.Dashboard;
import medicalboard.backend.mapper.UserMapper;
import medicalboard.backend.service.DashboardService;
import medicalboard.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/api/users/info")
    public ResponseEntity<Object> getUserInfo(@RequestBody UserDTO userDTO) {

        UserDTO result = UserMapper.entityToDto(userService.getUser(userDTO.getUserId()).get());
        result.clearPassword();
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @PostMapping("/api/users/dashboard/{pageNum}")
    public DashboardDTO getDashboard(@PathVariable Integer pageNum, @RequestBody UserDTO userDTO) {
        DashboardDTO result = dashboardService.getDashboard(userDTO, pageNum);

        return result;
    }
}
