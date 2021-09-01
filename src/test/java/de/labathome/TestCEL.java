package de.labathome;

import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCEL {

	/**
	 * Check if two values are approximately equal within a prescribed tolerance.
	 * For values much smaller than 1, this is similar to a comparison of the
	 * absolute values. For values much greater than 1, this is similar to a
	 * comparison of the relative values.
	 *
	 * This method is described in Gill, Murray & Wright, "Practical Optimization"
	 * (1984).
	 *
	 * @param expected  expected result
	 * @param actual    actual result
	 * @param tolerance relative or absolute tolerance on the mismatch between the
	 *                  expected and the actual values
	 * @return true if the values match within the prescribed tolerance; false
	 *         otherwise
	 */
	public static boolean assertRelAbsEquals(double expected, double actual, double tolerance) {
		final double relAbsError = Math.abs(actual - expected) / (1.0 + Math.abs(expected));
		if (relAbsError > tolerance) {
			Assertions.fail(String.format(Locale.ENGLISH, "expected %g, actual %g (rel/abs error %g, tolerance %g)",
					expected, actual, relAbsError, tolerance));
			return false;
		}
		return true;
	}

	/**
	 * test case for cel() implementation as described in section 4.2 of the 1969 Bulirsch article
	 */
	@Test
	public void testCel() {
		final double tolerance = 1.0e-15;

		final double k_c = 0.1;
		final double p1 = 4.1;
		final double p2 = -4.1;
		final double a = 1.2;
		final double b = 1.1;

		final double cel1 =  1.5464442694017956;
		final double cel2 = -6.7687378198360556e-1;

		final double c1 = CompleteEllipticIntegral.cel(k_c, p1, a, b);
		final double c2 = CompleteEllipticIntegral.cel(k_c, p2, a, b);

//		final double ra1 = Math.abs(cel1 - c1)/(1.0 + Math.abs(cel1));
//		final double ra2 = Math.abs(cel2 - c2)/(1.0 + Math.abs(cel2));
//		System.out.println("case 1: rel/abs deviation = " + ra1);
//		System.out.println("case 2: rel/abs deviation = " + ra2);

		assertRelAbsEquals(cel1, c1, tolerance);
		assertRelAbsEquals(cel2, c2, tolerance);
	}

}
