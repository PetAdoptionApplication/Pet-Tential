package com.codeup.pettential.services;

import com.codeup.pettential.models.Program;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServices {

    private List<Program> unsignedPrograms;

    public void addToUnsignedProgs(Program program){
        unsignedPrograms.add(program);
    }

    public List<Program> getUnsignedPrograms(){
        return unsignedPrograms;
    }
}
