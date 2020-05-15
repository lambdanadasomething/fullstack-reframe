(ns crudy.db)

(def default-db
  {:path "/"
   ;:view [:list-things]
   :view :welcome
   :state {}
   :modal [false nil]
   :data {
          :things [{:id 1 :user-name "Foo" :stat 53 :attr ["twit" "goog"]}
                   {:id 2 :user-name "Bar" :stat 21 :attr ["goog"]}
                   {:id 3 :user-name "Mary" :stat 5 :attr []}
                   {:id 4 :user-name "Peter" :stat 78 :attr ["git" "goog"]}
                   {:id 5 :user-name "Henry" :stat 44 :attr ["boing" "reddit" "devto"]}
                   {:id 6 :user-name "Angelina" :stat 1 :attr ["reddit"]}
                   {:id 7 :user-name "Joan" :stat 82 :attr []}]
          :tags ["twit" "goog" "git" "boing" "reddit" "devto"]}
   :name "re-frame"})
