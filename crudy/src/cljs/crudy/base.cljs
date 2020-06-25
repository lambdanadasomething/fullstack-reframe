(ns crudy.base
  (:require [re-frame.core :as re-frame]
            ["@elastic/eui" :refer (EuiPage EuiPageBody EuiPageHeader EuiPageHeaderSection
                                            EuiPageContent EuiPageContentHeader EuiPageContentBody
                                            EuiOverlayMask EuiConfirmModal
                                            EuiTitle)]))


(defn common-header []
  [:> EuiPageHeaderSection
   [:> EuiTitle {:size "l"}
    [:h1 "Common title"]]])

(defn page-header []
  [:> EuiPageHeaderSection
   [:> EuiTitle
    [:h2 "Content title"]]])

(defn main-panel []
  (let [cur-route (re-frame/subscribe [::subs/view])
        modal (re-frame/subscribe [::subs/modal])]
    (fn []
      [:> EuiPage
       [:> EuiPageBody {:component "div"}
        [:> EuiPageHeader
         [common-header]]
        [:> EuiPageContent
         [:> EuiPageContentHeader [page-header]]
         [:> EuiPageContentBody [mycontent @cur-route]]]]
       (let [[has-modal modal-type] @modal]
         (when has-modal
           [:> EuiOverlayMask
            [prompt-modal modal-type]]))])))

; Not exactly base, but still common components

(defn confrim-delete-modal []
  [:> EuiConfirmModal {:title "Test" :onCancel #(re-frame/dispatch [::events/close-modal]) :onConfirm #(re-frame/dispatch [::events/close-modal])
                       :cancelButtonText "Cancel" :confirmButtonText "Confirm" :buttonColor "danger" :defaultFocusedButton "cancel"}
   [:p "Are you sure you want to delete?"]])
