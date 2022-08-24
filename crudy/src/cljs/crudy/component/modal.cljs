(ns crudy.component.modal
  (:require [re-frame.core :as re-frame]
            ["@elastic/eui" :refer (EuiConfirmModal)]))

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
