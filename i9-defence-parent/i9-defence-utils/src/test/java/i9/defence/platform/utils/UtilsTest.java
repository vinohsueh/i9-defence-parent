package i9.defence.platform.utils;

import java.util.Date;

public class UtilsTest {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

        // final DefaultFuture<String> future = new DefaultFuture<String>();
        //
        // ExecutorService executorService =
        // Executors.newSingleThreadExecutor();
        // executorService.submit(new Callable<String>() {
        // @Override
        // public String call() throws Exception {
        // Thread.sleep(10000);
        // String uuid = UUID.randomUUID().toString();
        // future.doReceived(uuid);
        // return uuid;
        // }
        // });
        //
        // System.out.println(future.get());
        // executorService.shutdown();

        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacade bookFacade = cglib.newInstance(BookFacade.class);
        bookFacade.print();

        Date date = cglib.newInstance(Date.class);
        int hour = date.getHours();
        System.out.println(hour);
    }
}
