(ns crudy.base
  (:require [re-frame.core :as re-frame]
            [crudy.routing :as routing]
            ["@elastic/eui" :refer (EuiPage EuiPageBody EuiPageHeader EuiPageHeaderSection
                                            EuiPageContent EuiPageContentHeader EuiPageContentBody
                                            EuiOverlayMask EuiConfirmModal
                                            EuiTitle)]))

(re-frame/reg-sub
 ::subs.view
 (fn [db]
   (:view db)))
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
      [:> EuiPage
       [:> EuiPageBody {:component "div"}
        [:> EuiPageHeader
         [common-header]]
        [:> EuiPageContent
         [:> EuiPageContentHeader [page-header]]
         [:> EuiPageContentBody [routing/mycontent @cur-route]]]]
       (let [[has-modal modal-type] @modal]
         (when has-modal
           [:> EuiOverlayMask
            [routing/prompt-modal modal-type]]))])))
