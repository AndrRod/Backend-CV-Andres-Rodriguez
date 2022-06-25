package com.cvitae.projectcv.controller;

import com.cvitae.projectcv.dto.SkillsDtoPart;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("/skill")
public class SkillsController {
    @Autowired
    private SkillsService skillsService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillsDtoPart createSkill(@RequestBody SkillsDtoPart skillsDtoPart){
    return skillsService.createSkill(skillsDtoPart);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillsDtoPart updateById(@PathVariable Long id, @RequestBody SkillsDtoPart skillsDtoPart){
        return skillsService.updateSkillById(id, skillsDtoPart);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<SkillsDtoPart> skillsDtoPartList(){
        return skillsService.listSkillsDto();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillsDtoPart findById(@PathVariable Long id){
        return skillsService.findById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageGeneral deleteSkillById(@PathVariable Long id, HttpServletRequest request){
        return skillsService.deleteSkillById(id, request);
    }
}
