(ns hydrofoil.expectations.model
  (:require [expectations :refer :all]
            [hydrofoil.model :refer :all]
            [hydrofoil.core :refer :all]))

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
; M = 0| P = 45| x = .5
(expect 0.0
        (round-double (camber-function (NACA-design 0 45 12) 0.5)))

; M = 4.75| P = 45| x = .32
(expect 0.0065
        (round-double (camber-function (NACA-design 4.75 45 12) 0.32)))

; M = 9.5| P = 45| x = .48
(expect 0.0192
        (round-double (camber-function (NACA-design 9.5 45 12) 0.48)))

; M = 4.75| P = .0001| x = .64
(expect 0.0366
        (round-double (camber-function (NACA-design 4.75 0.0001 12) 0.48)))

; M = 4.75| P = 90| x = .8
(expect 0.0081
        (round-double (camber-function (NACA-design 4.75 90 12) 0.8)))

; M = 4.75| P = 30 | x = .96
(expect 0.0255
        (round-double (camber-function (NACA-design 4.75 30 12) 0.96)))

; M = 4.75| P = 45| x = 1
(expect 0.0188
        (round-double (camber-function (NACA-design 4.75 45 12) 1)))

; M = 4.75| P = 45| x = 0
(expect 0.0
        (round-double (camber-function (NACA-design 4.75 45 12) 0)))
; M = 4.75| P = 9| x = .09
(expect 0.0475
        (round-double (camber-function (NACA-design 4.75 9 12) 0.9)))
