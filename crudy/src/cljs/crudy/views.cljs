(ns crudy.views
  (:require
   ;["@babel/polyfill"]
   [re-frame.core :as re-frame]
   [crudy.subs :as subs]
   [crudy.events :as events]
   ["@elastic/eui" :refer (EuiButton EuiText EuiFieldText EuiPage EuiPageBody EuiPageContent EuiPageContentBody
                                     EuiPageContentHeader EuiPageContentHeaderSection EuiPageHeader EuiPageHeaderSection EuiTitle
                                     EuiBasicTable EuiBadge EuiForm EuiDatePicker EuiComboBox EuiPanel EuiSpacer
                                     EuiConfirmModal EuiOverlayMask)]
   [reagent.core :as rc]
   [crudy.util :as util]))








(defn list-things-content []
  (let [myitems (re-frame/subscribe [::subs/things])
        cols [{ :field "id" :name "UID" }
              { :field "user-name" :name "User Name" }
              { :field "stat" :name "Statistics" }
              { :field "attr" :name "Attributes" :render (fn [xs] (rc/as-element (for [x xs]
                                                                                   [:> EuiBadge {:color "secondary"} x])))}
              { :name "Actions" :actions [{ :name "Edit"   :description "Edit this item"   :icon "pencil" :type "icon" 
                                           :href "https://www.google.com" }
                                          { :name "Delete" :description "Delete this item" :icon "trash"  :type "icon" 
                                           :onClick (fn [x] (re-frame/dispatch [::events/list-things-delete x])) }]}]
        selected-items (re-frame/subscribe [::subs/list-things-selected])]
    [:div
     [:h1 "List of things"]
     [:p (str @selected-items)]
     [:> EuiBasicTable {:items @myitems :columns cols :itemId "id" 
                        :selection {:onSelectionChange (fn [x] (re-frame/dispatch [::events/list-things-table-select (util/extract-id x "id")]))}}]
     [:> EuiButton {:href "/"} "Go back"]]))

(defn search-things-widget []
  (let [opt (re-frame/subscribe [::subs/tags])]
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
