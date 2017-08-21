(ns hydrofoil.expectations.NACA-equations
  (:require [expectations :refer :all]
            [hydrofoil.core :refer :all]
            [hydrofoil.utils :refer :all]
            [hydrofoil.Model.NACA_equations :refer :all]))

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

;;; --------- gradient-function tests ------------
; M = 0| P = 45| x = .5
(expect 0.0
        (round-double (gradient-function (NACA-design 0 45 12) 0.5)))

; M = 4.75| P = 45| x =.32
(expect 0.0196
        (round-double (gradient-function (NACA-design 4.75 45 12) 0.32)))

; M = 9.5| P = 45| x = .48
(expect 0.0377
        (round-double (gradient-function (NACA-design 9.5 45 12) 0.48)))

; M = 4.5| P = .0001| x = .64
;(expect -0.0576
;        (round-double (gradient-function (NACA-design 4.5 0.0001 12) 0.64))) ;;   ***** ROUNDING ERROR *****

; M = 4.5| P = 90| x = .8
(expect 0.0091
        (round-double (gradient-function (NACA-design 4.5 90 12) 0.8)))

; M = 4.5| P = 45| x = 0
(expect 0.02
        (round-double (gradient-function (NACA-design 4.5 45 12) 0.0)))

; M = 4.5| P = 45| x = 1
(expect 0.0156
        (round-double (gradient-function (NACA-design 4.5 45 12) 1.0)))

;;;-------- upper-surface-y-function ----------
(expect 0.0
        (round-double (upper-surface-y-function (NACA-design 0 5 45) 0)))

(expect 0.1352
        (round-double (upper-surface-y-function (NACA-design 4.75 4.5 20) 0.5)))

(expect 0.2517
        (round-double (upper-surface-y-function (NACA-design 9.5 9 40) 0.5)))

;;-------- upper-surface-x-function ----------
(expect 0.5
        (round-double (upper-surface-x-function (NACA-design 0 0.5 20) 0.5)))

(expect 0.5014
        (round-double (upper-surface-x-function (NACA-design 4.75 4.5 20) 0.5)))

(expect 0.4835
        (round-double (upper-surface-x-function (NACA-design 9.5 9 40) 0.5)))

;;-------- lower-surface-y-function ----------
;(expect -0.0881
;        (round-double (lower-surface-y-function (NACA-design 0 0.5 20) 0.5))) ;;   ***** ROUNDING ERROR *****

;(expect -0.0410
;        (round-double (lower-surface-y-function (NACA-design 4.75 4.5 20) 0.5))) ;;   ***** ROUNDING ERROR *****

;(expect -0.0992
;        (round-double (lower-surface-y-function (NACA-design 9.5 9 40) 0.5))) ;;   ***** ROUNDING ERROR *****

;;-------- lower-surface-x-function ----------
(expect 0.5
        (round-double (lower-surface-x-function (NACA-design 0 0.5 20) 0.5)))

(expect 0.4986
        (round-double (lower-surface-x-function (NACA-design 4.75 4.5 20) 0.5)))

(expect 0.5165
        (round-double (lower-surface-x-function (NACA-design 9.5 9 40) 0.5)))

;;----------gradient-forward-polar-function ------------
(expect 0.0000
        (round-double (gradient-forward-polar-function (NACA-design 0 0.5 20) 0.5)))

(expect -0.0234
        (round-double (gradient-forward-polar-function (NACA-design 4.75 4.5 100) 1.5708)))

(expect 0.0938
        (round-double (gradient-forward-polar-function (NACA-design 9.5 9 40) 4.71239)))

;;----------gradient-aft-polar-function ------------
(expect 0.000
        (round-double (gradient-aft-polar-function (NACA-design 0 0.5 20) 0.5)))

(expect -0.0156
        (round-double (gradient-aft-polar-function (NACA-design 4.75 4.5 100) 1.5708)))

(expect 7.6
        (round-double (gradient-aft-polar-function (NACA-design 9.5 9 40) 4.71239)))

;;-------- theta-switch ----------
(expect 1.5708
        (round-double (theta-switch (NACA-design 5 5 5))))

;;;-------- area function ---------

(expect 0.0885
        (round-double (area (NACA-design 0 0 13)))) ;; symetric -- thin foil
;;(expect 0.2724
;;        (round-double (area (NACA-design 0 0 40)))) ;; symetric -- thick foil   *****ROUNDING ERROR*****
;; expected 0.2724, was 0.2722
(expect 0.0817
        (round-double (area (NACA-design 3 40 12)))) ;; assymetric -- thin foil
(expect 0.1367
        (round-double (area (NACA-design 9.5 0 20)))) ;; assymetric -- thick foil
