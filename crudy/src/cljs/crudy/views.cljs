(ns crudy.views
  (:require
   [re-frame.core :as re-frame]
   [crudy.subs :as subs]
   ["@elastic/eui" :refer (EuiButton EuiText)]
   [accountant.core :as accountant]
   ))

(accountant/configure-navigation!
 {:nav-handler   (fn [path] (js/alert "Hello"))
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
     [:> EuiButton {:href "mylink"} "Filled"]
     ]))

(defn list-things-panel []
  [:div])

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

(defn main-panel []
  (let [cur-route (re-frame/subscribe [::subs/view])]
    [mycontent @cur-route]))
