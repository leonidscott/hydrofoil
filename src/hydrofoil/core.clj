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

(defn run-constants
  [dencity velocity angle-of-attack]
  (hash-map :dencity dencity
            :velocity velocity
            :angle-of-attack angle-of-attack))


;;(defn generate-random-design
;;  [individual]
;;  (NACA-design (round-double (rand 9.5)) (round-double (rand 90)) (+ (round-double (rand 39)) 1)))


;;(defn random-change
;;  [individual]
;;  (def changeVector (vector (rand-int 2) (rand-int 2) (rand-int 2) )))


;;(defn score
;;  [individual]
;;  (assoc lift :score (score lift)))


;;(defn make-random-change
;;  [random-change]
;;  (if (= (get changeVector 1) 1) change-max-camber)
;;  (if (= (get changeVector 2) 1) change-position-camber)
;;  (if (= (get changeVector 3) 1) change-thickness))


;;(defn change-max-camber
;;  [individual]
;;  (if (> (+ max-camber (round-double (rand 1))) 9.5) (% max-camber 9.5) (max-camber)))


;;(defn change-position-camber
;;  [individual]
;;  (if (> (+ position-camber (round-double (rand 9))) 90) (% position-camber 90) (position-camber)))


;;(defn change-thickness
;;  [individual]
;;  (if (> (+ thickness (round-double (rand 4))) 40) (+ (% thickness 40) 1) (thickness)))
