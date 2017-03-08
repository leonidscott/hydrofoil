(ns hydrofoil.utils)

(defn NACA-design
  [max-camber position-camber thickness]
  (hash-map :corrected-max-camber (/ max-camber 100)
            :corrected-position-camber (/ position-camber 10)
            :corrected-thickness (/ thickness 100)
            :max-camber max-camber
            :positon-camber position-camber
            :thickness thickness))

(defn rand-double
  "Takes a double and returns a double with four points of accuracy"
  [lower-bound upper-bound]
  (/ (double (+ (rand-int (* (- upper-bound lower-bound) 100)) (* lower-bound 100)))100))
