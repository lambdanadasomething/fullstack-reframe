(ns crudy.view.list-things
  (:require [re-frame.core :as re-frame]
            [reagent.core :as rc]
            [crudy.util :as util]
            [crudy.routing.href :refer [href]]
            ["@elastic/eui" :refer (EuiPanel EuiSpacer EuiBasicTable
                                             EuiForm EuiFieldText EuiDatePicker EuiComboBox EuiButton
                                             EuiBadge)]))

(re-frame/reg-event-db
 ::events.list-things-table-select
 (fn [db [_ selected]]
   (assoc-in db [:state :things :selected] selected)))

(re-frame/reg-event-db
 ::events.list-things-action
 (fn [db [_ thing]]
   (println thing)
   (assoc-in db [:modal] [true (:action thing) thing])))

(re-frame/reg-sub
 ::subs.things
 (fn [db]
   (get-in db [:data :things])))

(re-frame/reg-sub
 ::subs.tags
 (fn [db]
   (util/maptize (get-in db [:data :tags]) :label)))

(re-frame/reg-sub
 ::subs.list-things-selected
 (fn [db]
   (get-in db [:state :things :selected])))

(defn user-action [action]
  (fn [user] (re-frame/dispatch [::events.list-things-action
                                 {:type :user,
                                  :action action
                                  :obj (js->clj user
                                                :keywordize-keys true)}])))

; https://github.com/elastic/eui/issues/4836
; "secondary" is replaced by "success"
(defn list-things-content []
  (let [myitems (re-frame/subscribe [::subs.things])
        cols [{:field "id" :name "UID"}
              {:field "user-name" :name "User Name"}
              {:field "stat" :name "Statistics"}
              {:field "attr" :name "Attributes" :render (fn [xs] (rc/as-element (for [x xs]
                                                                                  ^{:key x} [:> EuiBadge {:color "success"} x])))}
              {:name "Actions" :actions [{:name "Edit"   :description "Edit this item"   :icon "pencil" :type "icon"
                                          :onClick (user-action :edit-user)}
                                         {:name "Delete" :description "Delete this item" :icon "trash"  :type "icon"
                                          :onClick (user-action :confirm-delete)}]}]
        selected-items (re-frame/subscribe [::subs.list-things-selected])]
    [:div
     [:h1 "List of things"]
     [:p (str @selected-items)]
     [:> EuiBasicTable {:items @myitems :columns cols :itemId "id"
                        :selection {:onSelectionChange (fn [x] (re-frame/dispatch [::events.list-things-table-select (util/extract-id x "id")]))}}]
     [:> EuiButton {:href (href :page/welcome)} "Go back"]]))

(defn search-things-widget []
  (let [opt (re-frame/subscribe [::subs.tags])]
    [:> EuiForm {:component "form"}
     [:> EuiFieldText]
     [:> EuiDatePicker]
     [:> EuiComboBox {:options @opt :delimiter "," :selectedOptions [{:label "git"} {:label "goog"}]}]
     [:> EuiButton {:type "submit" :fill true} "Search"]]))

(defn list-things-panel []
  [:div
   [:> EuiPanel [search-things-widget]]
   [:> EuiSpacer]
   [list-things-content]])
