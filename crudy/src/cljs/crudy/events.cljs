(ns crudy.events
  (:require
   [re-frame.core :as re-frame]
   [crudy.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::change-name
 (fn [db [_ name]]
   (assoc-in db [:name] name)))

(re-frame/reg-event-db
 ::change-view
 (fn [db [_ view param]]
   (assoc-in db [:view] view)))

(re-frame/reg-event-db
 ::list-things-table-select
 (fn [db [_ selected]]
   (assoc-in db [:state :things :selected] selected)))
