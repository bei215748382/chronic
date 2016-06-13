package com.mlnx.chronic.vo;

/**
 * Created by shasha on 2016/6/7.
 */
public class BookCount {

    private BookStatus bookStatus;//状态
    private int hasCount;//已经预约几个号
    private int leftCount;//剩下几个号

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getHasCount() {
        return hasCount;
    }

    public void setHasCount(int hasCount) {
        this.hasCount = hasCount;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }
}
