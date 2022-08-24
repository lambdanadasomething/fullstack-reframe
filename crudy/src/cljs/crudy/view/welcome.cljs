(ns crudy.view.welcome
  (:require [re-frame.core :as re-frame]
            [crudy.routing.href :refer [href]]
            ["@elastic/eui" :refer (EuiText EuiButton EuiFieldText)]
            ["@elastic/eui/lib/components/icon/icon" :refer (EuiIcon)]
            ["@elastic/eui/es/components/icon/assets/beaker" :rename {icon EuiIconBeaker}]))

(re-frame/reg-sub
 ::subs.name
 (fn [db]
   (:name db)))

(re-frame/reg-event-db
 ::events.change-name
 (fn [db [_ name]]
   (assoc-in db [:name] name)))

(defn welcome-panel []
  (let [name (re-frame/subscribe [::subs.name])]
    [:div
     [:> EuiText
      [:h1 "Hello world test from " @name]
      [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."]
      [:h2 "Another subtitle"]
      [:ul
       [:li "Item one"]
       [:li "Item two"]
       [:li "Item three"]
       [:li "Supply imported icon as type attr" [:> EuiIcon {:type EuiIconBeaker}]]
       ;[:li "Use imported icon directly" [:> EuiIconBeaker]]
       [:li "Use name as type attr" [:> EuiIcon {:type "beaker"}]]]]
     [:> EuiButton {:href (href :page/list-things)} "List of things"]
     [:div
      [:> EuiFieldText {:id "myfield"}]
      [:> EuiButton {:onClick #(re-frame/dispatch [::events.change-name (.-value (js/document.getElementById "myfield"))])} "Change name"]]]))
