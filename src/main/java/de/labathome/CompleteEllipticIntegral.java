package de.labathome;

/**
 * Complete Elliptic Integral introduced by R. Bulirsch (1969).
 * These routines are based on a set of three articles:
 * * https://doi.org/10.1007/BF01397975 (Part I)
 * * https://doi.org/10.1007/BF01436529 (Part II)
 * * https://doi.org/10.1007/BF02165405 (Part III)
 */
public class CompleteEllipticIntegral {

	/** half of pi */
	private static final double PI_2 = Math.PI/2.0;

	/** sqrt of machine epsilon */
	private static final double SQRT_EPS = Math.sqrt(Math.nextUp(1.0) - 1.0);

	/**
	 * Compute the complete elliptic integral introduced in
	 * "Numerical Calculation of Elliptic Integrals and Elliptic Functions. III"
	 * by R. Bulirsch in "Numerische Mathematik" 13, 305-315 (1969):
	 * cel(k_c, p, a, b) =
	 * \int_0^{\pi/2} \frac{a \cos^2{\varphi} + b \sin^2{\varphi}}
	 *                     {  \cos^2{\varphi} + p \sin^2{\varphi}}
	 *                \frac{\mathrm{d}\varphi}
	 *                     {\sqrt{\cos^2{\varphi} + k_c^2 \sin^2{\varphi}}}
	 * @param k_c parameter k_c of cel(); absolute value must not be 0
	 * @param p   parameter p of cel()
	 * @param a   parameter a of cel()
	 * @param b   parameter b of cel()
	 * @return the value of cel(k_c, p, a, b)
	 */
	public static double cel(double k_c, double p, double a, double b) {
		if (k_c == 0.0) {
			if (b != 0.0) {
				// when k_c is zero and b != 0, cel is undefined
				return Double.NaN;
			} else {
				k_c = SQRT_EPS*SQRT_EPS;
			}
		} else {
			k_c = Math.abs(k_c);
		}

		double m = 1.0; // \mu
		double e = k_c;

		double f, q, g;

		// initialization
		if (p > 0.0) {
			p = Math.sqrt(p);
			b = b / p;
		} else { // p <= 0
			f = k_c*k_c;
			q = 1.0 - f;        // 1 - kc^2
			g = 1.0 - p;
			f -= p;             // kc^2 - p
			q *= b-a*p;         // (1 - kc^2)*(b-a*p)
			p = Math.sqrt(f/g); // (kc^2 - p)/(1-p)                    --> p done here
			a = (a-b)/g;        // (a-b)/(1-p)                         --> a done here
			b = -q/(g*g*p)+a*p; // -(1 - kc^2)*(b-a*p)/( (1-p)^2 * p ) --> b done here
		}

		// iteration until convergence
		boolean iterate = true;
		while (iterate) {
			f = a;
			a += b/p;
			g = e/p;
			b += f*g;
			b += b;
			p += g;
			g = m;
			m += k_c;
			if (Math.abs(g - k_c) > g*SQRT_EPS) {
				k_c = Math.sqrt(e);
				k_c += k_c;
				e = k_c*m;
			} else {
				iterate = false;
			}
		}

		return PI_2*(a*m+b)/(m*(m+p));
	}

}
