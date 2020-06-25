(ns crudy.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [crudy.config :as config]
   [crudy.init :as init]
   [crudy.base :as base]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [base/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::init/events.initialize-db])
  (dev-setup)
  (mount-root))
