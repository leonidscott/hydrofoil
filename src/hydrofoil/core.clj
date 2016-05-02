(ns hydrofoil.core
  (:require [hydrofoil.evolution :as evo]
            [hydrofoil.model :as model]))

;(defn -main [& args]
 ; (println (model/atan 1)))

(defn NACA-design
  [max-camber position-camber thickness]
  (hash-map :corrected-max-camber (/ max-camber 100)
            :corrected-position-camber (/ position-camber 10)
            :corrected-thickness (/ thickness 100)))

