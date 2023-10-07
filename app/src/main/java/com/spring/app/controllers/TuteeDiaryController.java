package com.spring.app.controllers;

import com.spring.common.interfaces.DiaryService;
import com.spring.common.interfaces.TuteeService;
import com.spring.dao.models.Diary;
import com.spring.dao.models.MyUserDetails;
import com.spring.dao.models.Tutee;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Controller class for handling requests sent from the Diary page for tutees.
 */
@Controller
public class TuteeDiaryController {
    private final DiaryService diaryService;
    private final TuteeService tuteeService;


    /**
     * Creates a new instance of {@link TuteeDiaryController}
     * @param diaryService service for handling diaries
     * @param tuteeService repository for handling tutees
     */
    public TuteeDiaryController(DiaryService diaryService, TuteeService tuteeService) {
        this.diaryService = diaryService;
        this.tuteeService = tuteeService;
    }

    /**
     * Handles the requests to the /tutee/diary-tutee URI.
     * Retrieves the authenticated user's information, and finds the Tutee object associated with the user's email.
     * Retrieves the Diary object associated with the Tutee object from the DiaryService and adds it to the model.
     * Also adds the username of the authenticated user and a boolean value indicating whether the diary exist or not to the model.
     * Returns the name of the view "tutee/diary-tutee"
     * @param model the model object
     * @param request the HttpServletRequest object
     * @param authentication the Authentication object
     * @return the name of the view "tutee/diary-tutee"
     */
    @GetMapping(value = "/tutee/diary-tutee")
    public String home(Model model, HttpServletRequest request, Authentication authentication) {
        MyUserDetails auth = (MyUserDetails) authentication.getPrincipal();
        Tutee tutee = tuteeService.findByEmail(auth.getUsername()).get();

        Optional<Diary> diaryOpt = diaryService.findByTutee(tutee);
        if (diaryOpt.isPresent()) {
            model.addAttribute("diary", diaryOpt.get());
            model.addAttribute("username", auth.getUsername());
            model.addAttribute("isDiaryExist", true);
        } else {
            model.addAttribute("isDiaryExist", false);
        }
        return "tutee/diary-tutee";
    }

    /**
     * Handles the GET requests to the /tutee/diary-tutee/create URI.
     * Adds a boolean attribute "isCreatingDiary" with value true to the model, which is used to indicate the creation of diary form in the view.
     * Returns the name of the view "tutee/diary-tutee".
     * @param model the model object
     * @param authentication the Authentication object
     * @return the name of the view "tutee/diary-tutee"
     */
    @GetMapping("/tutee/diary-tutee/create")
    public String createDiary(Model model, Authentication authentication) {
        model.addAttribute("isCreatingDiary", true);
        return "tutee/diary-tutee";
    }

    /**
     * Handles the POST requests to the /tutee/diary-tutee/create URI.
     * Retrieves the authenticated user's information, and finds the Tutee object associated with the user's email.
     * Retrieves the Diary object associated with the Tutee object from the DiaryService, if the Diary object exist, it appends the text with the new entry,
     * otherwise it creates a new Diary object with the given title and the text as the current date and the given description.
     * The new or updated Diary object is then saved using the DiaryService.
     * Returns a redirect to the /tutee/diary-tutee URI.
     * @param title the title of the diary
     * @param description the text of the diary entry
     * @param authentication the Authentication object
     * @return a redirect to the /tutee/diary-tutee URI
     */
    @PostMapping("/tutee/diary-tutee/create")
    public String saveDiary(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            Authentication authentication) {
        MyUserDetails auth = (MyUserDetails) authentication.getPrincipal();
        Tutee tutee = tuteeService.findByEmail(auth.getUsername()).get();

        Optional<Diary> diaryOpt = diaryService.findByTutee(tutee);
        Diary diary;
        if (diaryOpt.isPresent()) {
            diary = diaryOpt.get();
            diary.setText(diary.getText() + "\n" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " : " + description);
        } else {
            diary = new Diary();
            diary.setTitle(title);
            diary.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " : " + description);
            diary.setTutee(tutee);
        }
        diaryService.create(diary);
        return "redirect:/tutee/diary-tutee";
    }


    /**
     * Handles the POST requests to the /tutee/diary-tutee/edit URI.
     * Retrieves the authenticated user's information, and finds the Tutee object associated with the user's email.
     * Retrieves the Diary object associated with the Tutee object from the DiaryService, if the Diary object exist,
     * it appends the text with the new entry, with the current date and the given text.
     * The new or updated Diary object is then saved using the DiaryService.
     * Returns a redirect to the /tutee/diary-tutee URI.
     * @param text the new text of the diary entry
     * @param authentication the Authentication object
     * @return a redirect to the /tutee/diary-tutee URI
     */
    @PostMapping("/tutee/diary-tutee/edit")
    public String editDiary(@RequestParam("text") String text,
                            Authentication authentication) {
        MyUserDetails auth = (MyUserDetails) authentication.getPrincipal();
        Tutee tutee = tuteeService.findByEmail(auth.getUsername()).get();
        Optional<Diary> diaryOpt = diaryService.findByTutee(tutee);
        if(diaryOpt.isPresent()) {
            Diary diary = diaryOpt.get();
            diary.setText(diary.getText() + "\n" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " : " + text);
            diaryService.update(diary);
        }
        return "redirect:/tutee/diary-tutee";
    }
    @PostMapping("/tutee/diary-tutee/cancel")
    public String cancelDiary() {
        return "redirect:/tutee/diary-tutee";
    }
}
