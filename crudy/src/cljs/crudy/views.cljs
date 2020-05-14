(ns crudy.views
  (:require
   [re-frame.core :as re-frame]
   [crudy.subs :as subs]
   [crudy.events :as events]
   ["@elastic/eui" :refer (EuiButton EuiText EuiFieldText EuiPage EuiPageBody EuiPageContent EuiPageContentBody
     EuiPageContentHeader EuiPageContentHeaderSection EuiPageHeader EuiPageHeaderSection EuiTitle)]
   [accountant.core :as accountant]
   [secretary.core :as secretary :refer-macros [defroute]]
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

(defn list-things-panel []
  [:div
   [:h1 "List of things"]
   [:> EuiButton {:href "/"} "Go back"]])

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

(defn main-panel []
  (let [cur-route (re-frame/subscribe [::subs/view])]
    [:> EuiPage
     [:> EuiPageBody {:component "div"}
      [:> EuiPageHeader
       [common-header]]
      [:> EuiPageContent
       [:> EuiPageContentHeader [page-header]]
       [:> EuiPageContentBody [mycontent @cur-route]]]]]
    ))
