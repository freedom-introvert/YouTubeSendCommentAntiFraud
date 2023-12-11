package icu.freedomintrovert.YTSendCommAntiFraud.rxObservables;

import java.util.List;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class LoadHistoryCommentsOnSubscribe implements ObservableOnSubscribe<List<HistoryComment>> {
    @Override
    public void subscribe(@NonNull ObservableEmitter<List<HistoryComment>> emitter) throws Throwable {

    }
}
