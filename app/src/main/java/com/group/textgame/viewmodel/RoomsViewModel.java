package com.group.textgame.viewmodel;

import android.app.Application;
import com.group.textgame.model.Rooms;
import com.group.textgame.repo.RoomsDao;
import java.util.List;

public class RoomsViewModel {
    private StudyRepository studyRepo;

    public SubjectListViewModel(Application application) {
        studyRepo = StudyRepository.getInstance(application.getApplicationContext());
    }

    public List<Subject> getSubjects() {
        return studyRepo.getSubjects();
    }

    public void addSubject(Subject subject) {
        studyRepo.addSubject(subject);
    }
}


