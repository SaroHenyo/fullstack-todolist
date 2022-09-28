package saro.fullstacktodolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saro.fullstacktodolist.model.UserProfile;
import saro.fullstacktodolist.repository.UserProfileDataAccessService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileDataAccessService userProfileDataAccessService;

    public List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }
}