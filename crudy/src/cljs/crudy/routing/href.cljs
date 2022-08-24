(ns crudy.routing.href
  (:require [reitit.core :as reitit]
            [reitit.frontend.easy :as rfe]))

(def route
  [["/" {:name :page/welcome}]
   ["/mylink" {:name :page/list-things}]])

(def server-side-router (reitit/router route))

(defn server-href [page]
  (-> server-side-router 
      (reitit/match-by-name page) 
      (reitit/match->path)))

(def server? true)

(defn href [page]
  (if server?
    (server-href page)
    (rfe/href page)))
