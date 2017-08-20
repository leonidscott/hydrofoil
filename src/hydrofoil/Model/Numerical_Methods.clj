(ns hydrofoil.Model.Numerical_Methods)

;;;---------------- Derivative -------------------
(defn derivative
  "takes an individual, a function, and an x and returns a derivative at that point
  using numerical derivation"
  [individual function x]
  (let [resolution 0.00000001]
    (/ (- (function individual (+ x resolution))
          (function individual x))
       resolution)))


;;;---------------- Area Functions ---------------

(defn left-rule
  "Creates a lazy seque of points to evaluate a function at for a left rule riemann sum
  Takes a number of particians.
  n defines how many elements in the lazy squence
  low defines the lower bound of the range
  high defines the upper bound of the range"
  [n low high]
  (range low high (/ (- high low) n)))

(defn right-rule
  "Creates a lazy seque of points to evaluate a function at for a right rule riemann sum
  Takes a number of particians
  n defines how many elements in the lazy squence
  low defines the lower bound of the range
  high defines the upper bound of the range"
  [n low high]
  (range (/ (- high low) n) (+ high (/ (- high low) n)) (/ (- high low) n)))

(defn middle-rule
  "Creates a lazy seque of points to evaluate a function at for a right middle riemann sum
  Takes a number of particians"
  [n low high]
  (range (/ (- high low) (* 2 n)) high (/ (- high low) n)))

(defn integral-abstracted
  "Will do a riemann sum on the function:
  State will be a function, either left, right, or middle, based on the style of riemann sum.
  Pices is how boxes it makes, aka the resolution of the riemann sum"
  [individual function state low high n]
  (reduce #(+ %1 (* (/ (- high low) n) (function individual %2))) 0 (state n low high)))

(defn integral-production
  "same as riemann sum but it might be faster, you need to figure out left, right, middle
  functions on your own. ONLY WORKS FROM RANGE 0 TO 1"
  [individual function start-x end-x increment pieces]
  (reduce #(+ %1 (* (/ 1 pieces) (function individual %2))) 0 (range start-x end-x increment)))

(defn trapazoid-integral
  [individual function low high n]
  (* 0.5 (+ (integral-abstracted individual function left-rule low high n)
            (integral-abstracted individual function right-rule low high n))))

(defn simpson-integral
  [individual function low high n]
  (+ (/ (trapazoid-integral individual function low high (/ n 2)) 3)
     (/ (* 2 (integral-abstracted individual function middle-rule low high (/ n 2))) 3)))
