(use 'clojure.test)
(require '[clojure.string :as str])

(defn validation [message key]
      (if (re-matches #"^[A-Za-z\s_]+$" message)
        (> key 1)
        false)
      )

(defn replacement [message]
      (str/replace message #" " "_")
      )

(defn index_calculation [width height]
      (def row 0)
      (def direction-down false)
      (vec
        (for [col (range 0 width)] (do
                                     (if (or (= 0 row) (= row (- height 1)))
                                       (def direction-down (not direction-down)))
                                     (def matrix-index (+ (* row width) col))
                                     ;; (debug-get-fence-matrix row width col matrix-index)
                                     (def row
                                       (if (true? direction-down)
                                         (inc row)
                                         (dec row)))
                                     matrix-index
                                     ))
        )
      )

(defn get_second_string_index [cell key]
      (mod cell key)
      )

(defn get-encrypt-chars [message key]
      (zipmap
        (index_calculation (count message) key)
        (vec message))
      )

(defn get-decrypt-chars [message key]
      (zipmap
        (sort (index_calculation (count message) key))
        (vec message))
      )

; Encryption
(defn encrypt [message key]
      (str/join
        (for [[pos char]
              (sort-by first (get-encrypt-chars message key))
              ] char)
        )
      )
(defn encrypt-message [message key]
      (if (validation message key)
        (encrypt (replacement message) key)
        (str "ERROR: Invalid [message] or [key]!"))
      )


;; Decryption
(defn decrypt [message key]
      (str/join
        (for [[pos ch]
              (sort-by first
                       (for [[index char] (get-decrypt-chars message key)]
                            [(get_second_string_index index (count message)) char]
                            )
                       )]
             ch
             )
        )
      )
(defn decrypt-message [message key]
      (if (validation message key)
        (decrypt (replacement message) key)
        (str "ERROR: Invalid [message] or [key]!"))
      )
