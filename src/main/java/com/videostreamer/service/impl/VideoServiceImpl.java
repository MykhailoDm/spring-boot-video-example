package com.videostreamer.service.impl;

import com.videostreamer.exception.BadRequestException;
import com.videostreamer.exception.NotFoundException;
import com.videostreamer.exception.VideoAlreadyExistsException;
import com.videostreamer.model.Video;
import com.videostreamer.repository.VideoRepository;
import com.videostreamer.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @Override
    public Video getVideo(String name) {
        return videoRepository.findByName(name).orElseThrow(() -> new NotFoundException("Video was not found with name: " + name));
    }

    @Override
    public void saveVideo(MultipartFile file, String name) {
        if (videoRepository.existsByName(name)) {
            throw new VideoAlreadyExistsException(String.format("Video with name %s already exists", name));
        }
        try {
            videoRepository.save(new Video(name, file.getBytes()));
        } catch (IOException e) {
            throw new BadRequestException("Invalid video file sent");
        }
    }

    @Override
    public Set<String> getAllVideoNames() {
        return videoRepository.findAllNames();
    }
}
