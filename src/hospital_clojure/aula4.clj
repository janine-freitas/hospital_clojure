(ns hospital-clojure.aula4
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]
            [hospital-clojure.logic :as h.logic]))

(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa)
  (println "Apos inserir" pessoa))

(defn simula-um-dia-em-paralero-com-mapv
  []
  (let [hospital (atom (h.model/novo-hospital))
    pessoas ["111", "222", "333", "444", "555", "666"]]
    (mapv #(.start (Thread. (fn [] (chega-sem-malvado! hospital %)))) pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn starta-thread-de-chegada
  ([hospital]
  (fn [pessoa] (starta-thread-de-chegada hospital pessoa)))
  ([hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa))))))

(defn simula-um-dia-em-paralero-refatorando
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]
        starta (starta-thread-de-chegada hospital)]
    (mapv starta pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn starta-thread-de-chegada-partial
  [hospital pessoa]
   (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralero-refatorando-partial
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]
        starta (partial starta-thread-de-chegada-partial hospital)]
    (mapv starta pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralero-refatorando-partial)
