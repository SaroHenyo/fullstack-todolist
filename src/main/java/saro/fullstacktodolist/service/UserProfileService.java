package saro.fullstacktodolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import saro.fullstacktodolist.model.UserProfile;
import saro.fullstacktodolist.repository.UserProfileDataAccessService;
import saro.fullstacktodolist.util.FileStoreUtil;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.*;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStoreUtil fileStoreUtil;

    public List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // Check if file is Empty
        isFileEmpty(file);

        // Check if file is image
        isImage(file);

        // Check if user exist
        UserProfile user = getUserProfileOrThrow(userProfileId);

        // Grab some meta data
        Map<String, String> metadata = extractMetadata(file);

        // Store the image in S3
        String path = String.format("%s/%s", "pampangamarketplace", user.getUserProfileId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStoreUtil.save(path, fileName, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(fileName);
        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId){
        UserProfile user = getUserProfileOrThrow(userProfileId);
        String path = String.format("%s/%s", "pampangamarketplace", user.getUserProfileId());

        return user.getUserProfileImageLink().map(key -> fileStoreUtil.download(path, key)).orElse(new byte[0]);
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID id) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile is not found", id)));
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
        }
    }

    private void isImage(MultipartFile file) {
        if (Arrays.asList(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an Image [" + file.getContentType() + "]");
        }
    }
}