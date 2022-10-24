(ns crudy.component.form-modal
  (:require [re-frame.core :as re-frame]
            ["@elastic/eui" :refer (EuiModal EuiModalHeader EuiModalHeaderTitle
                                             EuiModalBody
                                             EuiModalFooter
                                             EuiForm EuiButtonEmpty EuiButton
                                             EuiFormRow EuiFieldText)]
            [fork.re-frame :as fork]))

(re-frame/reg-event-db
 ::events.submit-form
 (fn [db [_ {:keys [values dirty path]}]]
   (println values)
   db))

;[username (get-in arg [:obj :user-name])
; stat     (get-in arg [:obj :stat])]
(defn temp-form-body [{:keys [values handle-change handle-submit]}]
  [:> EuiForm {:id "my-edit-form" :component "form" :onSubmit handle-submit}
   [:> EuiFormRow {:label "User name"} [:> EuiFieldText {:value (values :user-name) :onChange handle-change}]]
   [:> EuiFormRow {:label "Statistic"} [:> EuiFieldText {:value (values :stat) :onChange handle-change}]]])

(defn temp-edit-modal [arg]
  [:> EuiModal {:onClose #(re-frame/dispatch [:crudy.component.modal/events.close-modal])}
   [:> EuiModalHeader [:> EuiModalHeaderTitle [:h1 "Edit Form"]]]
   [:> EuiModalBody
    [fork/form {:initial-values (:obj arg)
                :path [EuiForm]
                :form-id "my-edit-form"
                :prevent-default? true
                :clean-on-unmount? true
                :on-submit #(re-frame/dispatch [::events.submit-form %])}
     temp-form-body]]
   [:> EuiModalFooter
    [:> EuiButtonEmpty {:onClick #(re-frame/dispatch [:crudy.component.modal/events.close-modal])} "Cancel"]
    [:> EuiButton {:type "submit"
                   :form "my-edit-form"} "Save"]]])

