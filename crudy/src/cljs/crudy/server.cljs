(ns crudy.server
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [cljs-node-io.core :as io :refer [slurp spit]]
            [macchiato.server :as http]
            [taoensso.timbre :as timbre
             :refer-macros [log  trace  debug  info  warn  error  fatal  report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            [reagent.core :as reagent]
            [reagent.dom.server :as rdom]
            ;[crudy.base :as base]
            [crudy.core :as crudy]
            [crudy.routing :refer [route]]
            [reitit.ring :as ring]
            [macchiato.middleware.params :as params]
            [macchiato.middleware.restful-format :as rf]
            [hiccups.runtime :as hiccups]))

;; currently broken in shadow-cljs
(set! *warn-on-infer* true)

(defonce server (atom nil))

(def html-main-template (slurp "resources/public/index.html"))

(defn hello-a [x]
  [:html {:lang "en"}
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport" :content "width=device-width,initial-scale=1"}]
    [:title "crudy"]
    [:link {:href "css/eui_theme_light.css" :rel "stylesheet" :type "text/css"}]]
   [:body
    [:noscript "crudy is a JavaScript app. Please enable JavaScript to continue."]
    [:div
     {:dangerously-set-inner-HTML
      {:__html x}}]
    [:script {:src "js/compiled/app.js"}]]])

;(-> (crudy/ssr-init)
;    (rdom/render-to-string)
;    (hello-a)
;    (html))

;(hiccups/defhtml test-html [comp]
;  [:html {:lang "en"}
;   [:head
;    [:meta {:charset "utf-8"}]
;    [:meta {:name "viewport" :content "width=device-width,initial-scale=1"}]
;    [:title "crudy"]
;    [:link {:href "css/eui_theme_light.css" :rel "stylesheet" :type "text/css"}]]
;   [:body
;    [:noscript "crudy is a JavaScript app. Please enable JavaScript to continue."]
;    [:div
;     {:dangerously-set-inner-HTML
;      {:__html comp}}]
;    [:script {:src "js/compiled/app.js"}]]])

;(rdom/render-to-static-markup (test-html))

;https://dev.solita.fi/2020/05/26/clojurescript-web-serverwith-macchiato-shadowcljs-and-reitit.html
(defn wrap-body-to-params
  [handler]
  (fn [request respond raise]
    (handler (-> request
                 (assoc-in [:params :body-params] (:body request))
                 (assoc :body-params (:body request))) respond raise)))

(def app
  (ring/ring-handler
   (ring/router
    [route]
    {:data {:middleware [params/wrap-params
                         #(rf/wrap-restful-format % {:keywordize? true})
                         wrap-body-to-params]}})
   (ring/create-default-handler)))

(defn handler
  [request callback]
  (callback {:status 200
             :body "Hello Macchiato"}))

(defn start-server []
  (info "Hey I am running now!")
  (let [host "0.0.0.0"
        port 8290]
    (http/start
     {:handler    handler
      :host       host
      :port       port
      :on-success #(info "macchiato-test started on" host ":" port)})))


(defn start! []
  ;; called by main and after reloading code
  (reset! server (start-server)))

(defn stop! []
  ;; called before reloading code
  (.close @server)
  (reset! server nil))

(defn main []
  ;; executed once, on startup, can do one time setup here
  (start!))
