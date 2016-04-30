(ns Hydrofoil.testing
  (:require [hydrofoil_evolution.core :refer :all]
            #_ [model :refer :all]))

(expect 5
        (thickness-function (hash-map :corrected-thickness 2) 2))
