(ns hydrofoil.expectations.utils
  (:require [expectations :refer :all]
            [hydrofoil.utils :refer :all]))

;;; --------- Utils Tests ------------

;; round-double tests
; Four Digits or less
(expect 0.1000
        (round-double 0.1000))
(expect 0.9999
        (round-double 0.9999))

; Over four digits
(expect 0.1
        (round-double 0.100001))
(expect 1.0
        (round-double 0.99999))

;; rand-double tests
(expect true
        (<= 0.0 (rand-double 0 1) 1))
(expect true
        (<= 0.5 (rand-double 0.5 12) 12))
(expect true
        (<= -1.0 (rand-double -1.0 1.0) 1.0))
(expect 0.0
        (rand-double 0 0))
