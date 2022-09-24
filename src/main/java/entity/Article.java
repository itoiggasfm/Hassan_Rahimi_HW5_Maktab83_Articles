package entity;

import java.time.LocalDateTime;

import javafx.scene.text.Text;

public class Article {
    private int userID;
        private String title;
        private String brief;
        private String content;
        private boolean isPublished;
        private LocalDateTime createDate;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getBrief() {
            return brief;
        }
        public void setBrief(String brief) {
            this.brief = brief;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String  content) {
            this.content = content;
        }
        public boolean getIsPublished() {
            return isPublished;
        }
        public void setIsPublished(boolean isPublished) {
            this.isPublished = isPublished;
        }
        public LocalDateTime getCreateDate() {
            return createDate;
        }
        public void setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
        }

        
}
