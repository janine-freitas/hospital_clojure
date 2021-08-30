(ns hospital-clojure.aula6
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]))

(defn chega-em [fila pessoa]
  (conj fila pessoa))

;TROCA DE REFERENCIA VIA REF-SET
(defn chega-em! [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em @fila pessoa))))

;TROCA DE REFERENCIA VIA ALTER
(defn chega-em! [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia []
  (let [hospital {:espera (ref h.model/empty_queue)
                  :laboratorio1 (ref h.model/empty_queue)
                  :laboratorio2 (ref h.model/empty_queue)
                  :laboratorio3 (ref h.model/empty_queue)}]
    (dosync
      (chega-em! hospital "janine"))
    (pprint hospital)))
(simula-um-dia)