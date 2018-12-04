;;; emacs (sort-lines)
;;;
(def input (slurp "in"))

(defn parse-line [l]
  (re-find #"\[\d{4}-\d{2}-\d{2} \d{2}:(\d+)\] (\w+) .*" l))

(defn line [[last-guard sleep-time] [l m action]]
  (case action
    "Guard" (let [g (last (re-find #"Guard #(\d+)" l))]
              (if-not (get sleep-time g)
                [g (assoc sleep-time g [])]
                [g sleep-time]))
    "wakes" [last-guard (update sleep-time last-guard conj m)]
    "falls" [last-guard (update sleep-time last-guard conj m)]))

(defn parse-input [in]
  (reduce line
          ["" {}]
          (map parse-line (sort (clojure.string/split in #"\n")))))

(defn nice-intervals [times]
  (partition 2 (map #(Integer. %) times)))

(defn total-time [times]
  (apply + (map (partial apply -) (nice-intervals times))))

(defn most-minute [times]
  (apply max-key val
         (reduce (fn [acc [s e]]
                   (merge-with +
                               acc
                               (zipmap (range s e) (repeat 1))))
                 (zipmap (range 1 61) (repeat 0))
                 times)))

(defn process [in]
  (let [[amount id times] (first (sort
                                  (map (fn [[id times]]
                                         [(total-time times) id (nice-intervals times)])
                                       (second (parse-input in)))))
        minute (most-minute times)]
    [id minute]))

(defn process2 [in]
  (let [guards-and-intervals (map (fn [[id times]]
                                    [id (nice-intervals times)])
                                  (second (parse-input in)))]
    (apply max-key (comp second first) (map (fn [[guard times]]
                                              [(most-minute times) guard])
                                            guards-and-intervals))))
