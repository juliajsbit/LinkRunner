package example;

/**
 * Created by ykolesnik on 13.10.2015.
 */
public class mainTest {

    public static void main(String[] args) throws Exception {
        Runnable runnable = new test();
        Runnable runnable2 = new test();
        ParallelProcessingTasksExecutor parallelProcessingTasksExecutor = new ParallelProcessingTasksExecutor();

        System.out.println("Основной поток ");
        parallelProcessingTasksExecutor.submit(runnable);
        parallelProcessingTasksExecutor.submit(runnable2);

    }
}
