public class ChapterA {

    private static Object s = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        for(;;){
            new Thread(() -> {
                synchronized(s){
                    count += 1;
                    System.err.println("New thread #"+ count);
                }
                for(;;){
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                }
            }).start();
        }
    }
}
