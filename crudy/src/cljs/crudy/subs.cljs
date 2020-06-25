(ns crudy.subs
  (:require
   [re-frame.core :as re-frame]
   [crudy.util :as util]))

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
   (util/maptize (get-in db [:data :tags]) :label)))

(re-frame/reg-sub
 ::view
 (fn [db]
   (:view db)))

(re-frame/reg-sub
 ::state
 (fn [db]
   (:state db)))

(re-frame/reg-sub
 ::modal
 (fn [db]
   (:modal db)))

(re-frame/reg-sub
 ::list-things-selected
 (fn [db]
   (get-in db [:state :things :selected])))
