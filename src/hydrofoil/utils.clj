(ns hydrofoil.utils)

(defn NACA-design
  [max-camber position-camber thickness]
  (hash-map :corrected-max-camber (/ max-camber 100)
            :corrected-position-camber (/ position-camber 10)
            :corrected-thickness (/ thickness 100)
            :max-camber max-camber
            :positon-camber position-camber
            :thickness thickness))
