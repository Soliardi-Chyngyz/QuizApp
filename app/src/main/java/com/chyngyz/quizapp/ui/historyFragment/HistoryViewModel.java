package com.chyngyz.quizapp.ui.historyFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.chyngyz.quizapp.QuizApp;
import com.chyngyz.quizapp.ui.models.QuizResult;
import java.util.List;
import java.util.Objects;

public class HistoryViewModel extends ViewModel {

    private LiveData<List<QuizResult>> list = new MutableLiveData<>();
    private final MutableLiveData<Boolean> deleteAdapter = new MutableLiveData<>();

    public LiveData<Boolean> getDeleteAdapter(){
        return deleteAdapter;
    }

    public HistoryViewModel(){
        getData();
    }

    private void getData(){
        list = QuizApp.getInstance().getQuizRepository().getAll();
    }

    public void setDeleteAdapter (boolean value){
        deleteAdapter.setValue(value);
    }

    public LiveData<List<QuizResult>> getList() {
        return list;
    }

    public void deleteItem(int position) {
        QuizApp.getInstance().getQuizRepository().delete(Objects.requireNonNull(list.getValue()).get(position).getId());
    }
}