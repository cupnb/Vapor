package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Json {
    private final Map<String, Object> content;

    /**
     * Tries to parse the String as a Json.
     * @param str String that`s to be parsed
     */
    public Json(String str) {
       content = new HashMap<>();
       getElements(str.substring(1, getSubJson(str, 0)).replace("\\n", ""));
    }


    private void getElements(String str) throws IllegalArgumentException {
        if (str.equals("") || str.equals(" ")) { //Is the Json empty?
            return;
        }
        String current_key;
        Object current_value;
        int s = 0;
        int e;
        while(true) {
            //"KEY":VALUE
            verifyChar(str, s, '"'); //Key needs to be a String
            e = getSubString(str, s); //This is the current Key
            current_key = str.substring(s + 1, e); //Remove "
            verifyChar(str, e + 1, ':'); //Key and Value is separated by ':'
            Object[] res = getValue(str, e + 2); //Get the Value
            current_value = res[0]; //This is the current  Value
            s = (int) res[1] + 1; //The index where the Value ended
            content.put(current_key, current_value);

            if(s == str.length()) {
                break;
            }
            else {
                verifyChar(str, s, ','); //Key-Value pairs need to be separated by ','
            }
            s++;
        }
    }


    private Object[] getValue(String str, int start_index) {
        verifyIndex(str, start_index);
        Object[] res = new Object[2];
        int end_index;
        switch (str.charAt(start_index)) { //First char of the Value
            case '"': //Its a String
                end_index = getSubString(str, start_index);
                res[0] = str.substring(start_index + 1, end_index);
                res[1] = end_index;
                return res;

            case '{': //Its a Json
                end_index = getSubJson(str, start_index);
                res[0] = new Json(str.substring(start_index, end_index + 1));
                res[1] = end_index;
                return res;

            case '[': //Its a List
                Object[] list_res = getList(str, start_index);
                res[0] = list_res[0];
                res[1] = list_res[1];
                return res;

            case 't': //Its 'true'
                res[0] = true;
                res[1] = start_index + 3;
                return res;

            case 'f': //Its 'false'
                res[0] = false;
                res[1] = start_index + 4;
                return res;

            case 'n': //Its 'null'
                res[0] = null;
                res[1] = start_index + 3;
                return res;

            default: //Has to be a Number then (at least i hope so)
                Object[] num_res = getNumber(str, start_index);
                res[0] = num_res[0];
                res[1] = num_res[1];
                return res;
        }
    }


    private Object[] getList(String str, int startIndex) {
        int s = startIndex + 1;
        int e;
        LinkedList<Object> result = new LinkedList<>();

        loop: while (true) { //Go through every entry in the List
            verifyIndex(str, s);
            switch (str.charAt(s)) { //First Char of the current element
                case '"': //Its a String
                    e = getSubString(str, s);
                    result.add(str.substring(s + 1, e));
                    s = e + 1;
                    break;

                case '{': //Its a Json
                    e = getSubJson(str, s);
                    result.add(new Json(str.substring(s, e + 1)));
                    s = e + 1;
                    break;

                case '[': //Its another List
                    Object[] list_res = getList(str, s);
                    e = (int) list_res[1];
                    result.add(list_res[0]);
                    s = e + 1;
                    break;

                case ']': //There are no more entries in the List so we are done
                    break loop;

                default: //Its a Number
                    Object[] num_res = getNumber(str, s);
                    e = (int) num_res[1];
                    result.add(num_res[0]);
                    s = e + 1;
                    break;
            }
            verifyIndex(str, s);
            char ch = str.charAt(s);
            if(ch == ']') { //There are no more entries in the List so we are done
                break;
            }
            else if(ch != ',') { //List elements need to be separated by ','
                throw new IllegalArgumentException("Expected ',' or ']'");
            }
            s++;

        }
        Object[] res = new Object[2];
        res[0] = result.toArray();
        res[1] = s;
        return res;
    }


    private Object[] getNumber(String str, int startIndex) {
        int endIndex = startIndex;
        Object[] res = new Object[2];
        while (true) { //Find the end of the Number
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
            if (subString.contains(".")) { //Its a decimal Number
                res[0] = Double.parseDouble(subString);
            } else { //Its an Integer
                res[0] = Integer.parseInt(subString);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException(String.format("Couldn't parse Number: %s", subString));
        }
        return res;
    }


    private void verifyIndex(String str, int index) { //Verify that the index is not out of range
        if(index >= str.length()) {
            throw new IllegalArgumentException("Index out of range");
        }
    }


    private void verifyChar(String str, int index, char ch) { //Verify a char at a given index
        verifyIndex(str, index);
        if(str.charAt(index) != ch) {
            throw new IllegalArgumentException(String.format("Expected %c at %d", ch, index));
        }
    }


    private int getSubJson(String str, int start) {
        int i = start + 1;
        while (true) {
            verifyIndex(str, i);
            char currentChar = str.charAt(i);
            switch (currentChar) {
                case '{': //Skip the inner Json
                    i = getSubJson(str, i);
                    i++;
                    break;
                case '}': //This is the end of the Json
                    return i;
                case '"': //Skip the String
                    i = getSubString(str, i);
                    i++;
                    break;
                default:
                    i++;
            }
        }
    }


    private int getSubString(String str, int start) {
        int i = start + 1;
        while (true) {
            verifyIndex(str, i);
            if (str.charAt(i) == '"') { //Probably the end of the String
                if (i > 0) {
                    if (str.charAt(i - 1) != '\\') { // \" does not count
                        return i;
                    }
                }
                else {
                    return i;
                }
            }
            i++;
        }
    }

    /**
     * Returns the Value of a given Key
     * @param key The Key
     * @return The Value (nullable)
     */
    public Object getContent(String key) {
        return content.get(key);
    }


}
