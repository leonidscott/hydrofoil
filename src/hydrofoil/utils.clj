(ns hydrofoil.utils)

(defn NACA-design
  [max-camber position-camber thickness]
  {:C-M (/ max-camber 100)
   :C-P (/ position-camber 10)
   :C-XX (/ thickness 100)
   :M max-camber
   :P position-camber
   :XX thickness})

(defn round-double-2
  "Takes a POSITIVE double and returns a double with four points of accuracy"
  [n]
  (double (/ (int (+ (* n 10000)
                     0.5))
             10000)))

(defn round-double
  "Takes a POSITIVE double and returns a double with four points of accuracy
   Only used for clean results during testing, or presentation."
  [n]
  (-> n
      (* 10000)
      (+ 0.5)
      (int)
      (/ 10000)
      (double)))

(defn rand-double
  "Produces a random double with four digits of accuarcy between the upper and lower bound.
   Used when creating new individuals in evolution.clj"
  [lower-bound upper-bound]
  (-> (- upper-bound lower-bound)
      (rand)
      (+ lower-bound)))
