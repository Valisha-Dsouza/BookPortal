package onlinebookstore.utils;


public class Utils {

    public static String process(String param) {
        String result = "";

        if (param == null || param.isEmpty()) {
            return result;
        }

        String[] words = param.split("\\s");
        for (int i = 0; i < words.length; i++) {
            result += words[i].substring(0, 1).toUpperCase()
                    + words[i].substring(1).toLowerCase();

            if (i < words.length - 1) {
                result += " ";
            }
        }

        return result;
    }


}
