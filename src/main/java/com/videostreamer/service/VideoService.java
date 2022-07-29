package com.videostreamer.service;

import com.videostreamer.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface VideoService {

    Video getVideo(String name);

    void saveVideo(MultipartFile file, String name);

    Set<String> getAllVideoNames();
}
