import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class P2 extends Thread
{
    private CyclicBarrier signal;
    private CyclicBarrier done;

    private Point p;

    public Object EAX, EBX;

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

    P2(Point p, CyclicBarrier sig, CyclicBarrier done) {
        EAX = EBX = 0;
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

        //MOV EAX,[x]
        //MOV EBX,[y]
        EAX = X.getOpaque(p);
        EBX = Y.getOpaque(p);

        try {
            done.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
