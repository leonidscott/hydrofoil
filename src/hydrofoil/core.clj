(ns hydrofoil.core
  (:require [clojure.data.json :as json]
            [hydrofoil.evolution :refer :all]
            [hydrofoil.model :refer :all]
            [hydrofoil.utils :refer :all]))

(defn run-constants
  [dencity velocity angle-of-attack]
  (hash-map :dencity dencity
            :velocity velocity
            :angle-of-attack angle-of-attack
            :angle-of-attack-radians (*(/ (Math/PI) 180) angle-of-attack)))

(defn print-content [list]
    (doseq [item list]
    (println (json/write-str item))))

(defn print-csv-item [item]
  (do (doseq [element item]
        (print (key element) "," (val element) ", "))
      (println "")))

(defn print-content-csv [list]
  (doseq [item list]
    (print-csv-item item)))


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

        (do (print-content-csv performance-data)
            (println "")
            (flush)
            parent)

        (if (> child-score parent-score)

          (if (= (mod num-tries 250) 0)
            (do (print-content-csv performance-data)
                (println "")
                (flush)
                (recur child (inc num-tries) '() ))
            (recur child (inc num-tries) (conj performance-data (conj parent {:lift parent-score :cl cl}))))

          (recur parent (inc num-tries) performance-data)

          ;(if (= (mod num-tries 250) 0)
          ;  (do (println performance-data)
           ;     (recur parent (inc num-tries) '()))
           ; (recur parent (inc num-tries) (conj performance-data (conj parent {:lift parent-score :cl cl}))))

        )))))



