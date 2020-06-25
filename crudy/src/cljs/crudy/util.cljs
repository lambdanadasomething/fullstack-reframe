(ns crudy.util)

(defn maptize [seq k]
  (map #(assoc {} k %) seq))

(defn extract-id [list id]
  (map #(get % id) (js->clj list)))
