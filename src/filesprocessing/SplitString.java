package filesprocessing;

/**
 * a class that is only used to split a line into a few Strings with a seperator (it was made as a class
 * because i use it in several places and felt it made more sense than making it a method in another class).
 */
public class SplitString {

    /**
     * splits a String into a String array using the given separator.
     * @param toSplit   -   the String to split.
     * @param spliter   -   the given separator.
     * @return  an array of String consisting of the given String splitted according to the spliter.
     */
    public static String[] split(String toSplit, char spliter){
        String[] endStringArray = new String[4];
        StringBuilder st = new StringBuilder();
        int i = 0;
        for (int j = 0; j < toSplit.length(); j++){
            if (toSplit.charAt(j) != spliter){
                st.append(toSplit.charAt(j));
            }
            else{
                endStringArray[i] = st.toString();
                st = new StringBuilder();
                i++;
            }
        }
        endStringArray[i] = st.toString();
        int size = 0;
        for (String s : endStringArray){
            if (s != null)
                size++;
        }
        String[] reducedArray = new String[size];
        System.arraycopy(endStringArray, 0, reducedArray, 0, size);
        return reducedArray;
    }
}
