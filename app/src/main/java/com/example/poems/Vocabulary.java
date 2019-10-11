package com.example.poems;

public class Vocabulary {
    private String word;
    private String type;
    private String meaning;
    private String example;

    public static final Vocabulary[] vocabularies = {
            new Vocabulary("very", "adjective.", "很，非常", "Thank you very much!"),
            new Vocabulary("afternoon", "noun.", "下午", "Good afternoon!"),
            new Vocabulary("refrigerator", "noun.", "冰箱", "There is one refrigerator in the living room!"),
            new Vocabulary("closet", "noun.", "衣橱", "How many closets are there in the bedroom?")
    };

    private Vocabulary(String word, String type, String meaning, String example) {
        this.word = word;
        this.type = type;
        this.meaning = meaning;
        this.example = example;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return word + '\t' + '\t' + '\t' + '\t' + type + meaning + '\n' + "Ex: " + example;
    }
}
