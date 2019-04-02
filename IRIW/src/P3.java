import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class P3 extends Thread
{
    private Point p;
    private CyclicBarrier signal;

    static VarHandle X;
    static {
        try {
            X = MethodHandles.lookup()
                    .findVarHandle(Point.class, "x", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static VarHandle Y;
    static {
        try {
            Y = MethodHandles.lookup()
                    .findVarHandle(Point.class, "y", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    P3(Point p, CyclicBarrier sig) {
        this.p = p;
        signal = sig;
    }

    public void run() {
        try {
            signal.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        // MOV [y],$1
        Y.setOpaque(p, 1);
    }


}