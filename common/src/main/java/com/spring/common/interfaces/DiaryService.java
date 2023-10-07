package com.spring.common.interfaces;

import com.spring.dao.models.Diary;
import com.spring.dao.models.Tutee;

import java.util.List;
import java.util.Optional;

public interface DiaryService extends CrudService<Diary,Long> {

        Diary findByTitle(String title);

        List<Diary> findByTitleLike(String title);

        void create(Diary diary);

        void update(Diary diary);


        Optional<Diary> findByTutee(Tutee id);
}