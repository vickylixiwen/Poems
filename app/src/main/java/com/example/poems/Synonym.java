package com.example.poems;

public class Synonym {
    private String word;
    private String synonym;

    public static Synonym[] synonyms = {
            new Synonym("宽  ", "近义词：\t广"),
            new Synonym("连忙", "近义词：\t赶忙，赶快，马上"),
            new Synonym("长  ", "反义词：\t短"),
            new Synonym("快活", "反义词：\t难过"),
            new Synonym("平常", "近义词：\t平时"),
            new Synonym("办法", "近义词：\t方法"),
            new Synonym("温和", "近义词：\t温顺"),
            new Synonym("低  ", "反义词：\t高"),
            new Synonym("升  ", "反义词：\t降"),
            new Synonym("温和", "反义词：\t暴躁"),
            new Synonym("许多", "反义词：\t少许"),
            new Synonym("如果", "近义词：\t假如"),
            new Synonym("告别", "近义词：\t告辞"),
            new Synonym("旅行", "近义词：\t旅游"),
            new Synonym("轻  ", "反义词：\t重"),
            new Synonym("好  ", "反义词：\t坏"),
            new Synonym("细心", "反义词：\t粗心"),
            new Synonym("仔细", "反义词：\t马虎"),
            new Synonym("像  ", "近义词：\t似"),
            new Synonym("披  ", "近义词：\t挂"),
            new Synonym("寒  ", "近义词：\t冷"),
            new Synonym("高  ", "反义词：\t矮"),
            new Synonym("壮  ", "反义词：\t弱"),
            new Synonym("喜  ", "反义词：\t厌"),
            new Synonym("寒  ", "反义词：\t热"),
            new Synonym("香  ", "反义词：\t臭"),
            new Synonym("暖  ", "反义词：\t冷"),
            new Synonym("飞翔", "近义词：\t翱翔"),
            new Synonym("伙伴", "近义词：\t同伴，朋友"),
            new Synonym("朋友", "反义词：\t敌人"),
            new Synonym("保护", "反义词：\t破坏"),
            new Synonym("辛苦", "近义词：\t忙碌"),
            new Synonym("喜洋洋 ", "近义词：\t乐滋滋"),
            new Synonym("嫩  ", "反义词：\t老"),
            new Synonym("肥  ", "反义词：\t瘦"),
            new Synonym("忙  ", "反义词：\t闲"),
            new Synonym("早  ", "反义词：\t晚"),
            new Synonym("晴  ", "反义词：\t阴"),
            new Synonym("新  ", "反义词：\t旧"),
            new Synonym("轻  ", "反义词：\t重"),
            new Synonym("辛苦", "反义词：\t轻松"),
            new Synonym("下沉", "近义词：\t下降"),
            new Synonym("果然", "近义词：\t果真"),
            new Synonym("高兴", "近义词：\t喜悦，愉快"),
            new Synonym("下沉", "反义词：\t上浮，上升"),
            new Synonym("高兴", "反义词：\t难过，忧愁，忧伤")

    };

    public Synonym(String word, String synonym) {
        this.word = word;
        this.synonym = synonym;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public String toString() {
        return word + "\t\t\t\t" + synonym;
    }
}
