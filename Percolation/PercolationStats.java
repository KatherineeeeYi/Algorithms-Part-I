/**
 * Created by Xuyu Yi on 2017/5/21.
 * For the assignment 1 of Algorithms
 * A scientific application of the union–find data structure
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;



public class PercolationStats {
    private double[ ] x;
    private int T;

    public PercolationStats(int n, int trials) {
        if(n<=0||trials<=0){
            throw new IllegalArgumentException("argument must be positive");
        }
        this.T=trials;
        x=new double[T];
        for(int i=0;i<T;i++){
            Percolation p=new Percolation(n);
            while(true) {
                int m, k;
                do {
                    m = StdRandom.uniform(n) + 1;
                    k = StdRandom.uniform(n) + 1;
                }while(p.isOpen(m,k));
                p.open(m, k);
                if (p.percolates()) {
                    break;
                }
            }
            x[i]=(double)p.numberOfOpenSites()/(n*n);
        }
    }// perform trials independent experiments on an n-by-n grid

    public double mean()  {
        return StdStats.mean(x);
    }                        // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(x);

    }                       // sample standard deviation of percolation threshold
    public double confidenceLo() {
        double me=mean();
        double s=stddev();
        return me-(1.96*s)/(T^(1/2));
    }                 // low  endpoint of 95% confidence interval
    public double confidenceHi()  {
        double me=mean();
        double s=stddev();
        return me+(1.96*s)/(T^(1/2));
    }                // high endpoint of 95% confidence interval

    public static void main(String[] args)   {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats pStats = new PercolationStats(n, T);
        StdOut.printf("mean = %f\n", pStats.mean());
        StdOut.printf("stddev = %f\n", pStats.stddev());
        StdOut.printf("95%% confidence interval = ["+pStats.confidenceLo()+", "+ pStats.confidenceHi()+"]");



    }     // test client (described below)
}
