import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Json {
    public Map<String, Object> content;
    public Json(String str) {
       content = new HashMap<>();
       System.out.println(getSubJson(str, 0));
       getElements(str.substring(1, getSubJson(str, 0)));
       System.out.println("DONE");
    }


    private void getElements(String str) throws IllegalArgumentException {
        String current_key;
        Object current_value;
        int s = 0;
        int e;
        while(true) {
            verifyChar(str, s, '"');
            e = getSubString(str, s);
            current_key = str.substring(s + 1, e);
            verifyChar(str, e + 1, ':');
            Object[] res = getValue(str, e + 2);
            current_value = res[0];
            s = (int) res[1] + 1;
            content.put(current_key, current_value);

            if(s == str.length()) {
                break;
            }
            else {
                verifyChar(str, s, ',');
            }
            s++;
        }
    }


    private Object[] getValue(String str, int start_index) throws IllegalArgumentException {
        verifyIndex(str, start_index);
        Object[] res = new Object[2];
        int end_index;
        switch (str.charAt(start_index)) {
            case '"':
                end_index = getSubString(str, start_index);
                res[0] = str.substring(start_index + 1, end_index);
                res[1] = end_index;
                return res;

            case '{':
                end_index = getSubJson(str, start_index);
                res[0] = new Json(str.substring(start_index, end_index + 1));
                res[1] = end_index;
                return res;

            case '[':
                Object[] list_res = getList(str, start_index);
                res[0] = list_res[0];
                res[1] = list_res[1];
                return res;

            case 't':
                res[0] = true;
                res[1] = start_index + 3;
                return res;

            case 'f':
                res[0] = false;
                res[1] = start_index + 4;
                return res;

            case 'n':
                res[0] = null;
                res[1] = start_index + 3;
                return res;

            default:
                Object[] num_res = getNumber(str, start_index);
                res[0] = num_res[0];
                res[1] = num_res[1];
                return res;
        }
    }


    private Object[] getList(String str, int startIndex) throws IllegalArgumentException {
        int s = startIndex + 1;
        int e;
        LinkedList<Object> result = new LinkedList<>();

        loop: while (true) {
            verifyIndex(str, s);
            switch (str.charAt(s)) {
                case '"':
                    e = getSubString(str, s);
                    result.add(str.substring(s + 1, e));
                    s = e + 1;
                    break;

                case '{':
                    e = getSubJson(str, s);
                    result.add(new Json(str.substring(s, e + 1)));
                    s = e + 1;
                    break;

                case '[':
                    Object[] list_res = getList(str, s);
                    e = (int) list_res[1];
                    result.add(list_res[0]);
                    s = e + 1;
                    break;

                case ']':
                    break loop;

                default:
                    Object[] num_res = getNumber(str, s);
                    e = (int) num_res[1];
                    result.add(num_res[0]);
                    s = e + 1;
                    break;
            }
            verifyIndex(str, s);
            char ch = str.charAt(s);
            if(ch == ']') {
                break;
            }
            else if(ch != ',') {
                throw new IllegalArgumentException("Expected ',' or ']'");
            }
            s++;

        }
        Object[] res = new Object[2];
        res[0] = result;
        res[1] = s;
        return res;
    }


    private Object[] getNumber(String str, int startIndex) {
        int endIndex = startIndex;
        Object[] res = new Object[2];
        while (true) {
            if(endIndex == str.length()) {
                break;
            }
            char ch = str.charAt(endIndex);
            if(ch == ',' || ch == ']' || ch == '}') {
                break;
            }
            endIndex++;
        }
        endIndex--;
        res[1] = endIndex;
        String subString = str.substring(startIndex, endIndex + 1);
        try {
            if (subString.contains(".")) {
                res[0] = Double.parseDouble(subString);
            } else {
                res[0] = Integer.parseInt(subString);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("String is not a Json");
        }
        return res;
    }


    private void verifyIndex(String str, int index) throws IllegalArgumentException {
        if(index >= str.length()) {
            throw new IllegalArgumentException("String is not a Json");
        }
    }


    private void verifyChar(String str, int index, char ch) throws IllegalArgumentException {
        verifyIndex(str, index);
        if(str.charAt(index) != ch) {
            throw new IllegalArgumentException("String is not a Json");
        }
    }


    private int getSubJson(String str, int start) throws IllegalArgumentException {
        int i = start + 1;
        while (true) {
            verifyIndex(str, i);
            char currentChar = str.charAt(i);
            switch (currentChar) {
                case '{':
                    i = getSubJson(str, i);
                    i++;
                    break;
                case '}':
                    return i;
                case '"':
                    i = getSubString(str, i);
                    i++;
                    break;
                default:
                    i++;
            }
        }
    }


    private int getSubString(String str, int start) throws IllegalArgumentException {
        int i = start + 1;
        while (true) {
            verifyIndex(str, i);
            if (str.charAt(i) == '"') {
                return i;
            }
            i++;
        }
    }
}
