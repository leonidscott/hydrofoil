(ns hydrofoil.model
  ;;(:require [clojure.algo.generic.math-functions :as trig])
  )

;;---------------- Utilities ---------------
(defn round-double
  "Takes a double and returns a double with four points of accuracy"
  [n]
  (double (/ (int (+ (* n 10000) 0.5)) 10000)))


;;---------------- NACA Functions ---------------
(defn thickness-function
  "Take a corrected thickness and returns the function of thickess around the camber line.
  The thickness equation is (T/.2)(a-0x^.5 + a-1x + a-2x^2 + a-3x^3 + a-4x^4)
  NOTE: Does not work for x = 1"
  [individual x]
  (let [a-0 0.2969
        a-1 -0.126
        a-2 -0.3516
        a-3 0.2843
        a-4 -0.1036]
        (* (/ (individual :corrected-thickness) 0.2)
           (+ (* a-0 (Math/sqrt x))
              (* a-1 x)
              (* a-2 (Math/pow x 2))
              (* a-3 (Math/pow x 3))
              (* a-4 (Math/pow x 4))))))

(defn camber-function
  "Takes in a corrected maxiumum camber, corrected camber position, and an x value.
    Returns the y-coordinate of the camber at that x value. The camber equations are
  (2M/P^2)(2Px - x^2) for {0 <= x < p}, and (2M/(1-P)^2)(1 - 2P + 2Px -x^2) for {p <= x < 1}
  NOTE: Does not work for P = 0"
  [individual x]
  (if (and (>= x 0) (< x (individual :corrected-position-camber)))
      (* (/ (individual :corrected-max-camber)
            (Math/pow (individual :corrected-position-camber) 2))
         (- (* 2 (individual :corrected-position-camber) x)
            (Math/pow x 2)))
      (* (/ (individual :corrected-max-camber)
            (Math/pow (- 1 (individual :corrected-position-camber)) 2))
         (- (+ (- 1 (* 2 (individual :corrected-position-camber)))
               (* 2 (individual :corrected-position-camber) x))
            (Math/pow x 2)))))

(defn gradient-function
  "Takes a corrected maximum camber, corrected camber position, and an x value.
   Returns the camber gradient at that x value. The gradiant equations are
  (2M/P^2)(P-x) for {0 <= x < p}, and (2M/(1-P)^2)(P-x) for {p <= x <= 1}"
  [individual x]
  (let [position-camber (individual :corrected-position-camber)
        max-camber (individual :corrected-max-camber)]
    (if (and (>= x 0) (< x position-camber))
      (* (/ (* 2 max-camber) (Math/pow position-camber 2)) (- position-camber x)) ;;; (2M/P^2)(P-x)
      (* (/ (* 2 max-camber) (Math/pow (- 1 position-camber) 2)) (- position-camber x))))) ;;; (2M/(1-P)^2)(P-x)


(defn upper-surface-x-function
  "takes an x  and gives an x coordinate on the paremetric upper-surface-x-function
  (theta =  atan(G)), x - thickness-function * sin(theta)."
  [individual x]
  (let [thickness (individual :corrected-thickness)]
    (- x (* (thickness-function individual x)
            (Math/sin (Math/atan (gradient-function individual x)))))))


(defn lower-surface-x-function
  "takes an x  and gives an x coordinate on the paremetric lower-surface-x-function
  (theta =  atan(G)), x + thickness-function * sin(theta)."
  [individual x]
  (let [thickness (individual :corrected-thickness)]
    (+ x (* (thickness-function individual x)
            (Math/sin (Math/atan (gradient-function individual x)))))))


(defn upper-surface-y-function
  "takes an x  and gives an y coordinate on the paremetric upper-surface-x-function
  (theta =  atan(G)), camber-function + thickness-function * cos(theta)."
  [individual x]
  (let [thickness (individual :corrected-thickness)]
    (+ (camber-function individual x)
       (* (thickness-function individual x)
          (Math/cos (Math/atan (gradient-function individual x)))))))


(defn lower-surface-y-function
  "takes an x  and gives an y coordinate on the paremetric upper-surface-x-function
  (theta =  atan(G)), camber-function - thickness-function * cos(theta)."
  [individual x]
  (let [thickness (individual :corrected-thickness)]
    (- (camber-function individual x)
       (* (thickness-function individual x)
          (Math/cos (Math/atan (gradient-function individual x)))))))

;;;---------------- Area Functions ---------------

(defn left-rule
  "Creates a lazy seque of points to evaluate a function at for a left rule riemann sum
  Takes a number of particians"
  [pieces]
  (range 0 1 (/ 1 pieces)))

(defn right-rule
  "Creates a lazy seque of points to evaluate a function at for a right rule riemann sum
  Takes a number of particians"
  [pieces]
  (range (/ 1 pieces) (+ (/ 1 pieces) 1) (/ 1 pieces)))

(defn middle-rule
  "Creates a lazy seque of points to evaluate a function at for a right middle riemann sum
  Takes a number of particians"
  [pieces]
  (range (/ 1 (* 2 pieces))
         1
         (/ 1 pieces)))

(defn integral-abstracted
  "Will do a riemann sum on the function:
  State will be a function, either left, right, or middle, based on the style of riemann sum.
  Pices is how boxes it makes, aka the resolution of the riemann sum"
  [individual function state pieces]
  (reduce #(+ %1 (* (/ 1 pieces) (function individual %2))) 0 (state pieces)))

(defn integral-production
  "same as riemann sum but if might be faster, you need to figure our left, right, middle
  functions on your own"
  [individual function start-x end-x increment pieces]
  (reduce #(+ %1 (* (/ 1 pieces) (function individual %2))) 0 (range start-x end-x increment)))

(defn trapazoid-integral
  [individual function pieces]
  (* 0.5 (+ (integral-abstracted individual function left-rule pieces)
            (integral-abstracted individual function right-rule pieces))))

(defn simpson-integral
  [individual function pieces]
  (+ (/ (trapazoid-integral individual function (/ pieces 2)) 3)
     (/ (* 2 (integral-abstracted individual function middle-rule (/ pieces 2))) 3)))

(defn area
  [individual]
  (- (simpson-integral individual upper-surface-y-function 90)
     (simpson-integral individual lower-surface-y-function 90)))

;;;---------------- Coefficantof lift ----------------
(defn coefficient-of-lift
  [individual run-constants]
  (let [attack-angle (run-constants :angle-of-attack)
        camber-position (individual :corrected-position-camber)]
    (* (* 2 (Math/PI))
       (+ attack-angle (* 2 camber-position)))))




;;; **---------------- LIFT EQUATION ----------------**
(defn lift-function
  [individual run-constants]
  (let [dencity (run-constants :dencity dencity)
        velocity (run-constants :velocity velocity)
        angle-of-attack (run-constants :angle-of-attack angle-of-attack)]
    (* (coefficient-of-lift individual run-constants)
       (/ (* dencity (Math/pow velocity 2)) 2)
       (area individual))))
;;; **---------------- LIFT EQUATION ----------------**
;;(integral-abstracted (hash-map :corrected-max-camber 0 :corrected-position-camber 0 :corrected-thickness 0.45) upper-surface-y-function left-rule 100)

;(upper-surface-y-function (hash-map :corrected-max-camber 0 :corrected-position-camber 0.5 :corrected-thickness 0.45) 0)






