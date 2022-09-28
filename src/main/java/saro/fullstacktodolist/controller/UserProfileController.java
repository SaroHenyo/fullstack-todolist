package saro.fullstacktodolist.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saro.fullstacktodolist.model.UserProfile;
import saro.fullstacktodolist.service.UserProfileService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("getUserProfiles")
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }
}
