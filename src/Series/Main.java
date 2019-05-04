package Series;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	// write your code here

        File inFile = null;

        if (0 < args.length) {
            inFile = new File(args[0]);
        } else {
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
        }
    }
}
