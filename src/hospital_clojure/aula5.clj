(ns hospital-clojure.aula5
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]
            [hospital-clojure.logic :as h.logic]))

(defn chega-em! [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa))

(defn transfere! [hospital de para]
  (swap! hospital h.logic/transfere de para))

(defn simula-um-dia []
  (let [hospital (atom (h.model/novo-hospital))]
     (chega-em! hospital "joao")
     (chega-em! hospital "janine")
     (chega-em! hospital "wesley")
     (transfere! hospital :espera :laboratorio1)
     (pprint hospital)))

(simula-um-dia)