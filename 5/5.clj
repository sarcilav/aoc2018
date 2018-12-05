(def input-small "dabAcCaCBAcCcaDA")

(def input1 (slurp "in1"))

(defn reaction [s]
  (loop [head []
         tail s]
    (cond
      (empty? tail) head
      (empty? head) (recur (vector (first tail)) (rest tail))
      :else (let [reactable (fn [c1 c2]
                             (and (not= c1 c2)
                                (= (clojure.string/lower-case c1)
                                   (clojure.string/lower-case c2))))]
              (if (reactable (last head) (first tail))
                (recur (pop head) (rest tail))
                (recur (conj head (first tail)) (rest tail)))))))

(defn min-reduction [s]
  (let [abc (seq "abcdefghijklmnopqrstuvwxyz")
        remover (fn [s c]
                  (clojure.string/replace s (re-pattern (str "(?i)" c)) ""))]
    (apply min
           (map (fn [c]
                  (count (reaction (remover s c)))) abc))))


