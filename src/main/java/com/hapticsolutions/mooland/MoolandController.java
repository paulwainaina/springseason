package com.hapticsolutions.mooland;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class MoolandController {
    private final MoolandService cowService;
    public MoolandController( @Autowired  MoolandService cowService){
        this.cowService=cowService;
    }
    @GetMapping("/")
    public String hello(){
        return "Hello world";
    }
    @GetMapping("/cows")
    public Iterable<Cow> cows(){
        return cowService.get();
    }
    @GetMapping("/cows/{id}")
    public Cow getcow(@PathVariable Integer id){
        Cow cow= cowService.get(id);
        if (cow==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return cow;
    }
    @DeleteMapping("/cows/{id}")
    public void deleteCow(@PathVariable Integer id){
        cowService.remove(id);
    }
    @PostMapping("/cows")
    public Cow addCow(@RequestBody @Valid Cow cow){
        return cowService.add(cow);
    }

    @PostMapping("/cows/upload/{id}")
    public void uploadPassport(@RequestPart("data") MultipartFile file,@PathVariable Integer id){
        Cow cow= cowService.get(id);
        if (cow==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        cow.setPassport(file.getOriginalFilename());
        try {
            cow.setData(file.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cows/download/{id}")
    public ResponseEntity<byte[]> downloadPassport(@PathVariable String id){
        Cow cow= cowService.get(Integer.getInteger(id));
        if (cow==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.valueOf("image/x-icon"));
        ContentDisposition build=ContentDisposition.builder("inline").filename(cow.getPassport()).build();//"attachment
        headers.setContentDisposition(build);
        return new ResponseEntity<>(cow.getData(),headers,HttpStatus.OK);
    }
}
