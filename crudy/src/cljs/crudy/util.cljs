(ns crudy.util)

(defn maptize [seq k]
  (map #(assoc {} k %) seq))

(defn extract-id [list id]
  (map #(get % id) (js->clj list)))

; Not sure if used or not
(comment
  (re-frame/reg-sub
   ::state
   (fn [db]
     (:state db))))
