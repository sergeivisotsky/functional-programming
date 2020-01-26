(require '[clojure.string :as str])
(use 'clojure.test)

(defn average
      [numbers]
      (if (empty? numbers)
        0.0
        (float (/ (reduce + numbers) (count numbers))
               )
        )
      )

(defn get-average-mark [students]
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

(print "TEST: average\n")
(is (= 0.0 (average [])))
(is (= 5.0 (average [5])))
(is (= 3.5 (average [1 2 3 4 5 6])))

(print "TEST: get-average-mark\n")
(is (= 0.0 (average {})))
(is (= 10.0 (get-average-mark {"Anja" 10})))
(is (= 10.0 (get-average-mark {"Anja" 10 "Lauris" 5})))
(is (= 9.5 (get-average-mark {"Anja" 10 "Lauris" 5 "Sandra" 9})))
(is (= 7.25 (get-average-mark {"Vasja" 8 "Petja" 4 "Natasha" 7 "Anja" 10})))

(def task-students {"Inese" 10 "Vasja" 8 "Petja" 4 "Natasha" 7 "Anja" 10 "Lauris" 6 "Sandra" 8 "Krišjanis" 9} )
(is (= 7.4 (get-average-mark task-students)))
