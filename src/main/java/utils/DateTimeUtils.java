package utils;

public class DateTimeUtils {

    public static void wait(int duration){
        try {
            Thread.sleep(duration*1000L);
        } catch (InterruptedException e) {
            System.out.println("Exception in Thread.sleep(): " +e.getMessage());

        }

    }
}
