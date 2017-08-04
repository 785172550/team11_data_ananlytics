package com.example.model;

/**
 * Created by Kenneth on 2017/8/4.
 */

public class Result<T> {

    private int status;

    private String msg = null;

    private T content;

    public Result(int status, String msg, T content) {
        this.status = status;
        this.msg = msg;
        this.content = content;
    }

    public static class Builder<T> {

        private int status;

        private String msg;

        private T content;

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setContent(T content) {
            this.content = content;
            return this;
        }

        public Result build() {
            return new Result<T>(status, msg, content);
        }
    }
}
