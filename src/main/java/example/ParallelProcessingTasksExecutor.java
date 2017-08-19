package example;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ParallelProcessingTasksExecutor
 *
 */
public final class ParallelProcessingTasksExecutor {
	// Tag
	public static final String TAG = ParallelProcessingTasksExecutor.class.getSimpleName();
	
	// Pool size
	public static final int POOL_SIZE = 10;
	// Start task after START_AFTER second
	public static final int START_AFTER = 0;
	
	// Instance
	private static ParallelProcessingTasksExecutor instance;
	// Executor
	private ScheduledExecutorService executor;
	// Futures
	private Map<Integer, Future<?>> futuresMap;
	
	/**
	 * Constructor
	 */
    public ParallelProcessingTasksExecutor() {
        // set map of futures
        this.futuresMap = new HashMap<Integer, Future<?>>();
        // set executor
        this.executor = Executors.newScheduledThreadPool(POOL_SIZE, new ThreadFactory() {
            // Name
            private final String name = "ParallelProcessingTasksExecutor";
            // Counter
            private AtomicInteger integer = new AtomicInteger(1);

            /**
             * New thread
             *
             * @param r Runnable
             * @return Thread
             */
            public Thread newThread(Runnable r) {
                return new Thread(r, name + "(" + integer.getAndIncrement() + ")");
            }
        });
	}
	
    /**
     * Get instance
     *
     * @return ParallelProcessingTasksExecutor
     */
    public static synchronized ParallelProcessingTasksExecutor getInstance() {
        if (instance == null) {
            instance = new ParallelProcessingTasksExecutor();
        }
        return instance;
    }
    
    /**
     * Submit task
     * 
     * @param task Runnable
     * @return int
     * @throws Exception
     */
    public int submit(Runnable task) throws Exception {
    	return submit(task, START_AFTER);
    }
    
    /**
     * Submit task
     * 
     * @param task Runnable
     * @param startAfter int
     * @return int
     * @throws Exception
     */
    public int submit(Runnable task, int startAfter) throws Exception {
    	try {
    		int key = task.hashCode();
    		futuresMap.put(key, executor.schedule(task, startAfter, TimeUnit.SECONDS));
    		return key;
		} catch (Exception e) {
			throw new Exception(TAG + "[submit]: Task submit fail", e);
		}
    }
	
    /**
     * Cancel taks
     * 
     * @param key int
     */
    public void cancel(int key) {
    	if ((futuresMap != null) && (!futuresMap.isEmpty()) && (futuresMap.containsKey(key))) {
    		Future<?> future = futuresMap.get(key);
            if (!future.isDone()) {
                future.cancel(true);
            }
            futuresMap.remove(key);
    	}
    }
    
    /**
     * Dissolve all
     */
    public void dissolve() {
    	if ((futuresMap != null) && (!futuresMap.isEmpty())) {
    		for (Map.Entry<Integer, Future<?>> entry : futuresMap.entrySet()) {
                Future<?> future = entry.getValue();
                if (!future.isDone()) {
                    future.cancel(true);
                }
    		}
    	}
    	executor.shutdownNow();
    	instance = null;
    }
    
}
