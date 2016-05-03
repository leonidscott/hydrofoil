(ns hydrofoil.model
  ;;(:require [clojure.algo.generic.math-functions :as trig])
  )

(defn thickness-function
  "Take a corrected thickness and returns the function of thickess around the camber line.
  The thickness equation is (T/.2)(a-0x^.5 + a-1x + a-2x^2 + a-3x^3 + a-4x^4)"
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

(thickness-function (hash-map :corrected-thickness 40) 0)

(defn camber-function
  "Takes in a corrected maxiumum camber, corrected camber position, and an x value.
    Returns the y-coordinate of the camber at that x value. The camber equations are
  (2M/P^2)(2Px - x^2) for {0 <= x < p}, and (2M/(1-P)^2)(1 - 2P + 2Px -x^2) for {p <= x < 1}"
  [individual x]
  (if (and (> x 0) (< x (individual :corrected-position-camber)))
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
  [max-camber position-camber x]
  (if (and (> x 0) (< x position-camber))
    (* (/ (* 2 max-camber) (Math/pow position-camber 2)) (- position-camber x)) ;;; (2M/P^2)(P-x)
    (* (/ (* 2 max-camber) (Math/pow (- 1 position-camber) 2)) (- position-camber x)))) ;;; (2M/(1-P)^2)(P-x)

(defn round-double
  "Takes a double and returns a double with two points of accuracy"
  [n]
  (double (/ (int (+ (* n 10000) 0.5)) 10000)))


;;(defn upper-surface-x-function
;;  "takes an x  and gives an x coordinate on the paremetric upper-surface-x-function
;;  (theta =  atan(G)), x - thickness-function * sin(theta)."
;;  [x thickess]
;;  (- x (* (thickness-function thickess x)
;;          (Math/sin (FILL IN STUFF)))))

