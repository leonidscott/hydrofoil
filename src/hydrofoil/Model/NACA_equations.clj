(ns hydrofoil.Model.NACA_equations
  (:require [hydrofoil.Model.Numerical_Methods :refer :all]))

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
        (* (/ (individual :C-XX) 0.2)
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
  (if (and (>= x 0) (< x (individual :C-P)))
      (* (/ (individual :C-M)
            (Math/pow (individual :C-P) 2))
         (- (* 2 (individual :C-P) x)
            (Math/pow x 2)))
      (* (/ (individual :C-M)
            (Math/pow (- 1 (individual :C-P)) 2))
         (- (+ (- 1 (* 2 (individual :C-P)))
               (* 2 (individual :C-P) x))
            (Math/pow x 2)))))

(defn gradient-function
  "Takes a corrected maximum camber, corrected camber position, and an x value.
   Returns the camber gradient at that x value. The gradiant equations are
  (2M/P^2)(P-x) for {0 <= x < p}, and (2M/(1-P)^2)(P-x) for {p <= x <= 1}"
  [individual x]
  (let [position-camber (individual :C-P)
        max-camber (individual :C-M)]
    (if (and (>= x 0) (< x position-camber))
      (* (/ (* 2 max-camber) (Math/pow position-camber 2)) (- position-camber x)) ;;; (2M/P^2)(P-x)
      (* (/ (* 2 max-camber) (Math/pow (- 1 position-camber) 2)) (- position-camber x))))) ;;; (2M/(1-P)^2)(P-x)


(defn upper-surface-x-function
  "takes an x  and gives an x coordinate on the paremetric upper-surface-x-function
  (theta =  atan(G)), x - thickness-function * sin(theta)."
  [individual x]
  (let [thickness (individual :C-XX)]
    (- x (* (thickness-function individual x)
            (Math/sin (Math/atan (gradient-function individual x)))))))


(defn lower-surface-x-function
  "takes an x  and gives an x coordinate on the paremetric lower-surface-x-function
  (theta =  atan(G)), x + thickness-function * sin(theta)."
  [individual x]
  (let [thickness (individual :C-XX)]
    (+ x (* (thickness-function individual x)
            (Math/sin (Math/atan (gradient-function individual x)))))))


(defn upper-surface-y-function
  "takes an x  and gives an y coordinate on the paremetric upper-surface-x-function
  (theta =  atan(G)), camber-function + thickness-function * cos(theta)."
  [individual x]
  (let [thickness (individual :C-XX)]
    (+ (camber-function individual x)
       (* (thickness-function individual x)
          (Math/cos (Math/atan (gradient-function individual x)))))))


(defn lower-surface-y-function
  "takes an x  and gives an y coordinate on the paremetric upper-surface-x-function
  (theta =  atan(G)), camber-function - thickness-function * cos(theta)."
  [individual x]
  (let [thickness (individual :C-XX)]
    (- (camber-function individual x)
       (* (thickness-function individual x)
          (Math/cos (Math/atan (gradient-function individual x)))))))

(defn gradient-forward-polar-function
  "takes a theta-not and gives a y coordinate. This is used in the coefficient-of-lift calculations.
  (M(-c + 2P + c*Cos(theta-not)))/P^2"
  [individual theta-not]
  (let [position-camber (individual :C-P)
        max-camber (individual :C-M)]
    (* (/ max-camber (Math/pow position-camber 2))
       (+ -1 (* 2 position-camber) (* 1 (Math/cos theta-not))))))

(defn gradient-aft-polar-function
  "takes a theta-not and gives a y coordinate. This is used in the coefficient-of-lift calculations.
  (M (-c + 2P + c*Cos(theta-not)))/(-1 + P)^2"
  [individual theta-not]
  (let [position-camber (individual :C-P)
        max-camber (individual :C-M)]
    (* (/ max-camber (Math/pow (- position-camber 1) 2))
       (+ (* -1 1) (* 2 position-camber) (* 1 (Math/cos theta-not))))))

(defn GFPCos
  [individual theta-not]
  (let [position-camber (individual :C-P)
        max-camber (individual :C-M)]
    (let [position-camber (individual :C-P)
        max-camber (individual :C-M)]
    (* (/ max-camber (Math/pow position-camber 2))
       (+ -1 (* 2 position-camber) (* 1 (Math/cos theta-not)))
       (Math/cos theta-not)))))

(defn GAPCos
  [individual theta-not]
  (let [position-camber (individual :C-P)
        max-camber (individual :C-M)]
    (* (/ max-camber (Math/pow (- position-camber 1) 2))
       (+ (* -1 1) (* 2 position-camber) (* 1 (Math/cos theta-not)))
       (Math/cos theta-not))))

(defn theta-switch
  "The angle at which the coefficient-of-lift calculation will switch from one integration to another
  arccos(1 - 2P)"
  [individual]
  (let [position-camber (individual :C-P)]
    (Math/acos (- 1 (* 2 position-camber)))))

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
