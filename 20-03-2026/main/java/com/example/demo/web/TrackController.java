package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Track;
import com.example.demo.repo.TrackRepository;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackRepository repo;

    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        repo.save(track);
        return new ResponseEntity<>("Track added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> list = repo.findAll();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Track>> getTracksByTitle(@RequestParam String title) {
        List<Track> list = repo.findByTitle(title);

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Long id) {

        Optional<Track> track = repo.findById(id);

        if (track.isPresent()) {
            return new ResponseEntity<>(track.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }
}