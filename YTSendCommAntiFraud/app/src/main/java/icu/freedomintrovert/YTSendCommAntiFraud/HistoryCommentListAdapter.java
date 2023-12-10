package icu.freedomintrovert.YTSendCommAntiFraud;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import icu.freedomintrovert.YTSendCommAntiFraud.comment.HistoryComment;
import icu.freedomintrovert.YTSendCommAntiFraud.view.VoidDialogInterfaceOnClickListener;

public abstract class HistoryCommentListAdapter extends RecyclerView.Adapter<HistoryCommentListAdapter.ViewHolder> {


    Context context;
    List<? extends HistoryComment> commentList;

    public HistoryCommentListAdapter(Context context) {
        this.context = context;
        commentList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from(context).inflate(R.layout.item_history_comment,parent,false)
        return new ViewHolder(View.inflate(context,R.layout.item_history_comment,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryComment historyComment = commentList.get(position);
        holder.tvx_comment.setText(historyComment.commentText);
        switch (historyComment.state) {
            case HistoryComment.STATE_NORMAL:
                holder.txv_state.setText(R.string.normal);
                holder.img_state.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.baseline_done_outline_24));
                break;
            case HistoryComment.STATE_SHADOW_BAN:
                holder.txv_state.setText(R.string.shadow_ban);
                holder.img_state.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.hide));
                break;
            case HistoryComment.STATE_DELETED:
                holder.txv_state.setText(R.string.has_been_deleted);
                holder.img_state.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.deleted));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + historyComment.state);
        }
        holder.txv_date.setText(historyComment.getFormatDateFor_yMd());
        holder.txv_source_id.setText(historyComment.getCommentSectionSourceId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = View.inflate(context,R.layout.dialog_history_comment_info,null);
                TextView txv_comment_content = dialogView.findViewById(R.id.txv_comment_content);
                TextView txv_comment_id = dialogView.findViewById(R.id.txv_comment_id);
                TextView txv_state = dialogView.findViewById(R.id.txv_state);
                TextView txv_source_id_desc = dialogView.findViewById(R.id.txv_source_id_desc);
                TextView txv_source_id = dialogView.findViewById(R.id.txv_source_id);
                TextView txv_send_date = dialogView.findViewById(R.id.txv_send_date);

                txv_comment_content.setText(historyComment.commentText);
                switch (historyComment.state) {
                    case HistoryComment.STATE_NORMAL:
                        txv_state.setText(R.string.normal);
                        break;
                    case HistoryComment.STATE_SHADOW_BAN:
                        txv_state.setText(R.string.shadow_ban__desc);
                        break;
                    case HistoryComment.STATE_DELETED:
                        txv_state.setText(R.string.has_been_deleted);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + historyComment.state);
                }
                txv_source_id_desc.setText(getSourceIdDesc());
                txv_source_id_desc.setOnClickListener(v1 -> openDetailPage(historyComment));
                txv_comment_id.setText(historyComment.commentId);
                txv_source_id.setText(historyComment.getCommentSectionSourceId());
                txv_send_date.setText(historyComment.getFormatDateFor_yMdHms());
                new AlertDialog.Builder(context)
                        .setTitle(R.string.comment_info)
                        .setView(dialogView)
                        .setPositiveButton(R.string.close,new VoidDialogInterfaceOnClickListener())
                        .setNegativeButton(R.string.copy, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData mClipData = ClipData.newPlainText("Label", historyComment.commentText);
                                cm.setPrimaryClip(mClipData);
                                Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton(R.string.delete, (dialog, which) -> new AlertDialog.Builder(context)
                                .setTitle(R.string.confirm_deletion)
                                .setPositiveButton(android.R.string.ok, (dialog1, which1) -> {
                                    if (onHistoryCommentDelete(historyComment, holder)) {
                                        Toast.makeText(context, R.string.deleted_successfully, Toast.LENGTH_SHORT).show();
                                        commentList.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());
                                    } else {
                                        Toast.makeText(context, R.string.delete_failed, Toast.LENGTH_SHORT).show();
                                    }
                                }).setNegativeButton(android.R.string.cancel, new VoidDialogInterfaceOnClickListener())
                                .show()).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<? extends HistoryComment> historyCommentList){
       this.commentList = historyCommentList;
       notifyDataSetChanged();
    }

    protected abstract boolean onHistoryCommentDelete(HistoryComment historyComment,ViewHolder viewHolder);

    protected String getSourceIdDesc() {
        return "评论区源ID";
    }

    protected abstract void openDetailPage(HistoryComment historyComment);

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvx_comment,txv_state,txv_source_id,txv_date;
        ImageView img_state;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvx_comment = itemView.findViewById(R.id.txv_comment_content);
            txv_state = itemView.findViewById(R.id.txv_state);
            txv_source_id = itemView.findViewById(R.id.txv_source_id);
            txv_date = itemView.findViewById(R.id.txv_date);
            img_state = itemView.findViewById(R.id.img_state);
        }
    }
}
