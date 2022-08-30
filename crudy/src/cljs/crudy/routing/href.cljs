(ns crudy.routing.href
  (:require [reitit.core :as reitit]
            [reitit.frontend.easy :as rfe]
            ;[crudy.config :refer [PLATFORM]]
            [cljs.env]
            [crudy.macros :refer-macros [code-for-browser code-for-nodejs]]))

(def route
  [["/" {:name :page/welcome}]
   ["/mylink" {:name :page/list-things}]])

(def server-side-router (reitit/router route))

;(defn server-href [page]
;  (-> server-side-router 
;      (reitit/match-by-name page) 
;      (reitit/match->path)))

;(def server? true)

;(defn get-closure-define
;  [define]
;  (get-in @cljs.env/*compiler* [:options :closure-defines define]))

;(defn href [page]
;  (if (= PLATFORM "SERVER")
;    (server-href page)
;    (rfe/href page)))

;(= "BROWSER" (get-closure-define crudy.config/PLATFORM))

;(defmacro href-platform-dependent [& page]
;  (if true
;    `(rfe/href ~@page)
;    `(-> server-side-router
;        (reitit/match-by-name ~@page)
;        (reitit/match->path))))


(code-for-browser
 (defn href [page]
   (rfe/href page)))

(code-for-nodejs
 (defn href [page]
   (-> server-side-router 
      (reitit/match-by-name page) 
      (reitit/match->path))))
