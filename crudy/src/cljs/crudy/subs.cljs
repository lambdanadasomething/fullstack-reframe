(ns crudy.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::things
 (fn [db]
   (get-in db [:data :things])))

(re-frame/reg-sub
 ::tags
 (fn [db]
   (get-in db [:data :tags])))

(re-frame/reg-sub
 ::view
 (fn [db]
   (:view db)))

(re-frame/reg-sub
 ::state
 (fn [db]
   (:state db)))
