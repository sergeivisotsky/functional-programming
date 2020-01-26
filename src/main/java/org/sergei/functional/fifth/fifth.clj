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

(testing "validate [message key]"
         (is (= false (validate "" 3)))
         (is (= false (validate "TEXT" 1)))
         (is (= false (validate "IT IS UN-VALID" 3)))
         (is (= false (validate "abc 123" 2)))
         (is (= true (validate "GOOD_text" 5)))
         (is (= true (validate "IT IS VALID" 3)))
         (is (= true (validate "ValidationPassed" 10)))
         )
(testing "prepare [message]"
         (is (= "IT_IS_OK" (prepare "IT IS OK")))
         (is (= "Long___space" (prepare "Long   space")))
         (is (= "WE_ARE_DISCOVERED_FLEE_AT_ONCE" (prepare "WE ARE DISCOVERED FLEE AT ONCE")))
         )
(testing "get-fence-matrix [width height]"
         (is (= [0 11 2 13 4 15 6 17 8 19] (get-fence-matrix 10 2)))
         (is (= [0 6 12 8 4] (get-fence-matrix 5 3)))
         (is (= [0 13 26 39 28 17 6 19 32 45 34 23] (get-fence-matrix 12 4)))
         )
(testing "get-encrypt-fence-chars [message key]"
         (is (= {0 \A} (get-encrypt-fence-chars "A" 1)))
         (is (= {0 \A, 4 \B, 8 \C} (get-encrypt-fence-chars "ABC" 3)))
         (is (= {0 \A, 6 \B, 12 \C, 8 \D, 4 \E} (get-encrypt-fence-chars "ABCDE" 3)))
         )
(testing "encrypt [message key]"
         (is (= "ABC" (encrypt "ABC" 3)))
         (is (= "ABDC" (encrypt "ABCD" 3)))
         (is (= "AEBDC" (encrypt "ABCDE" 3)))
         (is (= "AEBDFC" (encrypt "ABCDEF" 3)))
         (is (= "AEBDFCG" (encrypt "ABCDEFG" 3)))
         (is (= "AEBDFHCG" (encrypt "ABCDEFGH" 3)))
         (is (= "AEIBDFHCG" (encrypt "ABCDEFGHI" 3)))
         (is (= "WRIVDETCEAEDSOEE_LEA_NE__CRF_O" (encrypt "WE_ARE_DISCOVERED_FLEE_AT_ONCE" 3)))
         (is (= "WIDTEDSE_A___CRF_OAEOELENERVEC" (encrypt "WE_ARE_DISCOVERED_FLEE_AT_ONCE" 5)))
         (is (= "WFE_L_DEAEERR_EEA_VTDO_EICOCSN" (encrypt "WE_ARE_DISCOVERED_FLEE_AT_ONCE" 10)))
         )
(testing "encrypt-message [message key]"
         (is (= "ERROR: Invalid [message] or [key]!" (encrypt-message "NOT-VALID" 3)))
         (is (= "ERROR: Invalid [message] or [key]!" (encrypt-message "TooSmallKey" 1)))
         (is (= "WRIVDETCEAEDSOEE_LEA_NE__CRF_O" (encrypt-message "WE ARE DISCOVERED FLEE AT ONCE" 3)))
         (is (= "A____C_DB_" (encrypt-message "A B  C   D" 3)))
         )
(testing "decrypt [message key]"
         (is (= "ABC" (decrypt "ABC" 3)))
         (is (= "ABCD" (decrypt "ABDC" 3)))
         (is (= "ABCDE" (decrypt "AEBDC" 3)))
         (is (= "ABCDEF" (decrypt "AEBDFC" 3)))
         (is (= "ABCDEFG" (decrypt "AEBDFCG" 3)))
         (is (= "ABCDEFGH" (decrypt "AEBDFHCG" 3)))
         (is (= "ABCDEFGHI" (decrypt "AEIBDFHCG" 3)))
         (is (= "WE_ARE_DISCOVERED_FLEE_AT_ONCE" (decrypt "WRIVDETCEAEDSOEE_LEA_NE__CRF_O" 3)))
         (is (= "WE_ARE_DISCOVERED_FLEE_AT_ONCE" (decrypt "WIDTEDSE_A___CRF_OAEOELENERVEC" 5)))
         (is (= "WE_ARE_DISCOVERED_FLEE_AT_ONCE" (decrypt "WFE_L_DEAEERR_EEA_VTDO_EICOCSN" 10)))
         )
(testing "decrypt-message [message key]"
         (is (= "ERROR: Invalid [message] or [key]!" (decrypt-message "NOT-VALID" 3)))
         (is (= "ERROR: Invalid [message] or [key]!" (decrypt-message "TooSmallKey" 1)))
         (is (= "WE_ARE_DISCOVERED_FLEE_AT_ONCE" (decrypt-message "WRIVDETCEAEDSOEE_LEA_NE__CRF_O" 3)))
         )
(testing "!--- Rail Fence Cipher ---!"
         (is (= "ERROR: Invalid [message] or [key]!" (decrypt-message (encrypt-message "ABC_123_xyz_0" 5) 5)))
         (is (= "DECRYPT_ENCRYPT_NO_CHANGES" (decrypt-message (encrypt-message "DECRYPT_ENCRYPT_NO_CHANGES" 3) 3)))
         (is (= "QWERTYUIOPASDFGHJKLZXCVBNM" (decrypt-message (encrypt-message "QWERTYUIOPASDFGHJKLZXCVBNM" 4) 4)))
         (is (= "QWERTYUIOPASDFGHJKLZXCVBNM" (decrypt-message (encrypt-message "QWERTYUIOPASDFGHJKLZXCVBNM" 10) 10)))
         )
