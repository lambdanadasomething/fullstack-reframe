(ns crudy.events
  (:require
   [re-frame.core :as re-frame]
   [crudy.db :as db]
   ))



(re-frame/reg-event-db
 ::list-things-table-select
 (fn [db [_ selected]]
   (assoc-in db [:state :things :selected] selected)))

(re-frame/reg-event-db
 ::list-things-delete
 (fn [db [_ id]]
   (assoc-in db [:modal] [true :confirm-delete])))
