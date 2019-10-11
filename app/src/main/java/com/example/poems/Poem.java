package com.example.poems;

public class Poem {
    private String title;
    private String author;
    private String content;
    private String desc;

    public static final Poem[] poems1 = {
        new Poem("咏鹅", "骆宾王",
                "鹅，鹅，鹅，\n曲项向天歌，\n白毛浮绿水，\n红掌拨清波。", ""),
            new Poem("江南", "汉乐府",
                    "江南可采莲，\n莲叶何田田，\n鱼戏莲叶间，\n鱼戏莲叶东，\n鱼戏莲叶西，\n鱼戏莲叶南，\n鱼戏莲叶北。", "")
    };

    public static final Poem[] poems3 = {
            new Poem("塞下曲", "唐 卢纶",
                    "月黑雁飞高，\n单于夜遁逃。\n欲将轻骑逐，\n大雪满弓刀。",
                    "①塞下曲：古时边塞的一种军歌。\t" +
                            "②月黑：没有月光。\t" +
                            "③单于（chán yú ）：匈奴的首领。这里指入侵者的最高统帅。\t" +
                            "④遁：逃走。\t" +
                            "⑤将：率领。\t" +
                            "⑥轻骑：轻装快速的骑兵。\t" +
                            "⑦逐：追赶。\t" +
                            "⑧满：沾满。\n" +
                            "译文：\n" +
                            "暗淡的月夜里，一群大雁惊叫着高飞而起，暴露了单于的军队想要趁夜色潜逃的阴谋。将军率领轻骑兵一路追杀，顾不得漫天的大雪已落满弓和刀。"),
            new Poem("忆江南", "唐 白居易",
                    "江南好，\n风景旧曾谙。\n日出江花红胜火，\n春来江水绿如蓝，\n能不忆江南？",
                            "译文： \n" +
                            "江南的风景多么美好，风景久已熟悉。春天到来时，太阳从江面升起，把江边的鲜花照得比火红，碧绿的江水绿得胜过蓝草。怎能叫人不怀念江南？\n" +
                            "整体赏析: \n" +
                            "忆江南，本为唐教坊曲名，后用作词牌名。"
            ),
            new Poem("江南春", "唐 杜牧",
                    "千里莺啼绿映红，\n水村山郭酒旗风。\n南朝四百八十寺，\n多少楼台烟雨中",
                    " 释义：\n莺啼：即莺啼燕语。\t" +
                            "郭：外城。此处指城镇。\t" +
                            "酒旗：一种挂在门前以作为酒店标记的小旗。\t" +
                            "南朝：指先后与北朝对峙的宋、齐、梁、陈政权。\t" +
                            "四百八十寺：南朝皇帝和大官僚好佛，在京城（今南京市）大建佛寺。据《南史·循吏·郭祖深传》说：“都下佛寺五百余所”。这里说四百八十寺，是虚数。\t" +
                            "楼台：楼阁亭台。此处指寺院建筑。\t" +
                            "烟雨：细雨蒙蒙，如烟如雾。\n" +
                            "译文：\n" +
                            "江南大地鸟啼声声绿草红花相映，水边村寨山麓城郭处处酒旗飘动。\t" +
                            "南朝遗留下的四百八十多座古寺，无数的楼台全笼罩在风烟云雨中。\n" +
                            "整体赏析:\n" +
                            "这首《江南春》，千百年来素负盛誉。四句诗，既写出了江南春景的丰富多彩，也写出了它的广阔、深邃和迷离。")
    };


    private Poem(String title, String author, String content, String desc) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString() {
        return this.title;
    }
}
