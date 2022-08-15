(ns crudy.server
  (:require ["express" :as express]
            ["fs" :as fs]
            [macchiato.server :as http]
            [taoensso.timbre :as timbre
             :refer-macros [log  trace  debug  info  warn  error  fatal  report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]))

;; currently broken in shadow-cljs
(set! *warn-on-infer* true)

(defonce server (atom nil))

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
