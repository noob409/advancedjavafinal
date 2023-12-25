package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JapaneseJsonCatch {

    private List<JapaneseType> japaneseList = new ArrayList<>();
    public JapaneseJsonCatch() throws IOException {

        JSONObject jsonObject = readJSONFromFile("kana.json");
        jsonObject.getJSONObject("-");

        for (String outermost: jsonObject.keySet()) {   // 最外層的資料
            JSONObject outerObject = jsonObject.getJSONObject(outermost);
            for (String middle: outerObject.keySet()) {    // 中間層的資料
                JSONObject middleSession = outerObject.getJSONObject(middle);
                for (String inner: middleSession.keySet()) {    // 最內層的資料
                    JSONObject innerSession = middleSession.getJSONObject(inner);
                    if (innerSession.has("Katakana") && innerSession.has("Romaji") && innerSession.has("Hiragana")) {
                        String getKatakana = innerSession.getString("Katakana");
                        String getRomaji = innerSession.getString("Romaji");
                        String getHiragana = innerSession.getString("Hiragana");
                        japaneseList.add(new JapaneseType(getRomaji, getKatakana, getHiragana));
                    } else {
                        System.out.println("Not found for " + middle);
                    }
                }
            }
        }
    }

    public List<JapaneseType> getJapaneseList() {
        return japaneseList;
    }

    public static JSONObject readJSONFromFile(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
}
