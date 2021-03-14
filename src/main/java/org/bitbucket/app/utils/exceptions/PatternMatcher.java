package org.bitbucket.app.utils.exceptions;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static String removeAllJsonSyntax(String string){

        Pattern pattern = Pattern.compile("(\\\\[nt]|\\s|[{}\\[\\]\"])");
        Matcher matcher = pattern.matcher(string);
        return matcher.replaceAll("");

    }

    public static List<String> jsonToPersonList(String input){

        List<String> personJsonList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{[^\\{^\\}.]*}");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            personJsonList.add(matcher.group());
        }
        return personJsonList;

    }

    public static boolean isBlank(String s){

        Pattern pattern = Pattern.compile("\\{[\\t\\s\\n]*}");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();

    }

    public static String takeId(String input){

        Pattern pattern = Pattern.compile("\\s\\d{1,19}$");
        Matcher matcher = pattern.matcher(input);
        String result = null;
        if(matcher.find()) {result = matcher.group().trim();}
        return result;

    }

    public static String takeCommand(String input){

        Pattern pattern = Pattern.compile("^[a-z]*\\b");
        Matcher matcher = pattern.matcher(input);
        String result = null;
        if(matcher.find()) {result = matcher.group().trim();}
        return result;

    }

    public static String takeFileName(String input){

        Pattern pattern = Pattern.compile("(\\s[^\\s]*\\.[a-z]*\\b|\".*\\.[a-z]*\")");
        Matcher matcher = pattern.matcher(input);
        String result = null;
        if(matcher.find()) {result = matcher.group().trim();}
        if(result!=null && result.charAt(0)=='"'){result = result.replace("\"","");}
        return result;

    }

    public static boolean isValidFileName(String input){

        if(input == null) {return true;}
        Pattern pattern = Pattern.compile("[\\/\\\\\\:\\*\\?\\>\\<\\|\\+\"\\|\\}\\{\\(\\)\\[]");
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();

    }

    public static boolean isValidName(String input){

        if(input.isBlank()){return false;}
        Pattern pattern = Pattern.compile("[\\{\\}\\<\\>\\;\\,\\:\\\\\\/\\[\\]]");
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();

    }
}
