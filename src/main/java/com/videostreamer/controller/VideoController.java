package com.videostreamer.controller;

import com.videostreamer.model.Video;
import com.videostreamer.service.VideoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public void saveVideo(@RequestParam("file") MultipartFile multipartFile, @RequestParam("name") String name) {
        videoService.saveVideo(multipartFile, name);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getVideo(@PathVariable("name") String name) {
        Video video = videoService.getVideo(name);
        return ResponseEntity.ok(new ByteArrayResource(video.getData()));
    }

    @GetMapping
    public Set<String> getVideos() {
        return videoService.getAllVideoNames();
    }
}
