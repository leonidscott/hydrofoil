(ns hydrofoil-evolution.core
  (:require [hydrofoil-evolution.evolution :as evo]
            [hydrofoil-evolution.model :as model]))

(defn -main [& args]
  (println (model/atan 1)))
