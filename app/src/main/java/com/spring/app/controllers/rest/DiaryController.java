package com.spring.app.controllers.rest;

import com.spring.common.services.DiaryServiceImpl;
import com.spring.dao.models.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/testRoutes/diaries")
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;

    @GetMapping("/")
    private Set<Diary> getAllDiaries(){
        return diaryService.findAll();
    }

    @GetMapping("/{id}")
    private Diary getDiary(@PathVariable("id") Long id){
        return diaryService.findById(id);
    }

    @PostMapping("/")
    private Long addDiary(@RequestBody Diary diary){
        diaryService.save(diary);
        return diary.getId();
    }

    @PutMapping("/")
    private Diary updateDiary(@RequestBody Diary diary){
        diaryService.save(diary);
        return diary;
    }

    @DeleteMapping("/{id}")
    private void deleteDiary(@PathVariable("id") Long id){
        diaryService.deleteById(id);
    }

}
