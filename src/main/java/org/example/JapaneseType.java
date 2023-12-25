package org.example;

public class JapaneseType {

    private String romaji;
    private String katakana;
    private String hiragana;
    public JapaneseType(String romaji, String katakana, String hiragana) {
        this.romaji = romaji;
        this.katakana = katakana;
        this.hiragana = hiragana;
    }

    public String getRomaji() {
        return romaji;
    }
    public String getKatakana() {
        return katakana;
    }

    public String getHiragana() {
        return hiragana;
    }
}
