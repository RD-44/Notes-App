package uk.ac.ucl.htmlfilter;

public class Filter {
    public static String filter(String input){
        // Avoids strings printed in html being recognised as html code
        // This is achieved by replacing certain suspect characters with their html equivalent
        return input.replaceAll("\"", "&quot;")
                .replaceAll("<", "&#60;")
                .replaceAll(">", "&#62;");
    }
}
