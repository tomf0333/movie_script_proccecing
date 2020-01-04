package filesprocessing.orders;

import java.io.File;

/**
 * a class that resembles a factory for the different orders in the package,
 * only it doesn't create an object of the order but only calls the correct order that was requested.
 * it also houses an orders enum used to make sure only correct orders will be requested.
 */
public class OrderSubsection {

    /**
     * the enum listing the different orders as mentioned in the super class description.
     */
    public enum orders {
        abs("abs"), type("type"), size("size");
        private final String name;

        orders(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * a method that reverses a File array if needed.
     * @param files -   the File array to reverse.
     * @return  the reversed File array.
     */
    static File[] reverse(File[] files) {
        File[] reversedFiles = new File[files.length];
        int j = files.length - 1;
        for (int i = 0; i < files.length; i++) {
            reversedFiles[j] = files[i];
            j--;
        }
        return reversedFiles;
    }

    // the order method, receives an enum that makes it know which order to use (to prevent user mistakes)
    // and the right type parameters for given order.
    public static File[] order(File[] files, orders order, boolean reverse) {
        switch (order) {
            case abs:
                return OrderAbs.order(files, reverse);
            case size:
                return OrderSize.order(files, reverse);
            case type:
                return OrderType.order(files, reverse);
            default:
                return files;
        }
    }
}
