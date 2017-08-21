(ns hydrofoil.core
  (:require [clojure.data.json :as json]
            [hydrofoil.evolution :refer :all]
            [hydrofoil.utils :refer :all]
            [hydrofoil.Model.Thin_Aerofoil_Theory :as TAT]))

(defn run-constants
  [dencity velocity angle-of-attack span root-chord]
  (hash-map :dencity dencity
            :velocity velocity
            :angle-of-attack angle-of-attack
            :angle-of-attack-radians (*(/ (Math/PI) 180) angle-of-attack)
            :span span
            :root-chord root-chord))

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


(defn hill-climber [max-tries run-constants scoring-function scoring-arg]
  (loop [parent (rand-individual)
         num-tries 1
         performance-data '()]
    (let [child (new-indv parent)
          parent-performance (scoring-function parent run-constants scoring-arg)
          parent-score (:score parent-performance)
          child-score (:score (scoring-function child run-constants scoring-arg))]

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
            (recur child (inc num-tries) (conj performance-data (conj parent {:Lift (:Lift parent-performance)
                                                                              :Drag (:Drag parent-performance)
                                                                              :CL-3D (:CL-3D parent-performance)
                                                                              :CD-Induced (:CD-Induced parent-performance)
                                                                              :score parent-score
                                                                              :cl-2D (:cl parent-performance)
                                                                              :generation num-tries}))))

          (recur parent (inc num-tries) performance-data)

          ;(if (= (mod num-tries 250) 0)
          ;  (do (println performance-data)
           ;     (recur parent (inc num-tries) '()))
           ; (recur parent (inc num-tries) (conj performance-data (conj parent {:lift parent-score :cl cl}))))

        )))))

(defn -main [angle-of-attack span root-chord scoring-arg]
  (hill-climber 10000 (run-constants 1 50 (Integer/parseInt angle-of-attack) (Integer/parseInt span) (Integer/parseInt root-chord)) drag-priority-scoring (Integer/parseInt scoring-arg)))
