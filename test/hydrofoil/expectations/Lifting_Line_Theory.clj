(ns hydrofoil.expectations.Lifting_Line_Theory
   (:require [expectations :refer :all]
             [hydrofoil.core :refer :all]
             [hydrofoil.utils :refer :all]
             [hydrofoil.Model.Lifting_Line_Theory :refer :all]))

;;; --------- Aspect Ratio ------------
(expect 10.9956 (round-double (S (run-constants 1 1 1 2 7))))

(expect 1.2732 (round-double (aspect-ratio (run-constants 1 1 1 5 5))))

(expect 0.3638 (round-double (aspect-ratio (run-constants 1 1 1 2 7))))


;;; --------- Zero Angle of Attack ------------
(expect -0.2274
        (round-double (zero-AOT (NACA-design 2 4 12) (run-constants 0 0 0 0 0))))

;same foil at 5 degrees AOT| cl = .7758
(expect -0.2274
        (round-double (zero-AOT (NACA-design 2 4 12) (run-constants 0 0 5 0 0))))

;;; --------- CL ------------
(expect 0.3043
        (round-double (CL-ThreeD (NACA-design 2 4 12) (run-constants 1 100 5 2 7))))

;;; --------- CD ------------
(expect 0.0810
        (round-double (CD-Induced (CL-ThreeD (NACA-design 2 4 12) (run-constants 1 100 5 2 7)) (run-constants 1 100 5 2 7))))

;;; --------- Lift and Drag ------------
(expect 16729.7663
        (round-double (lift 0.3043 (run-constants 1 100 5 2 7))))

(expect 4453.2076
        (round-double (drag 0.0810 (run-constants 1 100 5 2 7))))

(Lifting-Line-Theory (NACA-design 2 4 12) (run-constants 1 100 5 2 7))

(Lifting-Line-Theory (NACA-design 2 4 500) (run-constants 1 100 5 2 7))
