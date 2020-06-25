(ns crudy.routing
  (:require [accountant.core :as accountant]
            [secretary.core :as secretary :refer-macros [defroute]]))

(defroute "/" []
  (re-frame/dispatch [::events/change-view :welcome]))

(defroute "/mylink" []
  (re-frame/dispatch [::events/change-view :list-things]))

(accountant/configure-navigation!
 {:nav-handler   (fn [path] (secretary/dispatch! path))
  :path-exists?  (fn [path] true)})

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

(defn prompt-modal [type]
  (case type
    :confirm-delete [confrim-delete-modal]
    [:div [:p "Opps..."]]))
