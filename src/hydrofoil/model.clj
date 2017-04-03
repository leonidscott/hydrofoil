(ns hydrofoil.model
  (require [hydrofoil.utils :refer :all])
  ;;(:require [clojure.algo.generic.math-functions :as trig])
  )


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

(defn gradient-forward-polar-function
  "takes a theta-not and gives a y coordinate. This is used in the coefficient-of-lift calculations.
  (M(-c + 2P + c*Cos(theta-not)))/P^2"
  [individual theta-not]
  (let [position-camber (individual :corrected-position-camber)
        max-camber (individual :corrected-max-camber)]
    (* (/ max-camber (Math/pow position-camber 2))
       (+ -1 (* 2 position-camber) (* 1 (Math/cos theta-not))))))

(defn gradient-aft-polar-function
  "takes a theta-not and gives a y coordinate. This is used in the coefficient-of-lift calculations.
  (M (-c + 2P + c*Cos(theta-not)))/(-1 + P)^2"
  [individual theta-not]
  (let [position-camber (individual :corrected-position-camber)
        max-camber (individual :corrected-max-camber)]
    (* (/ max-camber (Math/pow (- position-camber 1) 2))
       (+ (* -1 1) (* 2 position-camber) (* 1 (Math/cos theta-not))))))

(defn GFPCos
  [individual theta-not]
  (let [position-camber (individual :corrected-position-camber)
        max-camber (individual :corrected-max-camber)]
    (let [position-camber (individual :corrected-position-camber)
        max-camber (individual :corrected-max-camber)]
    (* (/ max-camber (Math/pow position-camber 2))
       (+ -1 (* 2 position-camber) (* 1 (Math/cos theta-not)))
       (Math/cos theta-not)))))

(defn GAPCos
  [individual theta-not]
  (let [position-camber (individual :corrected-position-camber)
        max-camber (individual :corrected-max-camber)]
    (* (/ max-camber (Math/pow (- position-camber 1) 2))
       (+ (* -1 1) (* 2 position-camber) (* 1 (Math/cos theta-not)))
       (Math/cos theta-not))))

(defn theta-switch
  "The angle at which the coefficient-of-lift calculation will switch from one integration to another
  arccos(1 - 2P)"
  [individual]
  (let [position-camber (individual :corrected-position-camber)]
    (Math/acos (- 1 (* 2 position-camber)))))


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

(defn adjusted-upper-function
  "   /////   upper-surface-y-function * d/dx(upper-surface-x-function) /////
  This exists as a helper function for 'area' because the way the integral functions work,
  you can't pass the multiplication of two functions without creating a helper function that
  does that... so that's the only reason this is here."
  [individual x]
  (* (upper-surface-y-function individual x)
     (derivative individual upper-surface-x-function x)))

(defn adjusted-lower-function
  "   /////   lower-surface-y-function * d/dx(lower-surface-x-function) /////
  This exists as a helper function for 'area' because the way the integral functions work,
  you can't pass the multiplication of two functions without creating a helper function that
  does that... so that's the only reason this is here."
  [individual x]
  (* (lower-surface-y-function individual x)
     (derivative individual lower-surface-x-function x)))

(defn area
  "  ///// Integral[upper-surface-y-function * d/dx(upper-surface-x-function)
            - lower-surface-y-function * d/dx(lower-surface-x-function),
            {0, 1}] /////
  The integral multiplies by the derivative of the x function because the functions are
  paremetric."
  [individual]
  (- (simpson-integral individual adjusted-upper-function 0 1 90)
     (simpson-integral individual adjusted-lower-function 0 1 90)))

;;;---------------- Coefficantof lift ----------------
(defn symetric-coefficient-of-lift
  [individual run-constants]
  (let [attack-angle (run-constants :angle-of-attack-radians)
        camber-position (individual :corrected-position-camber)]
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
  (let [max-camber (individual :max-camber)]
    (conj individual {:cl (if (== max-camber 0)
      (symetric-coefficient-of-lift individual run-constants)
      (cambered-coefficient-of-lift individual run-constants))})))



;;; **---------------- LIFT EQUATION ----------------**
(defn lift-function
  [individual run-constants]
  (let [dencity (run-constants :dencity)
        velocity (run-constants :velocity)
        angle-of-attack (run-constants :angle-of-attack)
        cl (:cl (coefficient-of-lift individual run-constants))]

    (conj individual
          { :cl cl
            :lift (* cl
                   (/ (* dencity (Math/pow velocity 2)) 2)
                   (area individual))})))
;;; **---------------- LIFT EQUATION ----------------**

(lift-function (NACA-design 2 4 12) (hash-map :dencity 1 :velocity 5 :angle-of-attack 5 :angle-of-attack-radians 0.0872665))

;;(integral-abstracted (hash-map :corrected-max-camber 0 :corrected-position-camber 0 :corrected-thickness 0.45) upper-surface-y-function left-rule 100)

;;(upper-surface-y-function (hash-map :corrected-max-camber 0 :corrected-position-camber 0.5 :corrected-thickness 0.45) 0)

(* (integral-production (NACA-design 2 4 12) gradient-forward-polar-function 0 1.369 (/ 1.369 200) 200) 1.369)

(integral-abstracted (NACA-design 2 4 12) gradient-forward-polar-function middle-rule 0 (theta-switch (NACA-design 2 4 12)) 200)

(trapazoid-integral (NACA-design 2 4 12) gradient-forward-polar-function 0 (theta-switch (NACA-design 2 4 12)) 10000)

(simpson-integral (NACA-design 2 4 12) gradient-forward-polar-function 0 (theta-switch (NACA-design 2 4 12)) 1000)

(integral-abstracted (NACA-design 2 4 12) gradient-aft-polar-function left-rule (theta-switch (NACA-design 2 4 12)) (Math/PI) 200)

(simpson-integral (NACA-design 2 4 12) gradient-aft-polar-function (theta-switch (NACA-design 2 4 12)) (Math/PI) 200)



