(ns hydrofoil.Model.Lifting_Line_Theory
  (:require [hydrofoil.Model.Thin_Aerofoil_Theory :as TAT]))

(defn S
  "eliptical area: S = (Pi * b * c)/4"
  [run-constants]
  (/ (* (Math/PI) (:span run-constants) (:root-chord run-constants)) 4))

(defn aspect-ratio
  "AR(b, cR) = (4b^2)/(pi b Cr)"
  [run-constants]
  (let [span (run-constants :span)
        root-chord (run-constants :root-chord)]
    (/ (* 4 span) (* (Math/PI) root-chord))))

(defn zero-AOT
  "Computes the zero angle of attack for an individual using the cl from Thin Aerofoil Theory
  aL0 = 2 Pi a - cl"
  [individual run-constants]
  (- (* 2 (Math/PI) (:angle-of-attack-radians run-constants)) (:cl (TAT/coefficient-of-lift individual run-constants))))

(defn CL-ThreeD
  "CL = (2 Pi AR (AOT - zero-AOT)) / (AOT + 2)"
  [individual run-constants]
    (/ (* 2 (Math/PI) (aspect-ratio run-constants) (- (:angle-of-attack-radians run-constants) (zero-AOT individual run-constants)))
       (+ (aspect-ratio run-constants) 2)))

(defn CD-Induced
  "CDI = CL^2 / (Pi AR)"
  [CL run-constants]
  (/ (Math/pow CL 2) (* (Math/PI) (aspect-ratio run-constants))))

(defn lift
  "L = (1/2) CL p V^2 S"
  [CL run-constants]
  (* 0.5 CL (:dencity run-constants) (Math/pow (:velocity run-constants) 2) (S run-constants)))

(defn drag
  "D = (1/2) CD p V^2 S"
  [CD run-constants]
  (* 0.5 CD (:dencity run-constants) (Math/pow (:velocity run-constants) 2) (S run-constants)))

(defn Lifting-Line-Theory
  "Takes an individual and its run constants, gives back:
  Lift, Drag, CL-3D, CD-Induced, cl-2D"
  [individual run-constants]
  (let [CL (CL-ThreeD individual run-constants)
        CD (CD-Induced CL run-constants)
        lift (lift CL run-constants)
        drag (drag CD run-constants)
        cl (:cl (TAT/coefficient-of-lift individual run-constants))]
    (conj individual
          {:Lift lift
           :Drag drag
           :CL-3D CL
           :CD-Induced CD
           :cl cl})))


