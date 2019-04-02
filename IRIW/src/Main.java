import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main
{
    public static void main(String args[]) throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier signal = new CyclicBarrier(5);
        Point p = new Point();

        P0 p0 = new P0(p, signal);
        P1 p1 = new P1(p, signal);
        P2 p2 = new P2(p, signal);
        P3 p3 = new P3(p, signal);


        p0.start();
        p1.start();
        p2.start();
        p3.start();

        // start the threads all at once
        signal.await();

        System.out.println("P0: EAX="+p0.EAX);
        System.out.println("P0: EBX="+p0.EBX);
        System.out.println("P2: EAX="+p2.EAX);
        System.out.println("P2: EBX="+p2.EBX);

    }
}
