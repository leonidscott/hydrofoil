(ns hydrofoil.expectations.model
  (:require [expectations :refer :all]
            [hydrofoil.model :refer :all]))

;;; --------- thickness-function tests ------------
; thickness 0:
(expect 0.00
        (round-double (thickness-function (hash-map :corrected-thickness 1) 0)))
(expect 0.00
        (round-double (thickness-function (hash-map :corrected-thickness 40) 0)))

;thickness 10:
(expect 0.0359
        (round-double (thickness-function (hash-map :corrected-thickness 0.10) 0.08)))
(expect 0.0453
        (round-double (thickness-function (hash-map :corrected-thickness 0.10) (* 0.08 2))))
(expect 0.0493
        (round-double (thickness-function (hash-map :corrected-thickness 0.10) (* 0.08 3))))

;thickness 20:
(expect 0.0999
        (round-double (thickness-function (hash-map :corrected-thickness 0.20) (* 0.08 4))))
(expect 0.0967
        (round-double (thickness-function (hash-map :corrected-thickness 0.20) (* 0.08 5))))
(expect 0.0902
        (round-double (thickness-function (hash-map :corrected-thickness 0.20) (* 0.08 6))))

;thickness 30:
(expect 0.0643
        (round-double (thickness-function (hash-map :corrected-thickness 0.3) (* 0.08 10))))

;thickness 40:
(expect 0.0
        (round-double (thickness-function (hash-map :corrected-thickness 0.4) 0.9999)))

;;; --------- camber-function tests ------------
