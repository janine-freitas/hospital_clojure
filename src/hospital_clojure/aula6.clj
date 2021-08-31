(ns hospital-clojure.aula6
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]))

(defn cabe-na-fila?
  [fila]
  (-> fila
      count
      (< 5)))

(defn chega-em [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))

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
      (chega-em! hospital "janine")
      (chega-em! hospital "janine1")
      (chega-em! hospital "janine2")
      (chega-em! hospital "janine3")
      (chega-em! hospital "janine4")
      (chega-em! hospital "janine5")
      )
    (pprint hospital)))
;(simula-um-dia)

(defn async-chega-em! [hospital pessoa]
  (future
    (Thread/sleep (rand 5000))
    (dosync
      (println "Tentando o codigo sincronizado" pessoa)
      (chega-em! hospital pessoa))))

(defn simula-um-dia-async []
  (let [hospital {:espera (ref h.model/empty_queue)
                  :laboratorio1 (ref h.model/empty_queue)
                  :laboratorio2 (ref h.model/empty_queue)
                  :laboratorio3 (ref h.model/empty_queue)}]

    (def futures (mapv #(async-chega-em! hospital %) (range 10)))
    ;(dotimes [pessoa 10] (async-chega-em! hospital pessoa))

    (future
      (dotimes [n 4]
        (Thread/sleep (rand 2000))
        (pprint hospital)
        (pprint futures)))))
(simula-um-dia-async)