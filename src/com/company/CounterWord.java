package com.company;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

public class CounterWord {
    private final String url;
    private String text;
    private List<Map.Entry<String, Integer>> list;
    private String[] listOfWord;

    public CounterWord(String url) {
        this.url = url;
    }

    public  void parsString(){
        try {
            URLConnection connection = new URL(url).openConnection();
            text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteUnnecessaryCharacters();

    }

    public void deleteUnnecessaryCharacters(){
        text = text.replaceAll("- ", "");
        text = text.replaceAll("[^\\p{L}\\s[-]]+", "").toLowerCase(Locale.ROOT);


    }

    public void countingWords(){
        parsString();
        listOfWord = text.split("\\s+");
        Map<String, Integer> dictionaryFrequency = new HashMap<>();
        for (String word : listOfWord){
            boolean answer =false;
            for(String wordOfKey : dictionaryFrequency.keySet()){
                if(word.equals(wordOfKey)){
                    answer =true;
                }
            }
            if(answer){
                dictionaryFrequency.put(word, dictionaryFrequency.get(word) + 1);
            } else {
                dictionaryFrequency.put(word,1);
            }

        }

        list = dictionaryFrequency.entrySet().stream()
                .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toList());
    }

    public void numberOfAllWords(){
        System.out.println("Общее количество слов в тексте = " + listOfWord.length);

    }
    public void numberOfIndividualWords(){
        System.out.println(list);
    }

}
