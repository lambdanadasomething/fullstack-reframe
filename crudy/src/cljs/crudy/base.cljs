(ns crudy.base
  (:require [re-frame.core :as re-frame]
            [crudy.routing :as routing]
            ["@elastic/eui" :refer (EuiPage EuiPageBody EuiPageHeader EuiPageHeaderSection
                                            EuiPageSection EuiPageSectionHeader EuiPageContentBody
                                            EuiOverlayMask EuiConfirmModal
                                            EuiTitle
                                            EuiProvider)]))

(re-frame/reg-sub
 ::subs.view
 (fn [db]
   (get-in db [:view :data :name])))
(re-frame/reg-sub
 ::subs.modal
 (fn [db]
   (:modal db)))


(defn common-header []
  [:> EuiPageHeaderSection
   [:> EuiTitle {:size "l"}
    [:h1 "Common title"]]])

(defn page-header []
  [:> EuiPageHeaderSection
   [:> EuiTitle
    [:h2 "Content title"]]])

(defn main-panel []
  (let [cur-route (re-frame/subscribe [::subs.view])
        modal (re-frame/subscribe [::subs.modal])]
    (fn []
      [:> EuiProvider {:colorMode "light"}
       [:> EuiPage
        [:> EuiPageBody {:component "div"}
         [:> EuiPageHeader 
          [common-header]]
         [:> EuiPageSection
          [page-header]
          [routing/mycontent @cur-route]]]
        (let [[has-modal modal-type] @modal]
          (when has-modal
            [:> EuiOverlayMask
             [routing/prompt-modal modal-type]]))]]
      )))

;[:> EuiPage
; [:> EuiPageBody {:component "div"}
;  [:> EuiPageHeader
;   [common-header]]
;  [:> EuiPageContent
;   [:> EuiPageContentHeader [page-header]]
;   [:> EuiPageContentBody [routing/mycontent @cur-route]]]]
; (let [[has-modal modal-type] @modal]
;   (when has-modal
;     [:> EuiOverlayMask
;      [routing/prompt-modal modal-type]]))]
