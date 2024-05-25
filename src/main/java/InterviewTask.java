import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class InterviewTask {
    public static void main(String[] args) {


        long start = System.currentTimeMillis();
        Executor executor = Executors.newFixedThreadPool(20);
        List<CompletableFuture<Integer>> completableFutures = IntStream.range(0, 20)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                    return i;
                }, executor)).toList();
        completableFutures.stream().map(CompletableFuture::join).forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);
    }
}
