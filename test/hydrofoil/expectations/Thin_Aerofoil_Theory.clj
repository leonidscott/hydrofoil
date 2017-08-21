(ns hydrofoil.expectations.Thin_Aerofoil_Theory
  (:require [expectations :refer :all]
            [hydrofoil.core :refer :all]
            [hydrofoil.utils :refer :all]
            [hydrofoil.Model.Thin_Aerofoil_Theory :refer :all]))

;;;-------- coefficient-of-lift-symetric ---------
(expect 0.0
        (round-double (:cl (coefficient-of-lift (NACA-design 0 0.5 10) (run-constants 0 0 0 0 0)))))

(expect 2.1932
        (round-double (:cl (coefficient-of-lift (NACA-design 0 0.5 10) (run-constants 0 0 20 0 0)))))

;;;-------- coefficient-of-lift-Components ---------
;(expect 0.0882
;        (round-double (integral-production (NACA-design 2 4 12) gradient-forward-polar-function 0 1.369 (/ (theta-switch (NACA-design 2 4 12)) 200) 200)))


(expect -0.0044
        (round-double (A-0 (NACA-design 2 4 12) (run-constants 0 0 0 0 0))));; should expect -0.0044

(expect 0.0815
        (round-double (A-1 (NACA-design 2 4 12) (run-constants 0 0 0 0 0))))

(expect 0.2275
        (round-double (:cl (coefficient-of-lift (NACA-design 2 4 12) (run-constants 0 0 0 0 0)))))
