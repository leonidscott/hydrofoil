(ns hydrofoil.Model.Thin_Aerofoil_Theory
  (:require [hydrofoil.Model.NACA_equations :refer :all]
            [hydrofoil.Model.Numerical_Methods :refer :all]))

;;;---------------- Coefficant of lift ----------------
(defn symetric-coefficient-of-lift
  [individual run-constants]
  (let [attack-angle (run-constants :angle-of-attack-radians)
        camber-position (individual :C-P)]
    (* 2 Math/PI attack-angle)))

(defn A-0
  [individual run-constants]
  (let [attack-angle (run-constants :angle-of-attack-radians)
        theta-switch-constant (theta-switch individual)]
    (- attack-angle
       (* (/ 1 Math/PI)
          (+ (integral-abstracted individual gradient-forward-polar-function middle-rule 0 theta-switch-constant 600)
             (integral-abstracted individual gradient-aft-polar-function left-rule theta-switch-constant Math/PI 600))))))

(defn A-1
  [individual run-constants]
  (let [theta-switch-constant (theta-switch individual)]
    (* (/ 2 Math/PI)
       (+ (integral-abstracted individual GFPCos middle-rule 0 theta-switch-constant 850)
          (integral-abstracted individual GAPCos left-rule theta-switch-constant Math/PI 850)))))


(defn cambered-coefficient-of-lift
  [individual run-constants]
  (+ (* 2 (Math/PI) (A-0 individual run-constants))
     (* (Math/PI) (A-1 individual run-constants))))

(defn coefficient-of-lift
  [individual run-constants]
  (let [max-camber (individual :M)]
    (conj individual {:cl (if (== max-camber 0)
      (symetric-coefficient-of-lift individual run-constants)
      (cambered-coefficient-of-lift individual run-constants))})))

;;; **---------------- LIFT EQUATION ----------------**
(defn thin-aerofoil-theory
  [individual run-constants]
  (let [dencity (run-constants :dencity)
        velocity (run-constants :velocity)
        angle-of-attack (run-constants :angle-of-attack)
        cl (:cl (coefficient-of-lift individual run-constants))]

    (conj individual
          { :cl cl
            :Lift (* cl
                   (/ (* dencity (Math/pow velocity 2)) 2)
                   (area individual))})))
;;; **---------------- LIFT EQUATION ----------------**
