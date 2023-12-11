package icu.freedomintrovert.YTSendCommAntiFraud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.ToBeCheckedComment;
import icu.freedomintrovert.YTSendCommAntiFraud.db.StatisticsDB;
import icu.freedomintrovert.YTSendCommAntiFraud.view.VoidDialogInterfaceOnClickListener;

public class ToBeCheckedCommentsAdapter extends RecyclerView.Adapter<ToBeCheckedCommentsAdapter.ViewHolder> {
    StatisticsDB statisticsDB;
    Activity context;
    List<ToBeCheckedComment> commentList;
    DialogCommentChecker dialogCommentChecker;
    Config config;

    public ToBeCheckedCommentsAdapter(StatisticsDB statisticsDB, Activity context) {
        this.statisticsDB = statisticsDB;
        this.context = context;
        commentList = statisticsDB.getAllToBeCheckedComments();
        Collections.reverse(commentList);
        config = new Config(context);
        dialogCommentChecker = new DialogCommentChecker(context, statisticsDB, config);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_to_be_checked_comment, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToBeCheckedComment comment = commentList.get(position);
        View itemView = holder.itemView;
        TextView txv_comment_content = itemView.findViewById(R.id.txv_comment_content);
        TextView txv_source_id = itemView.findViewById(R.id.txv_source_id);
        TextView txv_date = itemView.findViewById(R.id.txv_date);
        ImageView img_type = itemView.findViewById(R.id.img_type);

        txv_comment_content.setText(comment.commentText);
        txv_source_id.setText(comment.sourceId);
        txv_date.setText(comment.getFormatDateFor_yMdHms());

        if (comment.commentSectionType == ToBeCheckedComment.COMMENT_SECTION_TYPE_VIDEO) {
            img_type.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_smart_display_24));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("检查此评论")
                        .setMessage(comment.commentText)
                        .setPositiveButton(android.R.string.ok, null)
                        .setNegativeButton(android.R.string.cancel, new VoidDialogInterfaceOnClickListener())
                        .show();
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toCheckComment(comment, holder,dialog);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    private void toCheckComment(ToBeCheckedComment comment, ViewHolder viewHolder,DialogInterface dialog) {
        DocumentFile documentFile = DocumentFile.fromTreeUri(context, Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.google.android.youtube"));
        if (documentFile == null || !documentFile.canRead()) {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.permission_required)
                    .setMessage(R.string.request_folder_permission)
                    .setNegativeButton(android.R.string.cancel, (dialog1, which) -> dialog.dismiss())
                    .setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) (dialog12, which) -> {
                        startForRoot(context, ToBeCheckedCommentListActivity.REQUEST_CODE_FOR_DIR);
                    })
                    .show();
        } else {
            DocumentFile headersFile = null;
            for (DocumentFile file : documentFile.listFiles()) {
                if (file.getName().equals("headers.txt")) {
                    headersFile = file;
                }
            }
            if (headersFile != null) {
                try {
                    InputStream inputStream = context.getContentResolver().openInputStream(headersFile.getUri());
                    String headersKV;
                    if (inputStream != null) {
                        byte[] buffer = new byte[(int) headersFile.length()];
                        inputStream.read(buffer);
                        inputStream.close();
                        headersKV = new String(buffer);
                        Bundle bundle = new Bundle();
                        String[] split = headersKV.split("\n");
                        boolean hasAuthorization = false;
                        for (String s : split) {
                            String[] split1 = s.split("=");
                            if (split1[0].equals("Authorization")){
                                hasAuthorization = true;
                            }
                            bundle.putString(split1[0], split1[1]);
                        }
                        if (hasAuthorization) {
                            dialogCommentChecker.check(comment, comment.sourceId, ToBeCheckedComment.COMMENT_SECTION_TYPE_VIDEO, comment.context1, bundle, false, new DialogCommentChecker.OnExitListener() {
                                @Override
                                public void onExit(int exitCode) {
                                    if (exitCode == EXIT_CODE_COMMENT_NORMAL || exitCode == EXIT_CODE_COMMENT_SHADOW_BAN || exitCode == EXIT_CODE_COMMENT_DELETED){
                                        dialog.dismiss();
                                        commentList.remove(viewHolder.getAdapterPosition());
                                        notifyItemRemoved(viewHolder.getAdapterPosition());
                                    } else if (exitCode == EXIT_CODE_HTTP_STATUS_401){
                                        new AlertDialog.Builder(context)
                                                .setTitle(R.string.failed_to_get_the_list_of_comments)
                                                .setMessage(R.string.received_401_status_code)
                                                .setNegativeButton(android.R.string.cancel,new VoidDialogInterfaceOnClickListener())
                                                .setPositiveButton(R.string.Launch_YouTube, (dialog13, which) -> {
                                                    Intent intent = new Intent();
                                                    intent.setComponent(new ComponentName("com.google.android.youtube","com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity"));
                                                    context.startActivity(intent);
                                                })
                                                .show();
                                    }
                                }
                            },comment.sendTime);

                        }
                    }
                } catch (IOException e) {
                    dialog.dismiss();
                    Toast.makeText(context, context.getString(R.string.error_occurred) + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                dialog.dismiss();
                Toast.makeText(context, R.string.not_found_headers_txt_youtube, Toast.LENGTH_LONG).show();
            }
        }
    }

    //获取指定目录的访问权限
    public static void startForRoot(Activity context, int REQUEST_CODE_FOR_DIR) {
        Uri uri1 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.google.android.youtube");
        DocumentFile documentFile = DocumentFile.fromTreeUri(context, uri1);
        Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
        intent1.putExtra(DocumentsContract.EXTRA_INITIAL_URI, documentFile.getUri());
        context.startActivityForResult(intent1, REQUEST_CODE_FOR_DIR);
    }
}
