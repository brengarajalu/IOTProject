package com.example.brengarajulu.gimbaltrackerv3;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by brengarajulu on 5/11/2015.
 */

    public class User {
        @JsonProperty("userId")
        private String userId;
        @JsonProperty("userName")
        private String userName;
        @JsonProperty("created_at")
        private String created_at;
        @JsonProperty("interests")
        private List<String> interests;
        @JsonProperty("disinterests")
        private List<String> disinterests;

        public String getUserId() {
            return userId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
        }

        public List<String> getDisinterests() {
            return disinterests;
        }

        public void setDisinterests(List<String> disinterests) {
            this.disinterests = disinterests;
        }

}
