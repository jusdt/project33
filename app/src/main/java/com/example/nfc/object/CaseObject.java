package com.example.nfc.object;

public class CaseObject {

    private String title;
    private int result;

    public CaseObject(String title, int result) {
        this.title = title;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String toString() {
        return "title: " + title + ", result: " + result + "\n";
    }
}
