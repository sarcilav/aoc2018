(def input (parse-input (slurp "in")))

(defn parse-input [in]
  (clojure.string/split in #"\n"))

(defn valid-freqs [s]
  (distinct (filter #(#{3 2} %) (vals (frequencies s)))))

(defn process [c]
  (apply * (vals (frequencies (flatten (map valid-freqs c))))))


(defn word-diff [a b]
  [(count (filter #(not= 0 %) (map compare a b))) a b])

(defn process2 [c]
  (distinct(map (fn [e]
                  (filter (comp (partial = 1) first)
                          (map (partial word-diff e) c)))
                c)))
