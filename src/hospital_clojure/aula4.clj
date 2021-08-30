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

;DOSEQ
(defn starta-thread-de-chegada-doseq
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralero-refatorando-doseq
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas (range 6)]

    (doseq [pessoa pessoas]
      (starta-thread-de-chegada-doseq hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn teste-doseq []
  (doseq [x [0 1 2]
        y [0  1 2]]
  (prn (* x y))))
(println "DOSEQ")
(teste-doseq)

(defn teste-dotimes []
  (dotimes [x 3]
    (dotimes [y 3]
      (prn (* x y)))))
(println "DOTIMES")
(teste-dotimes)
;DOTIMES
(defn starta-thread-de-chegada-dotimes
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralero-refatorando-dotimes
  []
  (let [hospital (atom (h.model/novo-hospital))]

    (dotimes [pessoa 6]
      (starta-thread-de-chegada-doseq hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralero-refatorando-dotimes)
