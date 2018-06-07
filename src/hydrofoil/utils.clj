(ns hydrofoil.utils)

(defn NACA-design
  [max-camber position-camber thickness]
  {:C-M (/ max-camber 100)
   :C-P (/ position-camber 10)
   :C-XX (/ thickness 100)
   :M max-camber
   :P position-camber
   :XX thickness})

(defn round-double
   "Takes a double and returns a double with four points of accuracy"
   [n]
  (double (/ (int (+ (* n 10000)
                     0.5))
             10000)))

(defn rand-double
  [lower-bound upper-bound]
  (let [diff (- upper-bound lower-bound)
        r (rand diff)
        r' (+ lower-bound r)]
    (round-double r')))
