package com.hapticsolutions.mooland;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class MoolandService {

    private final MoolandRepository moolandRepository;
    public MoolandService(@Autowired MoolandRepository moolandRepository) {
        this.moolandRepository = moolandRepository;
    }


    public Iterable<Cow> get(){
        return moolandRepository.findAll();
    }

    public Cow get(Integer id) {
        return moolandRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
         moolandRepository.deleteById(id);
    }

    public Cow add( Cow cow) {
        return moolandRepository.save(cow);
    }
}
