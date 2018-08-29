//package i9.defence.platform.socket.test;
//
//import java.util.concurrent.TimeUnit;
//
//import org.zeroturnaround.exec.ProcessExecutor;
//import org.zeroturnaround.exec.stream.LogOutputStream;
//
//public class UninxShellExecuteTest {
//
//    public static void main(String[] args) throws Exception {
//        ProcessExecutor processExecutor = new ProcessExecutor().command("ping", "www.baidu.com", "-t")
//                .redirectOutput(new LogOutputStream() {
//                    @Override
//                    protected void processLine(String line) {
//                        System.out.println(line);
//                    }
//                }).timeout(60, TimeUnit.SECONDS);
//        processExecutor.execute();
//    }
//}
