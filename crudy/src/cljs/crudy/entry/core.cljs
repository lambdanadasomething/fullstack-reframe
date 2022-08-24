(ns crudy.entry.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [reagent.dom.server :as rsdom]
   [re-frame.core :as re-frame]
   [crudy.config :as config]
   [crudy.init :as init]
   [crudy.view.base :as base]
   [crudy.routing :as routing]
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
  (re-frame/dispatch-sync [::routing/events.initialize-router])
  (dev-setup)
  (mount-root))

(defn ssr-init []
  (re-frame/dispatch-sync [::init/events.initialize-db])
  (re-frame/clear-subscription-cache!)
  (rsdom/render-to-string [base/main-panel]))

;crudy.welcome/welcome-panel

(ssr-init)


; SSR Trouble so far:
; 1) Dynamic import: repeatedly importing the same ns eventually makes
; this goes away
; 2) Reitit Routing 1: When using reitit's "easy" frontend integration,
; We didn't put it into re-frame's effect system...
; 3) Reitit Routing 2: reitit's easy fronternd, href function only works
; in the browser env. We can use plain reitit for backend reverse
; routing.
; 3a) However, this implies that we need a way to detect current target
; at runtime.
; 4) Out of the three form of using EUI icons, the 2nd method (direct)
; Doesn't work.

