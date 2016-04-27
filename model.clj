(defn thickness-function
  "Take the thickness and returns the function of thickess around the camber line"
  [thickness x]
  (let [a-0 0.2969
        a-1 -0.126
        a-2 -0.3516
        a-3 0.2843
        a-4 -0.1036]
        (* (/ thickness 0.2)
           (+ (* a-0 (^ x 0.5)) (* a-1 x) (* a-2 (^ x 2)) (* a-3 (^ x 3)) (* a-4 (^ x 4))))))
