package com.sjiwon.studyholic.domain.file;

import com.sjiwon.studyholic.domain.file.dto.UploadProfile;
import com.sjiwon.studyholic.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileUploadService {
    @Value("${file.dir}")
    private String filePath;

    @Transactional
    public void uploadProfileImage(MultipartFile profile, User user) {
        UploadProfile uploadProfileInfo = getUploadProfileInfo(profile.getOriginalFilename());
        user.applyUploadImage(uploadProfileInfo.getUploadName(), uploadProfileInfo.getStorageName());

        try {
            profile.transferTo(new File(filePath + user.getStorageName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private UploadProfile getUploadProfileInfo(String uploadName) {
        String storageName = UUID.randomUUID().toString().replace("-", "").substring(0, 10) + getFileExtension(uploadName);
        return new UploadProfile(uploadName, storageName);
    }

    private String getFileExtension(String uploadName) {
        return uploadName.substring(uploadName.lastIndexOf("."));
    }
}
