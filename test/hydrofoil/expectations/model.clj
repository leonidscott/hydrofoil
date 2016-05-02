(ns hydrofoil.expectations.model
  (:require [expectations :refer :all]
            [hydrofoil.model :refer :all]))

(expect 5
        (thickness-function (hash-map :corrected-thickness 2) 2))
