# CompleteEllipticIntegral
This is a Java implementation of the complete elliptic integral introduced by R. Bulirsch in 1965 and 1969.
These routines are based on a set of three articles named "Numerical calculation of elliptic integrals and elliptic functions":
 * https://doi.org/10.1007/BF01397975 (Part I, Numer. Math. 7, 78–90 (1965))
 * https://doi.org/10.1007/BF01436529 (Part II, Numer. Math. 7, 353–354 (1965))
 * https://doi.org/10.1007/BF02165405 (Part III, Numer. Math. 13, 305–315 (1969))

The complete elliptic integral `cel(k_c, p, a, b)` is defined as follows:

![complete elliptic integral](eqn/cel.png)

## Maven Central
This code snippet is available on Maven Central at the following coordinates:

```xml
<dependency>
  <groupId>de.labathome</groupId>
  <artifactId>CompleteEllipticIntegral</artifactId>
  <version>1.0.0</version>
</dependency>
```
