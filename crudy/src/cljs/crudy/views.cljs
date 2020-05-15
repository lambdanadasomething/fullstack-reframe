(ns crudy.views
  (:require
   [re-frame.core :as re-frame]
   [crudy.subs :as subs]
   [crudy.events :as events]
   ["@elastic/eui" :refer (EuiButton EuiText EuiFieldText EuiPage EuiPageBody EuiPageContent EuiPageContentBody
                                     EuiPageContentHeader EuiPageContentHeaderSection EuiPageHeader EuiPageHeaderSection EuiTitle
                                     EuiBasicTable EuiBadge EuiForm EuiDatePicker EuiComboBox EuiIcon EuiPanel EuiSpacer
                                     EuiConfirmModal EuiOverlayMask)]
   [accountant.core :as accountant]
   [secretary.core :as secretary :refer-macros [defroute]]
   [reagent.core :as rc]
   ))


(defroute "/" []
  (re-frame/dispatch [::events/change-view :welcome]))

(defroute "/mylink" []
  (re-frame/dispatch [::events/change-view :list-things]))

(accountant/configure-navigation!
 {:nav-handler   (fn [path] (secretary/dispatch! path))
  :path-exists?  (fn [path] true)})


(defn welcome-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:> EuiText
      [:h1 "Hello world from " @name]
      [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."]
      [:h2 "Another subtitle"]
      [:ul
       [:li "Item one"]
       [:li "Item two"]
       [:li "Item three"]]]
     [:> EuiButton {:href "mylink"} "List of things"]
     [:div
      [:> EuiFieldText {:id "myfield"}]
      [:> EuiButton {:onClick #(re-frame/dispatch [::events/change-name (.-value (js/document.getElementById "myfield"))])} "Change name"]]
     ]))

(defn extract-id [list id]
  (map #(get % id) (js->clj list)))

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
                        :selection {:onSelectionChange (fn [x] (re-frame/dispatch [::events/list-things-table-select (extract-id x "id")]))}}]
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

(defn not-found-panel []
  [:div
   [:h1 "404 - Page not found"]
   [:p "Nothing on file here. Did you misspell the url? :-/"]])

(defn mycontent [route]
  (case route
    :welcome     [welcome-panel]
    :list-things [list-things-panel]
    :not-found   [not-found-panel]
    [:div]))

(defn common-header []
  [:> EuiPageHeaderSection
   [:> EuiTitle {:size 1}
    [:h1 "Common title"]]])

(defn page-header []
  [:> EuiPageHeaderSection
   [:> EuiTitle
    [:h2 "Content title"]]])

(defn confrim-delete-modal []
  [:> EuiConfirmModal {:title "Test" :onCancel #(re-frame/dispatch [::events/close-modal]) :onConfirm #(re-frame/dispatch [::events/close-modal])
                       :cancelButtonText "Cancel" :confirmButtonText "Confirm" :buttonColor "danger" :defaultFocusedButton "cancel"}
   [:p "Are you sure you want to delete?"]])

(defn prompt-modal [type]
  (case type
    :confirm-delete [confrim-delete-modal]
    [:div [:p "Opps..."]]))

(defn main-panel []
  (let [cur-route (re-frame/subscribe [::subs/view])
        modal (re-frame/subscribe [::subs/modal])]
    (fn []
      [:> EuiPage
       [:> EuiPageBody {:component "div"}
        [:> EuiPageHeader
         [common-header]]
        [:> EuiPageContent
         [:> EuiPageContentHeader [page-header]]
         [:> EuiPageContentBody [mycontent @cur-route]]]]
       (let [[has-modal modal-type] @modal]
         (when has-modal
           [:> EuiOverlayMask
            [prompt-modal modal-type]]))])
    ))
