package com.abc.resources;

import com.abc.domain.Module;
import com.abc.dto.AddModuleDto;
import com.abc.dto.ModuleDto;
import com.abc.repositories.ModuleRepository;
import com.abc.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by SunimalM on 11/30/2018.
 */
@RestController
@RequestMapping("/api/module")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;


    @GetMapping("/all")
    public ResponseEntity getAllStudent() {

        ResponseBuilder<List<ModuleDto>> allModuleResponse = new ResponseBuilder<>();

        try {
            List<Module> moduleList = moduleRepository.findAll();
            if (moduleList.size() > 0) {
                List<ModuleDto> moduleDtoList = new ArrayList<>();

                moduleDtoList.forEach(m -> {
                    ModuleDto moduleDto = new ModuleDto();
                    moduleDto.setModuleName(m.getModuleName());
                    moduleDto.setStudents(m.getStudents());
                    moduleDtoList.add(moduleDto);
                });

                allModuleResponse.setStatus(HttpStatus.OK.name());
                allModuleResponse.setStatusCode(HttpStatus.OK.value());
                allModuleResponse.setPayload(moduleDtoList);
                allModuleResponse.setMessage("all student retrieved Successfully");
                return new ResponseEntity<>(allModuleResponse, HttpStatus.OK);

            } else {
                allModuleResponse.setStatus(HttpStatus.NO_CONTENT.name());
                allModuleResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                allModuleResponse.setPayload(new ArrayList<>(0));
                allModuleResponse.setMessage("all module retrieved unsuccessful");
                return new ResponseEntity<>(allModuleResponse, HttpStatus.OK);
            }
        }catch (RuntimeException e)
        {
            allModuleResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            allModuleResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            allModuleResponse.setPayload(new ArrayList<>(0));
            allModuleResponse.setMessage("Internal Error");
            return new ResponseEntity<>(allModuleResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody AddModuleDto addModuleDto) {
        ResponseBuilder<AddModuleDto> addModuleResponse = new ResponseBuilder<>();

        Optional<Module> existingModule = moduleRepository.findByModuleId(addModuleDto.getModuleId());
        if(!existingModule.isPresent()) {
            Module module = new Module();
            module.setModuleId(addModuleDto.getModuleId());
            module.setModuleName(addModuleDto.getModuleName());
            module.setStudents(addModuleDto.getStudents()); // assumption we get student objects
            moduleRepository.save(module);

            addModuleResponse.setStatus(HttpStatus.OK.name());
            addModuleResponse.setStatusCode(HttpStatus.OK.value());
            addModuleResponse.setPayload(addModuleDto);
            addModuleResponse.setMessage("all student retrieved Successfully");
            return new ResponseEntity<>(addModuleResponse, HttpStatus.OK);
        }
        else{
            addModuleResponse.setStatus(HttpStatus.NO_CONTENT.name());
            addModuleResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
            addModuleResponse.setPayload(addModuleDto);
            addModuleResponse.setMessage("Module Id Already Exist");
            return new ResponseEntity<>(addModuleResponse, HttpStatus.NO_CONTENT);
        }

    }
}
