(ns crudy.routing
  (:require [re-frame.core :as re-frame]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [crudy.view.welcome :refer [welcome-panel]]
            [crudy.view.list-things :as list-things]))

(re-frame/reg-event-db
 ::events.change-view
 (fn [db [_ view param]]
   (assoc-in db [:view] view)))

(def route
  [["/" {:name :page/welcome}]
   ["/mylink" {:name :page/list-things}]])

(defn start-fe-router []
  (rfe/start!
   (rf/router route)
   (fn [m] (re-frame/dispatch [::events.change-view m]))
   {:use-fragment false}))

(re-frame/reg-event-db
 ::events.initialize-router
 (fn [db _]
   (do
     (start-fe-router)
     db)))

(defn not-found-panel []
  [:div
   [:h1 "404 - Page not found"]
   [:p "Nothing on file here. Did you misspell the url? :-/"]])

(defn mycontent [route]
  (case route
    :page/welcome     [welcome-panel]
    :page/list-things [list-things/list-things-panel]
    :page/not-found   [not-found-panel]
    [:div]))
