(ns crudy.routing
  (:require [re-frame.core :as re-frame]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            ["@elastic/eui" :refer (EuiConfirmModal)] ;temporary until we figure out where to put the modal
            [crudy.welcome :refer [welcome-panel]]
            [crudy.list-things :as list-things]))

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

; Not exactly base, but still common components

; Temp event?
(re-frame/reg-event-db
 ::events.close-modal
 (fn [db [_]]
   (assoc-in db [:modal] [false nil])))

(defn confrim-delete-modal []
  [:> EuiConfirmModal {:title "Test" :onCancel #(re-frame/dispatch [::events.close-modal]) :onConfirm #(re-frame/dispatch [::events.close-modal])
                       :cancelButtonText "Cancel" :confirmButtonText "Confirm" :buttonColor "danger" :defaultFocusedButton "cancel"}
   [:p "Are you sure you want to delete?"]])


; Common entry point for prompt screen
(defn prompt-modal [type]
  (case type
    :confirm-delete [confrim-delete-modal]
    [:div [:p "Opps..."]]))

