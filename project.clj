(defproject lein-nrepl-eval "0.1.0-SNAPSHOT"
  :description "Sends commands to an nREPL server"
  :url "http://github.com/jcrossley3/lein-nrepl-eval"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :dependencies [[org.clojure/tools.nrepl "0.2.0-beta9"]]
  :lein-release {:deploy-via :clojars})