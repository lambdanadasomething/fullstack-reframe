(ns crudy.init
  (:require
   [re-frame.core :as re-frame]
   ;["@babel/polyfill"]
   ["@elastic/eui/lib/components/icon/icon" :refer (appendIconComponentCache EuiIcon)]
   ["@elastic/eui/es/components/icon/assets/beaker" :rename {icon EuiIconBeaker}]
   ["@elastic/eui/es/components/icon/assets/arrow_down" :rename {icon EuiIconArrowDown}]
   ["@elastic/eui/es/components/icon/assets/calendar" :rename {icon EuiIconCalendar}]
   ["@elastic/eui/es/components/icon/assets/cross" :rename {icon EuiIconCross}]
   ["@elastic/eui/es/components/icon/assets/pencil" :rename {icon EuiIconPencil}]
   ["@elastic/eui/es/components/icon/assets/trash" :rename {icon EuiIconTrash}]))

; Official workaround for dynamic import in EuiIcon
(appendIconComponentCache #js {"beaker" EuiIconBeaker
                               "arrowDown" EuiIconArrowDown
                               "calendar" EuiIconCalendar
                               "cross" EuiIconCross
                               "pencil" EuiIconPencil
                               "trash" EuiIconTrash})
;(js/console.log appendIconComponentCache)

; Initialize app state
(def default-db
  {:path "/"
   ;:view [:list-things]
   :view {:data {:name :page/welcome}}
   :state {}
   :modal [false nil]
   :data {:things [{:id 1 :user-name "Foo" :stat 53 :attr ["twit" "goog"]}
                   {:id 2 :user-name "Bar" :stat 21 :attr ["goog"]}
                   {:id 3 :user-name "Mary" :stat 5 :attr []}
                   {:id 4 :user-name "Peter" :stat 78 :attr ["git" "goog"]}
                   {:id 5 :user-name "Henry" :stat 44 :attr ["boing" "reddit" "devto"]}
                   {:id 6 :user-name "Angelina" :stat 1 :attr ["reddit"]}
                   {:id 7 :user-name "Joan" :stat 82 :attr []}]
          :tags ["twit" "goog" "git" "boing" "reddit" "devto"]}
   :name "re-frame"})

(re-frame/reg-event-db
 ::events.initialize-db
 (fn [_ _]
   default-db))
