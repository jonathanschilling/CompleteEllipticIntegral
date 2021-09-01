package de.labathome;

/** Complete Elliptic Integral introduced by R. Burlisch (1969) */
public class CompleteEllipticIntegral {

	private static final double PI_2 = Math.PI/2.0;

	public static double cel(double k_c, double p, double a, double b) {

		final double CA = Math.sqrt(Math.nextUp(1.0) - 1.0);

		// kc is the value of k_c to actually use
		double kc;
		if (k_c != 0.0) {
			kc = k_c;
		} else {
			if (b != 0.0) {
				return Double.NaN;
			} else {
				// 10^-D describes the numerical precision of the floating-point implementation in use
				kc = CA*CA;
			}
		}

		double mu = 1.0;

		double e = kc;
		double f, q, g;

		if (p > 0.0) {
			p = Math.sqrt(p);
			b = b / p;
		} else { // p <= 0
			f = kc*kc;
			q = 1.0 - f;        // 1 - kc^2
			g = 1.0 - p;
			f -= p;             // kc^2 - p
			q *= b-a*p;         // (1 - kc^2)*(b-a*p)
			p = Math.sqrt(f/g); // (kc^2 - p)/(1-p)                    --> p done here
			a = (a-b)/g;        // (a-b)/(1-p)                         --> a done here
			b = -q/(g*g*p)+a*p; // -(1 - kc^2)*(b-a*p)/( (1-p)^2 * p ) --> b done here
		}

		boolean iterate = true;
		while (iterate) {
			f = a;
			a += b/p;
			g = e/p;
			b += f*g;
			b += b;
			p += g;
			g = mu;
			mu += kc;
			if (Math.abs(g - kc) > g*CA) {
				kc = Math.sqrt(e);
				kc += kc;
				e = kc*mu;
			} else {
				iterate = false;
			}
		}

		return PI_2*(a*mu+b)/(mu*(mu+p));
	}

}
