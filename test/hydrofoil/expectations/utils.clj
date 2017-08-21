(ns hydrofoil.expectations.utils
  (:require [expectations :refer :all]
            [hydrofoil.core :refer :all]
            [hydrofoil.evolution :refer :all]))

;;; --------- Utils Tests ------------

(expect 2
        (+ 1 1))
