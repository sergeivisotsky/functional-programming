(require '[clojure.string :as str])

(defn average
  [numbers]
  (if (empty? numbers)
    0.0
    (float (/ (reduce + numbers) (count numbers))
     )
  )
)

(defn get-average-mark [marks]
(average
  (for [
        [x y]
        (filter
          (fn [[k v]] (str/ends-with? k "a")) students
          )
        ] y)
  )
)
(def marks {"Inese" 10 "Vasja" 8 "Petja" 4 "Natasha" 7 "Anja" 10 "Lauris" 6 "Sandra" 8 "Krišjanis" 9})
(is (= 8 (get-average-mark marks)))
