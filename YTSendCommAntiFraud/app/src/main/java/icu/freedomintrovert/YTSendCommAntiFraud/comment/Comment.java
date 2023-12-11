package icu.freedomintrovert.YTSendCommAntiFraud.comment;


public class Comment {
    public String commentText;
    public String commentId;

    public Comment() {
    }


    public Comment(String commentText, String commentId) {
        this.commentText = commentText;
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentText='" + commentText + '\'' +
                ", commentId='" + commentId + '\'' +
                '}';
    }
}
