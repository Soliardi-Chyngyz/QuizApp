package com.chyngyz.quizapp.ui.resultActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.ui.models.QuizResult;
import com.chyngyz.quizapp.ui.models.QuizResultNoRoom;

public class ResultViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isFinish = new MutableLiveData<>();

    public void setIsFinish(){
        isFinish.postValue(true);
    }

    public MutableLiveData<Boolean> getIsFinish() {
        return isFinish;
    }

    public void setQuizResult(QuizResultNoRoom quizResultNoRoom) {
        if (quizResultNoRoom != null) {
            QuizApp.getInstance().getQuizRepository().saveQuizResult(new QuizResult(
                    quizResultNoRoom.getTitle(),
                    quizResultNoRoom.getDifficulty(),
                    quizResultNoRoom.getAnswerCorrect(),
                    quizResultNoRoom.getDate(),
                    null
            ));
        }
    }
}
