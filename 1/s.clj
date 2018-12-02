(def input (slurp  "in.small"))

(defn parse-input [in]
  (map read-string (clojure.string/split in #"\n")))

(defn process [in]
  (apply + (parse-input in)))

(defn freqs [c]
  (reductions + (flatten (repeat c))))

(defn first-duplicate [coll]
  (reduce (fn [acc x]
            (if (get acc x)
              (reduced x)
              (conj acc x)))
          #{} coll))

(defn process2 [in]
  (-> in
     parse-input
     freqs
     first-duplicate))
