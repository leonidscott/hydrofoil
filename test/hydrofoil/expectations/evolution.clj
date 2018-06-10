(ns hydrofoil.expectations.evolution
  (:require [expectations :refer :all]
            [hydrofoil.core :refer :all]
            [hydrofoil.evolution :refer :all]
            [hydrofoil.utils :refer :all]))

(expect true
  (let [child (new-indv (NACA-design 2 4 12))]
    (and (<= (child :M) 22) (>= (child :M 2)))))

(expect true
  (let [child (new-indv (NACA-design 2 4 12))]
    (and (<= (child :P) 9) (>= (child :P 0)))))

(expect true
  (let [child (new-indv (NACA-design 2 4 12))]
    (and (<= (child :XX) 22) (> (child :XX 2)))))

(expect nil
        (some #(and (> (% :M) 100) (< (% :M) 0)) (take 100 (iterate new-indv (NACA-design 2 4 12)))))

(expect nil
        (some #(and (> (% :P) 10) (< (% :P) 0)) (take 100 (iterate new-indv (NACA-design 2 4 12)))))

(expect nil
        (some #(and (> (% :XX) 100) (< (% :XX) 0)) (take 100 (iterate new-indv (NACA-design 2 4 12)))))

;;; ---------------------- p-min -----------------------------
(expect 0.0100
        (round-double (p-min 0)))
(expect 0.2194
        (round-double (p-min 0.1)))
(expect 0.8796
        (round-double (p-min 10)))
(expect 2.8578
        (round-double (p-min 19.99)))
(expect 2.79
        (round-double (p-min 20)))
(expect 2.79
        (round-double (p-min 35)))
(expect 2.79
        (round-double (p-min 100)))
;;; ---------------------- p-min -----------------------------

;;; ---------------------- thin-aerofoil-scoring -----------------------------
(expect 541.1701
        (round-double (:score (thin-aerofoil-scoring (NACA-design 2 4 12) (run-constants 1 100 10 0 0) 1))))
;;; ---------------------- thin-aerofoil-scoring -----------------------------


;;; ---------------------- lift-priority-scoring -----------------------------
(expect 995544.5847414348
        (:score (lift-priority-scoring (NACA-design 2 4 12) (run-constants 1 100 5 2 7) 200)))
(expect 0
        (:score (lift-priority-scoring (NACA-design 2 4 12) (run-constants 1 100 5 2 7) 200000)))
;;; ---------------------- lift-priority-scoring -----------------------------


;;; ---------------------- drag-priority-scoring -----------------------------
(expect 0
        (:score (drag-priority-scoring (NACA-design 2 4 12) (run-constants 1 100 5 2 7) 100)))
(expect 16731.4673
        (round-double (:score (drag-priority-scoring (NACA-design 2 4 12) (run-constants 1 100 5 2 7) 20000))))



