(defproject crudy "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.60"]
                 [thheller/shadow-cljs "2.19.9"]
                 [reagent "1.1.1"]
                 [re-frame "1.3.0-rc3"]
                 [metosin/reitit "0.5.18"]
                 [macchiato/core "0.2.22"]
                 [com.taoensso/timbre "5.2.1"]
                 [com.github.pkpkpk/cljs-node-io "2.0.332"]
                 [macchiato/hiccups "0.4.1"]]

  :plugins [[lein-shadow "0.4.0"]
            
            [lein-shell "0.5.0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]


  :shell {:commands {"open" {:windows ["cmd" "/c" "start"]
                             :macosx  "open"
                             :linux   "xdg-open"}}}

  :shadow-cljs {:nrepl {:port 8777}
                :dev-http {8280 ["resources/public" "node_modules/@elastic/eui/es/components/icons/"]}
                ;:ssl {}
                :http {:port 9630
                       :host "0.0.0.0"}
                
                :builds {:app {:target :browser
                               :output-dir "resources/public/js/compiled"
                               :asset-path "/js/compiled"
                               :modules {:app {:init-fn crudy.entry.core/init
                                               :preloads [devtools.preload]}} 
                               :devtools {;:http-root ["resources/public" "node_modules/@elastic/eui/es/components/icons/"]
                                          ;:http-port 8280
                                          :devtools-url ~(clojure.string/trim-newline (clojure.string/replace (:out (clojure.java.shell/sh "gp" "url")) "https://" "https://5050-"))
                                          }}
                         :ssr {:target :node-script
                               :main crudy.entry.server/main
                               :output-to "out/crudy/script.js"
                               :devtools {:before-load crudy.entry.server/stop!
                                          :after-load crudy.entry.server/start!}}}}

  :aliases {"dev"          ["with-profile" "dev" "do"
                            ["shadow" "watch" "app" "ssr"]]
            "prod"         ["with-profile" "prod" "do"
                            ["shadow" "release" "app"]]
            "build-report" ["with-profile" "prod" "do"
                            ["shadow" "run" "shadow.cljs.build-report" "app" "target/build-report.html"]
                            ["shell" "open" "target/build-report.html"]]
            "karma"        ["with-profile" "prod" "do"
                            ["shadow" "compile" "karma-test"]
                            ["shell" "karma" "start" "--single-run" "--reporters" "junit,dots"]]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "1.0.0"]]
    :source-paths ["dev"]}

   :prod {}
   
}

  :prep-tasks [])
