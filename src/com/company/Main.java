package com.company;

public class Main {

    public static void main(String[] args) {
        String url = "http://madbrains.github.io/java_course_test";
        CounterWord work = new CounterWord(url);
        work.countingWords();
        work.numberOfAllWords();
        work.numberOfIndividualWords();
    }
}
