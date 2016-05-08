(ns hydrofoil.core
  (:require [hydrofoil.evolution :as evo]
            [hydrofoil.model :refer :all]))

;(defn -main [& args]
 ; (println (model/atan 1)))

(defn NACA-design
  [max-camber position-camber thickness]
  (hash-map :corrected-max-camber (/ max-camber 100)
            :corrected-position-camber (/ position-camber 10)
            :corrected-thickness (/ thickness 100)
            :max-camber max-camber
            :positon-camber position-camber
            :thickness thickness))


(defn generate-random-design
  []
  (NACA-design (round-double (rand 9.5)) (round-double (rand 90)) (+ (round-double (rand 39)) 1)))


(defn random-change
  []
  (def changeVector (vector (rand-int 2) (rand-int 2) (rand-int 2) )))


(defn score
  [lift]
  (assoc lift :score (score lift)))


(defn make-random-change
  [random-change]
  (if (= (get changeVector 1) 1) change-max-camber)
  (if (= (get changeVector 2) 1) change-position-camber)
  (if (= (get changeVector 3) 1) change-thickness))


(defn change-max-camber
  []
  (if (> (+ max-camber (round-double (rand 1))) 9.5) ))


