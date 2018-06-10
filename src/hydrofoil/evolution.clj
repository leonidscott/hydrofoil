(ns hydrofoil.evolution
  (:require [hydrofoil.utils :refer :all]
            [hydrofoil.Model.Thin_Aerofoil_Theory :as TAT]
            [hydrofoil.Model.Lifting_Line_Theory :as LLT]))


(defn upper-bound-check
  [exploratory-limit upper-bound]
  (if (> exploratory-limit upper-bound)
    upper-bound
    exploratory-limit))

(defn lower-bound-check
  [exploratory-limit lower-bound]
  (if (< exploratory-limit lower-bound)
    lower-bound
    exploratory-limit))

(defn p-min
  [t]
  (cond
    (and (> t 0) (< t 20)) (+ 0.21934298928655427 (* 0.006602765526534473 (Math/pow t 2)))
    ( = t 0) 0.0100
    :else 2.79 ))

(defn new-indv
  [parent]
  (let [exploration-factor 30 ;;; Doesn't do anything yet. hopefully will change m, p, and t, exp-factors.
        ;;; how far you can go from the parent value
        m-exp-factor 10
        p-exp-factor 10
        t-exp-factor 10
        ;;; the numerical values of the upper limits
        m-upper-bound (upper-bound-check (+ m-exp-factor (parent :M)) 9.5)
        p-upper-bound (upper-bound-check (+ p-exp-factor (parent :P)) 9)
        t-upper-bound (upper-bound-check (+ t-exp-factor (parent :XX)) 40)
        ;;; the numerical value of the lower limits
        m-lower-bound (lower-bound-check (- (parent :M) m-exp-factor) 0)
        ;p-lower-bound (lower-bound-check (- (parent :P) p-exp-factor) 0)
        t-lower-bound (lower-bound-check (- (parent :XX) t-exp-factor) 0)

        t-value (rand-double t-lower-bound t-upper-bound)
        p-lower-bound (lower-bound-check (- (parent :P) p-exp-factor) (p-min t-value))]

    (NACA-design (rand-double m-lower-bound m-upper-bound)
                 (rand-double p-lower-bound p-upper-bound)
                 t-value)))


(defn rand-individual
  []
  (let [t-value (rand-double 0 40)]
    (NACA-design (rand-double 0 9.5)
                 (rand-double (p-min t-value) 9)
                 t-value)))

;;; ------------ SCORING --------------
(defn thin-aerofoil-scoring
  "Most rudimentary scoring, uses thin aerofoil theory, and just returns lift"
  [individual run-constants scoring-arg]
  (let    [individual-performance (TAT/thin-aerofoil-theory individual run-constants)
           score (:Lift individual-performance)]
    (conj individual-performance
          {:score score})))

(defn lift-priority-scoring
  "Using LLT: Inputs will include a minimum lift, foils not achieving this lift will recieve a score of 0.
  If a foil, reaches the lift threashold, the score will be 1,000,000 - drag. Individuals will want a HIGH score. "
  [individual run-constants min-lift]
  (let [individual-performance (LLT/Lifting-Line-Theory individual run-constants)]
    (if (> (:Lift individual-performance) min-lift)
            (conj individual-performance {:score (- 1000000 (:Drag individual-performance))})
            (conj individual-performance {:score 0})
      )))

(defn drag-priority-scoring
  "Using LLT: Inputs will include a maximum drag, foils not achieving this maximum drag will recieve a score of 0.
  If a foil, reaches the lift threashold, the score will be simply its lift. Individuals will want a HIGH score."
  [individual run-constants max-drag]
  (let [individual-performance (LLT/Lifting-Line-Theory individual run-constants)]
    (if (< (:Drag individual-performance) max-drag)
            (conj individual-performance {:score (:Lift individual-performance)})
            (conj individual-performance {:score 0}))))

(defn linear-combination-scoring
  "Using LLT: No extra input, lift and drag will be weighted with constants, and summed."
  [individual run-constants]
  (let [lift-weight 0.5
        drag-weight 3
        individual-performance (LLT/Lifting-Line-Theory individual run-constants)]
    (conj individual-performance {:score (+ (* lift-weight (:Lift individual-performance))
                                            (* drag-weight (:Drag individual-performance)))})))







