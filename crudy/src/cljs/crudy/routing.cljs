(ns crudy.routing
  (:require [accountant.core :as accountant]
            [secretary.core :as secretary :refer-macros [defroute]]
            [crudy.welcome :as welcome]
            [crudy.list-things :as list-things]))

(re-frame/reg-event-db
 ::events.change-view
 (fn [db [_ view param]]
   (assoc-in db [:view] view)))

(defroute "/" []
  (re-frame/dispatch [::events.change-view :welcome]))

(defroute "/mylink" []
  (re-frame/dispatch [::events.change-view :list-things]))

(accountant/configure-navigation!
 {:nav-handler   (fn [path] (secretary/dispatch! path))
  :path-exists?  (fn [path] true)})

(defn not-found-panel []
  [:div
   [:h1 "404 - Page not found"]
   [:p "Nothing on file here. Did you misspell the url? :-/"]])

(defn mycontent [route]
  (case route
    :welcome     [welcome/welcome-panel]
    :list-things [list-things/list-things-panel]
    :not-found   [not-found-panel]
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

