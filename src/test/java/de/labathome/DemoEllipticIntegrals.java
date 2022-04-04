package de.labathome;

import aliceinnets.python.jyplot.JyPlot;

public class DemoEllipticIntegrals {

	public static void main(String[] args) {

		run();

		//demoEandK();
	}

	public static void run() {

		// see where things to wrong for k_c --> 1
		double kc = 1.0 - 1.0e-15;

		double c1 = CompleteEllipticIntegral.cel(kc, 1, -1, 1);
		double c2 = CompleteEllipticIntegral.cel(kc, kc*kc, -1, 1);

		System.out.printf("cel(k_c,     1, -1, 1) = %g\n", c1);
		System.out.printf("cel(k_c, k_c^2, -1, 1) = %g\n", c2);
	}

	public static void demoEandK() {
		int n = 101;
		double[] x = new double[n];

		double[] k = new double[n];
		double[] e = new double[n];

		for (int i=0; i<n; ++i) {
			x[i] = i/(n-1.0);

			k[i] = CompleteEllipticIntegral.ellipticK(x[i]);
			e[i] = CompleteEllipticIntegral.ellipticE(x[i]);
		}

		JyPlot plt = new JyPlot();

		plt.figure();
		plt.plot(x, k, "label='K(k)'");
		plt.plot(x, e, "label='E(k)'");
		plt.xlabel("k");
		plt.ylabel("E, K");
		plt.grid(true);
		plt.legend("loc='upper left'");

		plt.show();
		plt.exec();
	}
}
