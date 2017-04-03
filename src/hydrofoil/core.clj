(ns hydrofoil.core
  (:require [hydrofoil.evolution :refer :all]
            [hydrofoil.model :refer :all]
            [hydrofoil.utils :refer :all]))

;;[hydrofoil.evolution :as evo]

;(defn -main [& args]
 ; (println (model/atan 1)))


(defn run-constants
  [dencity velocity angle-of-attack]
  (hash-map :dencity dencity
            :velocity velocity
            :angle-of-attack angle-of-attack
            :angle-of-attack-radians (*(/ (Math/PI) 180) angle-of-attack)))


(defn hill-climber [max-tries run-constants]
  (loop [parent (rand-individual)
         num-tries 1
         performance-data '()]
    (let [child (new-indv parent)
          parent-performance (lift-function parent run-constants)
          parent-score (:lift parent-performance)
          child-score (:lift (lift-function child run-constants))
          cl (:cl parent-performance)]

      (if (>= num-tries max-tries)

        (do (print performance-data)
            parent)

        (if (> child-score parent-score)

          (if (= (mod num-tries 250) 0)
            (do (println performance-data)
                (println "")
                (recur child (inc num-tries) '() ))
            (recur child (inc num-tries) (conj performance-data (conj parent {:lift parent-score :cl cl}))))

          (recur parent (inc num-tries) performance-data)

          ;(if (= (mod num-tries 250) 0)
          ;  (do (println performance-data)
           ;     (recur parent (inc num-tries) '()))
           ; (recur parent (inc num-tries) (conj performance-data (conj parent {:lift parent-score :cl cl}))))

        )))))

(defn -main [& args]
  (hill-climber 1000 (run-constants 1 50 -10)))

(lift-function (NACA-design 5 5 12) (run-constants 1 1 5))

;(conj performance-data (conj {:generation num-tries} (conj {:lift parent-score :cl cl} parent))






      ;((conj parent {:generation num-tries :lift parent-score :cl cl}))


;(lift-function (hill-climber 20 (run-constants 1 5 5)) (run-constants 1 5 5))

  ;;(new-indv(NACA-design 50 50 50)))

;;(new-indv (NACA-design 2 4 12))

;;(NACA-design 2 4 12)

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
