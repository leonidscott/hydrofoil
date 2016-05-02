(ns hydrofoil.test.model
  (:require [hydrofoil.model :refer :all]
            [expectations :refer :all]))

(expect 5
        (thickness-function (hash-map :corrected-thickness 2) 2))
