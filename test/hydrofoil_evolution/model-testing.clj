(ns hydrofoil_evolution.model-testing
  (:require [hydrofoil_evolution.model :refer :all]
            [expectations :refer :all]))

(expect 5
        (thickness-function (hash-map :corrected-thickness 2) 2))
