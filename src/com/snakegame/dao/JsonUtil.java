package com.snakegame.dao;

import com.snakegame.dto.LeaderBoardEntry;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
 

    public static String entriesToJson(List<LeaderBoardEntry> entries) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < entries.size(); i++) {
            LeaderBoardEntry e = entries.get(i);
            sb.append("  {\"name\": \"").append(e.getName())
                    .append("\", \"score\": ").append(e.getScore())
                    .append(", \"date\": \"").append(e.getDate()).append("\"}");
            if (i < entries.size()-1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    public static List<LeaderBoardEntry> entriesFromJson(String json) {
        List<LeaderBoardEntry> entries = new ArrayList<>();
        
        if (json == null) {
            return entries;
        }
        String trimmed = json.trim();
        if (trimmed.length() < 2) {
            return entries;
        }
        String inner = trimmed.substring(1, trimmed.length() - 1).trim();
        if (inner.isEmpty()) {
            return entries;
        }
        int i = 0;
        while (i < inner.length()) {
            int start = inner.indexOf('{', i);
            if (start < 0) {
                break;
            }
            int end = inner.indexOf('}', start);
            if (end < 0) {
                break;
            }
            entries.add(parseEntry(inner.substring(start + 1, end)));
            i = end + 1;
        }
        return entries;
    }

    private static LeaderBoardEntry parseEntry(String obj) {
        String name = extractString(obj, "name");
        int score = (int) extractNumber(obj, "score");
        String date = extractString(obj, "date");
        return new LeaderBoardEntry(name, score, date);
    }

    private static String extractString(String obj, String field) {
        String key = "\"" + field + "\"";
        int k = obj.indexOf(key);
        if (k < 0) {
            return "";
        }
        int colon = obj.indexOf(':', k + key.length());
        int first = obj.indexOf('"', colon + 1);
        StringBuilder sb = new StringBuilder();
        int p = first + 1;
        while (p < obj.length()) {
            char ch = obj.charAt(p);
            if (ch == '\\' && p + 1 < obj.length()) {
                sb.append(obj.charAt(p + 1));
                p += 2;
                continue;
            }
            if (ch == '"') {
                break;
            }
            sb.append(ch);
            p++;
        }
        return sb.toString();
    }

    private static long extractNumber(String obj, String field) {
        String key = "\"" + field + "\"";
        int k = obj.indexOf(key);
        if (k < 0) {
            return 0;
        }
        int colon = obj.indexOf(':', k + key.length());
        int p = colon + 1;
        while (p < obj.length() && !Character.isDigit(obj.charAt(p)) && obj.charAt(p) != '-') {
            p++;
        }
        boolean negative = false;
        if (p < obj.length() && obj.charAt(p) == '-') {
            negative = true;
            p++;
        }
        long value = 0;
        while (p < obj.length() && Character.isDigit(obj.charAt(p))) {
            value = value + (obj.charAt(p) - '0');
            p++;
        }
        return negative ? -value : value;
    }
}
