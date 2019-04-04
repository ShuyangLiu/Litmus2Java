import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main
{
    public static void main(String args[]) throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier signal = new CyclicBarrier(3);
        final CyclicBarrier done = new CyclicBarrier(3);
        Point p = new Point();

        P0 p0 = new P0(p, signal, done);
        P1 p1 = new P1(p, signal, done);

        p0.start();
        p1.start();

        // start the threads all at once
        signal.await();

        // wait for all of them done
        done.await();

        // This assertion would fail if there is a inconsistency
        assert !((int)p0.EAX == 1 && (int)p0.EBX == 0 && p.x == 1);
    }
}
