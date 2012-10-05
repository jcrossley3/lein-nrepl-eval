(ns leiningen.nrepl-eval
  (:refer-clojure :exclude [eval])
  (:require [clojure.tools.nrepl :as repl]))

(def ^:dynamic *nrepl-port* 7888)

(defn get-host [& [opts]]
  (or (:host opts) "localhost"))

(defn get-port [& [opts]]
  (read-string (or (:port opts) (str *nrepl-port*))))

(defn nrepl
  "Invoke command in remote nrepl; takes :host and :port options"
  [command & [opts]]
  (with-open [conn (repl/connect :host (get-host opts) :port (get-port opts))]
    (-> (repl/client conn Long/MAX_VALUE)
        (repl/client-session)
        (repl/message {:op :eval :code command})
        doall)))

(defn parse
  "Stringify the nrepl results; contains all stdout and stderr and the
   value of the last expression"
  [results]
  (let [summary (reduce
                 (fn [m [k v]]
                   (case k
                     (:out :err) (update-in m [:out] #(str % v))
                     (assoc m k v)))            
                 {} (apply concat results))]
    (str (:out summary) (:value summary))))

(defn nrepl-eval
  "Eval some code in a remote nrepl"
  {:no-project-needed true}
  [project command & {:keys [host port] :or {host "localhost", port 7888} :as opts}]
  (println (parse (nrepl command
                         (into {} (for [[k v] opts] [(read-string k) v]))))))