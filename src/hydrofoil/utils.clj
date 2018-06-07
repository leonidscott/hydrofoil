(ns hydrofoil.utils)

(defn NACA-design
  [max-camber position-camber thickness]
  (hash-map :C-M (/ max-camber 100)
            :C-P (/ position-camber 10)
            :C-XX (/ thickness 100)
            :M max-camber
            :P position-camber
            :XX thickness))

(defn rand-double
  "Takes a double and returns a double with four points of accuracy"
  [lower-bound upper-bound]
  (/ (double (+ (rand-int (* (- upper-bound lower-bound) 100)) (* lower-bound 100)))100))

(defn round-double
   "Takes a double and returns a double with four points of accuracy"
   [n]
   (double (/ (int (+ (* n 10000) 0.5)) 10000)))
