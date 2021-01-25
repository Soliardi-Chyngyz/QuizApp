package com.chyngyz.quizapp.ui.historyFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.chyngyz.quizapp.R;
import com.chyngyz.quizapp.databinding.HistoryFragmentBinding;
import com.chyngyz.quizapp.ui.adapter.HistoryAdapter;

public class HistoryFragment extends Fragment implements HistoryAdapter.OnMenuBarClick {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding binding;
    private HistoryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.history_fragment, container, false));
        assert binding != null;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);

        getRoom();
        clearAdapter();
    }

    private void clearAdapter() {
        mViewModel.getDeleteAdapter().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                adapter.clearAll();
        });
    }

    private void getRoom() {
        mViewModel.getList().observe(getViewLifecycleOwner(), quizResults -> {
            adapter = new HistoryAdapter(quizResults, this);
            binding.historyRecycler.setAdapter(adapter);
            adapter.addItem(quizResults);
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void menuClicked(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.inflate(R.menu.pop_up_menu);
        popupMenu
                .setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.menu_delete:
                            mViewModel.deleteItem(position);
                            adapter.deleteItem(position);
                            return true;
                        case R.id.menu_stay:
                            return true;
                        default:
                            return false;
                    }
                });
        popupMenu.show();
    } // меню каждого item
}