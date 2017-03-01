(ns com.platey.server.app
  (:import [org.slf4j.LoggerFactory])
  (:require [ring.middleware.logger :as logger]
            [clojure.java.io :as io])
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]] ; form, query params decode; cookie; session, etc
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server))

(defonce log 
  (org.slf4j.LoggerFactory/getLogger "com.platey.server.main"))
(defonce server (atom nil))
(defonce server-port 8085)

(defn get-default-config [req]
  (let [config-resource (io/resource "configurations/default.json")]    
    {:status 200 :body (slurp config-resource)}))

(defn get-default-document [req]
  (let [document-resource (io/resource "documents/default.json")]
    {:status 200 :body (slurp document-resource)}))

(defn get-plates [req]
  {:status :body "NYI"})

(defn get-plate-by-id [req]
  (let [plate-id (-> req :params :id)
        plate-resource (io/resource (str "plates/" plate-id ".json"))]

    (if (not (nil? plate-resource))
      {:status 200 :body (slurp plate-resource)}
      {:status 404 :body (str plate-id ": not found\n")})))

(defroutes routes
  (GET "/configurations/default" [] get-default-config)
  (GET "/documents/default" [] get-default-document)
  (GET "/plates" [] get-plates)
  (GET "/plates/:id" [] get-plate-by-id)
  (not-found "<p>Page not found</p>"))

(defn start-server! []
  (let [site (site routes)
        logged-site (logger/wrap-with-logger site)
        new-server (run-server logged-site {:port server-port})]
    (reset! server new-server)))

(defn stop-server! []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (.info log (str "Booting server on " server-port))
  (start-server!))
