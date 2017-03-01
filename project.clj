(defproject platey-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [compojure "1.5.2"]
                 [org.clojure/data.json "0.2.6"]
                 [ring.middleware.logger "0.5.0"]
                 [javax.servlet/servlet-api "2.5"]]
  :main com.platey.server.app
  :source-paths ["src/main/clojure"]
  :resource-paths ["src/main/resources"])
