(ns crudy.component.modal
  (:require [re-frame.core :as re-frame]
            [crudy.component.form-modal :as form-modal]
            ["@elastic/eui" :refer (EuiConfirmModal)]))

; Temp event?
(re-frame/reg-event-db
 ::events.close-modal
 (fn [db [_]]
   (assoc-in db [:modal] [false nil nil])))

(defn remove-user [db id]
  (update-in db [:data :things] #(remove (fn [x] (= id (:id x))) %)))

(re-frame/reg-event-db
 ::events.confirm-delete
 (fn [db [_ obj]]
   (-> db
       (assoc-in [:modal] [false nil nil])
       (remove-user (get-in obj [:obj :id])))))

(defn describe-obj [{:keys [type obj]}]
  (case type
    :user (str "User " (:user-name obj))
    ""))

(defn confrim-delete-modal [arg]
  [:> EuiConfirmModal {:title "Test" :onCancel #(re-frame/dispatch [::events.close-modal]) :onConfirm #(re-frame/dispatch [::events.confirm-delete arg])
                       :cancelButtonText "Cancel" :confirmButtonText "Confirm" :buttonColor "danger" :defaultFocusedButton "cancel"}
   [:p (str "Are you sure you want to delete " (describe-obj arg) "?")]])


; Common entry point for prompt screen
(defn prompt-modal [type arg]
  (case type
    :confirm-delete [confrim-delete-modal arg]
    :edit-user [form-modal/temp-edit-modal arg]
    [:div [:p "Opps..."]]))
