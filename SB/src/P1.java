import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class P1 extends Thread
{
    private CyclicBarrier signal;
    private CyclicBarrier done;

    private Point p;

    Object EAX;

    private static VarHandle X;
    static {
        try {
            X = MethodHandles.lookup()
                    .findVarHandle(Point.class, "x", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static VarHandle Y;
    static {
        try {
            Y = MethodHandles.lookup()
                    .findVarHandle(Point.class, "y", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    P1(Point p, CyclicBarrier sig, CyclicBarrier done) {
        EAX = 0;
        signal = sig;
        this.done = done;
        this.p = p;
    }

    public void run() {

        try {
            signal.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // MOV [y],$1
        // MOV EAX,[x]
        Y.setOpaque(p,1);
        EAX = X.getOpaque(p);

        try {
            done.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
