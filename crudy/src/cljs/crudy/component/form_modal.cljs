(ns crudy.component.form-modal
  (:require [re-frame.core :as re-frame]
            ["@elastic/eui" :refer (EuiModal EuiModalHeader EuiModalHeaderTitle
                                             EuiModalBody
                                             EuiModalFooter
                                             EuiForm EuiButtonEmpty EuiButton
                                             EuiFormRow EuiFieldText)]))

(defn temp-form-body [arg]
  (let [username (get-in arg [:obj :user-name])
        stat     (get-in arg [:obj :stat])]
    [:> EuiForm {:id "my-edit-form" :component "form"}
     [:> EuiFormRow {:label "User name"} [:> EuiFieldText {:value username}]]
     [:> EuiFormRow {:label "Statistic"} [:> EuiFieldText {:value stat}]]]))

(defn temp-edit-modal [arg]
  [:> EuiModal {:onClose #(re-frame/dispatch [:crudy.component.modal/events.close-modal])}
   [:> EuiModalHeader [:> EuiModalHeaderTitle [:h1 "Edit Form"]]]
   [:> EuiModalBody
    (temp-form-body arg)]
   [:> EuiModalFooter
    [:> EuiButtonEmpty {:onClick #(re-frame/dispatch [:crudy.component.modal/events.close-modal])} "Cancel"]
    [:> EuiButton {:type "submit"
                   :form "my-edit-form"} "Save"]]])

