package uk.ac.ucl.htmlfilter;

public class Filter {
    public static String parse(String input){ // Avoids strings printed in html being recognised as html code
        return input.replaceAll("\"", "&quot;")
                .replaceAll("<", "&#60;")
                .replaceAll(">", "&#62;");
    }
}
