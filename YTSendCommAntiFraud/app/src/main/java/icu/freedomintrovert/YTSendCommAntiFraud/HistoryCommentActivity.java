package icu.freedomintrovert.YTSendCommAntiFraud;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.view.ProgressBarDialog;
import icu.freedomintrovert.YTSendCommAntiFraud.view.VoidDialogInterfaceOnClickListener;
import io.reactivex.rxjava3.core.Observable;

public abstract class HistoryCommentActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EXPORT = 1;
    private static final int REQUEST_CODE_IMPORT = 2;
    private HistoryCommentListAdapter adapter;
    public Context context;
    private LoadingHistoryCommentFragment loadingHistoryCommentFragment;
    private HistoryCommentFragment historyCommentFragment;
    private Config config;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_comments);
        context = this;
        ActivityRec.setActivity(this);
        config = new Config(context);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        loadingHistoryCommentFragment = new LoadingHistoryCommentFragment();
        adapter = getAdapter();
        historyCommentFragment = new HistoryCommentFragment(adapter);
        reloadData();
    }

    private void reloadData() {
        replaceFragment(loadingHistoryCommentFragment);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<? extends HistoryComment> historyCommentList = getHistoryCommentList();
                List<HistoryComment> sortedCommentList = new ArrayList<>();
                boolean enableNormal = config.getFilterRulerEnableNormal();
                boolean enableShadowBan = config.getFilterRulerEnableShadowBan();
                boolean enableDelete = config.getFilterRulerEnableDelete();
                for (HistoryComment historyComment : historyCommentList) {
                    if (enableNormal && historyComment.state.equals(HistoryComment.STATE_NORMAL)){
                        sortedCommentList.add(historyComment);
                    } else if (enableShadowBan && historyComment.state.equals(HistoryComment.STATE_SHADOW_BAN)) {
                        sortedCommentList.add(historyComment);
                    } else if (enableDelete && historyComment.state.equals(HistoryComment.STATE_DELETED)){
                        sortedCommentList.add(historyComment);
                    }
                }
                if (config.getSortRuler() == Config.SORT_RULER_DATE_DESC){
                    Collections.reverse(sortedCommentList);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.reloadData(sortedCommentList);
                        replaceFragment(historyCommentFragment);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_export_and_input, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.item_sort) {
            //这要求sortRuler数字与选项位置一致
            AtomicInteger sortRuler = new AtomicInteger(config.getSortRuler());
            new AlertDialog.Builder(context).setTitle(R.string.sorting_method).setIcon(R.drawable.baseline_sort_24).setSingleChoiceItems(new String[]{getString(R.string.sent_date__n_o), getString(R.string.sent_date__o_n)}, sortRuler.get(), (dialog, which) -> {
                sortRuler.set(which);
            }).setPositiveButton(android.R.string.ok, (dialog, which) -> {
                if (sortRuler.get() == 0) {
                    onSortTypeSet(Config.SORT_RULER_DATE_DESC);
                } else if (sortRuler.get() == 1) {
                    onSortTypeSet(Config.SORT_RULER_DATE_ASC);
                }
            }).setNegativeButton(android.R.string.cancel, new VoidDialogInterfaceOnClickListener()).show();
        } else if (itemId == R.id.item_filter) {
            AtomicBoolean enableNormal = new AtomicBoolean(config.getFilterRulerEnableNormal());
            AtomicBoolean enableShadowBan = new AtomicBoolean(config.getFilterRulerEnableShadowBan());
            AtomicBoolean enableDeleted = new AtomicBoolean(config.getFilterRulerEnableDelete());
            new AlertDialog.Builder(context).setTitle(R.string.filter).setIcon(R.drawable.baseline_filter_alt_24).setMultiChoiceItems(new String[]{getString(R.string.Normal), "ShadowBan", getString(R.string.Has_been_deleted)}, new boolean[]{enableNormal.get(), enableShadowBan.get(), enableDeleted.get()}, (dialog, which, isChecked) -> {
                if (which == 0) {
                    enableNormal.set(isChecked);
                } else if (which == 1) {
                    enableShadowBan.set(isChecked);
                } else if (which == 2) {
                    enableDeleted.set(isChecked);
                }
            }).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onFilterRulerSet(enableNormal.get(), enableShadowBan.get(), enableDeleted.get());
                }
            }).setNegativeButton(android.R.string.cancel, new VoidDialogInterfaceOnClickListener()).show();
        } else if (itemId == R.id.item_export) {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/comma-separated-values");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINA);
            intent.putExtra(Intent.EXTRA_TITLE, getExportFileName() + "_" + sdf.format(new Date()) + ".csv");
            startActivityForResult(intent, REQUEST_CODE_EXPORT);
        } else if (itemId == R.id.item_import) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/comma-separated-values");
            startActivityForResult(intent, REQUEST_CODE_IMPORT);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            switch (requestCode) {
                case REQUEST_CODE_EXPORT:
                    try {
                        OutputStream outputStream = getContentResolver().openOutputStream(data.getData());
                        onExport(outputStream);
                    } catch (IOException e) {
                        Toast.makeText(context, getString(R.string.export_failed)+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case REQUEST_CODE_IMPORT:
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());
                        onImport(inputStream,adapter);
                    } catch (IOException e) {
                        Toast.makeText(context, getString(R.string.export_failed)+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }

    protected abstract void onImport(InputStream inputStream, HistoryCommentListAdapter adapter);

    protected abstract void onExport(OutputStream outputStream);

    protected abstract String getExportFileName();

    protected void onFilterRulerSet(boolean enableNormal, boolean enableShadowBan, boolean enableDeleted) {
        config.setFilterRulerEnableNormal(enableNormal);
        config.setFilterRulerEnableShadowBan(enableShadowBan);
        config.setFilterRulerEnableDeleted(enableDeleted);
        reloadData();
    }

    protected void onSortTypeSet(int sortRuler) {
        config.setSortRuler(sortRuler);
        reloadData();
    }

    protected abstract List<? extends HistoryComment> getHistoryCommentList();


    protected abstract HistoryCommentListAdapter getAdapter();


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
