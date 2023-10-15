package icu.freedomintrovert.YTSendCommAntiFraud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.MenuItem;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import de.robv.android.xposed.XSharedPreferences;
import icu.freedomintrovert.YTSendCommAntiFraud.comment.ToBeCheckedComment;
import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;

public class ToBeCheckedCommentListActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_FOR_DIR = 1;
    Context context;
    RecyclerView recyclerView;
    StatisticsDB statisticsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_to_be_checked);
        context = this;
        ActivityRec.setActivity(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ToBeCheckedCommentsAdapter(getStatisticsDB(), this));

    }

    public StatisticsDB getStatisticsDB() {
        if (statisticsDB == null) {
            statisticsDB = new StatisticsDB(context);
        }
        return statisticsDB;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //返回授权状态
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri;
        if (data == null) {
            return;
        }

        if (requestCode == REQUEST_CODE_FOR_DIR && (uri = data.getData()) != null) {
            getContentResolver().takePersistableUriPermission(uri, data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));//关键是这里，这个就是保存这个目录的访问权限
            System.out.println(uri);
            DocumentFile documentFile = DocumentFile.fromTreeUri(context,uri);
            for (DocumentFile file : documentFile.listFiles()) {
                System.out.println(file.getName());
            }
        }

    }



}