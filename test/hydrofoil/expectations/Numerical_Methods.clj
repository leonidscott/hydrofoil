(ns hydrofoil.expectations.Numerical_Methods
  (:require [expectations :refer :all]
            [hydrofoil.core :refer :all]
            [hydrofoil.utils :refer :all]
            [hydrofoil.Model.NACA_equations :refer :all]
            [hydrofoil.Model.Numerical_Methods :refer :all]))

;;;-------- riemann-sum ----------
(expect 0.0681
        (round-double (integral-abstracted (NACA-design 0 5 20) upper-surface-y-function left-rule 0 1 140)))

(expect 0.0681
        (round-double (integral-abstracted (NACA-design 0 5 20) upper-surface-y-function right-rule 0 1 140)))

(expect 0.0681
        (round-double (integral-abstracted (NACA-design 0 5 20) upper-surface-y-function middle-rule 0 1 50)))

(expect 0.0681
        (round-double (trapazoid-integral (NACA-design 0 5 20) upper-surface-y-function 0 1 140)))

(expect 0.0681
        (round-double (simpson-integral (NACA-design 0 5 20) upper-surface-y-function 0 1 80)))



(expect 0.0994
        (round-double (integral-abstracted (NACA-design 4.75 4.5 20) upper-surface-y-function left-rule 0 1 80)))

(expect 0.0994
        (round-double (integral-abstracted (NACA-design 4.75 4.5 20) upper-surface-y-function right-rule 0 1 80)))

(expect 0.0994
        (round-double (integral-abstracted (NACA-design 4.75 4.5 20) upper-surface-y-function middle-rule 0 1 195)))

(expect 0.0994
        (round-double (trapazoid-integral (NACA-design 4.75 4.5 20) upper-surface-y-function  0 1  80)))

(expect 0.0994
        (round-double (simpson-integral (NACA-design 4.75 4.5 20) upper-surface-y-function 0 1 50)))

(expect 0.0882
        (round-double (simpson-integral (NACA-design 2 4 12) gradient-forward-polar-function 0 (theta-switch (NACA-design 2 4 12)) 200)))

;;;-------- derivative ----------
(expect 1.0455
        (round-double(derivative (NACA-design 9.5 9 30) upper-surface-x-function 0.5)))
(expect 0.4877
        (round-double (upper-surface-x-function (NACA-design 9.5 9 30) 0.5)))

