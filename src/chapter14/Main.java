package chapter14;

public class Main {

    public static void main(String[] args) {
        String[] mArgs = {"-true", "-100"};

        try {
            Args arg = new Args("l,p#,d*", mArgs);

            boolean logging = arg.getBoolean('l');
            int port = arg.getInt('p');
            String directory = arg.getString('d');

            System.out.println("logging " + logging +  ", port : " + port + " , directory : " + directory);
        } catch (ArgsException e) {
            System.out.println("Argument error: " + e.errorMessage());
        }
    }
}
