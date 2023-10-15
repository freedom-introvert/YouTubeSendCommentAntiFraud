package icu.freedomintrovert.YTSendCommAntiFraud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryCommentFragment extends Fragment {
   private View rootView;
   private RecyclerView recyclerView;
   private HistoryCommentListAdapter adapter;

    public HistoryCommentFragment() {
    }

    public HistoryCommentFragment(HistoryCommentListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_history_comment, container, false);

        recyclerView = rootView.findViewById(R.id.rv_history_comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return rootView;
    }
}