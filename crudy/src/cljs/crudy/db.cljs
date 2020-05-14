(ns crudy.db)

(def default-db
  {:path "/"
   ;:view [:list-things]
   :view :welcome
   :state {}
   :data {
          :things [{:id 1 :name "Foo" :stat 53 :attr ["twit" "goog"]}
                   {:id 2 :name "Bar" :stat 21 :attr ["goog"]}
                   {:id 3 :name "Mary" :stat 5 :attr []}
                   {:id 4 :name "Peter" :stat 78 :attr ["git" "goog"]}
                   {:id 5 :name "Henry" :stat 44 :attr ["boing" "reddit" "devto"]}
                   {:id 6 :name "Angelina" :stat 1 :attr ["reddit"]}
                   {:id 7 :name "Joan" :stat 82 :attr []}]
          :tags ["twit" "goog" "git" "boing" "reddit" "devto"]}
   :name "re-frame"})
