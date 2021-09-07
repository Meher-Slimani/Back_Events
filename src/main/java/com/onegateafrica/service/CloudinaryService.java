package com.onegateafrica.service;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.*;

import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {


    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> cloudinaryCredentials = new HashMap<>();
        cloudinaryCredentials.put("cloud_name", "eventsapplication");
        cloudinaryCredentials.put("api_key", "859689276489346");
        cloudinaryCredentials.put("api_secret", "4PpT4L--peEeDMJUVBUk3xlOnPI");
        cloudinary = new Cloudinary(cloudinaryCredentials);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        Files.delete(Paths.get(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        return result;
    }

    public File convert(MultipartFile multipartFile)  {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fo = new FileOutputStream(file); ){
            fo.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public Map delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }



}
