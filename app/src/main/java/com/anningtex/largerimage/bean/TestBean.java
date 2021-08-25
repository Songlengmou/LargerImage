package com.anningtex.largerimage.bean;

import java.util.List;

/**
 * @Author Song
 * @Date：2021-08-24
 */
public class TestBean {
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private int container_id;
        private int master;
        private String master_text;
        private String path;
        private String path_medium;
        private String path_mini;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getContainer_id() {
            return container_id;
        }

        public void setContainer_id(int container_id) {
            this.container_id = container_id;
        }

        public int getMaster() {
            return master;
        }

        public void setMaster(int master) {
            this.master = master;
        }

        public String getMaster_text() {
            return master_text;
        }

        public void setMaster_text(String master_text) {
            this.master_text = master_text;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPath_medium() {
            return path_medium;
        }

        public void setPath_medium(String path_medium) {
            this.path_medium = path_medium;
        }

        public String getPath_mini() {
            return path_mini;
        }

        public void setPath_mini(String path_mini) {
            this.path_mini = path_mini;
        }
    }
}
