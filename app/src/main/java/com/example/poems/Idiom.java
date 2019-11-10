package com.example.poems;

public class Idiom {
    private String idiomName;
    private String idiomSource;
    private String idiomMeaning;

    public static final Idiom[] IDIOMS = {
        new Idiom("明修栈道，暗度陈仓",
                "项羽自封为西楚霸王后，就向各诸侯分封领地，其中把巴蜀、汉中三郡分封给刘邦，立为汉王。\n" +
                "刘邦在去领地途中令部下烧毁了栈道，他这是向项羽表白没有向东扩张的意图。刘邦待具备了一定的实力后，便抓住时机迅速挥师东进，其野心是要与项羽一争，韩信便出了“明修栈道，暗度陈仓”的计策。\n" +
                "陈仓是刘邦进入关中的必经之地，两地之间有险山峻岭阻隔，又有雍王章邯的重兵把守。\n" +
                "刘邦按韩信的计策派了最信任的大将——樊哙带领一万人去修五百里栈道，并以军令限一月内修好。当然，这样浩大的工程即使三年也不可能完成。\n" +
                "正是这一点，迷惑麻痹了陈仓的守将。陈仓的雍王章邯万万没想到刘邦的精锐部队摸着无人知晓的小道翻山越岭偷袭了陈仓。\n" +
                "刘邦通过“明修栈道，暗度陈仓”，顺利挺进到关中，站稳了脚跟，从此拉开了他开创汉王朝事业的大幕。",
                "这个成语，在军事上的含义是：从正面迷惑敌人，用来掩盖自己的攻击路线，而从侧翼进行突然袭击。这是声东击西、出奇制胜的谋略。引申开来，是指用明显的行动迷惑对方，使人不备的策略，也比喻暗中进行活动。有时也可将“明修栈道”省略掉，把“暗度陈仓”单独来使用。"),
            new Idiom("成也萧何，败也萧何", "“成也萧何，败也萧何”是一句成语，为民间对西汉建国功臣韩信一生的经典概括。“成也萧何”是指韩信成为大将军是萧何推荐的；“败也萧何”是指韩信被杀是萧何出的计谋。不论是成功还是败亡都是由于同一个人。", "“成也萧何，败也萧何”是一句成语，为民间对西汉建国功臣韩信一生的经典概括。")
    };

    private Idiom(String idiomName, String idiomSource, String idiomMeaning) {
        this.idiomName = idiomName;
        this.idiomSource = idiomSource;
        this.idiomMeaning = idiomMeaning;
    }

    public String getIdiomName() {
        return idiomName;
    }

    public void setIdiomName(String idiomName) {
        this.idiomName = idiomName;
    }

    public String getIdiomSource() {
        return idiomSource;
    }

    public void setIdiomSource(String idiomSource) {
        this.idiomSource = idiomSource;
    }

    public String getIdiomMeaning() {
        return idiomMeaning;
    }

    public void setIdiomMeaning(String idiomMeaning) {
        this.idiomMeaning = idiomMeaning;
    }

    @Override
    public String toString() {
        return idiomName;
    }
}
