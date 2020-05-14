(ns crudy.events
  (:require
   [re-frame.core :as re-frame]
   [crudy.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(comment (re-frame/reg-event-db
 ::change-path
 (fn [])))
