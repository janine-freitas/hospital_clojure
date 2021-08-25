(ns hospital-clojure.aula1
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]
            [hospital-clojure.logic :as h.logic]))

(defn simula_um_dia []
  (def hospital (h.model/novo-hospital))
  (def hospital (h.logic/chega-em hospital :espera "111"))
  (def hospital (h.logic/chega-em hospital :espera "222"))
  (def hospital(h.logic/chega-em hospital :espera "333"))

  (def hospital(h.logic/chega-em hospital :laboratorio1 "444"))
  (def hospital(h.logic/chega-em hospital :laboratorio2 "555"))
  (def hospital(h.logic/chega-em hospital :laboratorio3 "666"))
  ; VARIAVEIS GLOBAIS DEVEM SER EVITADAS
  (pprint hospital)

  (def hospital(h.logic/atende hospital :laboratorio1))
  (def hospital(h.logic/atende hospital :espera))
  (pprint hospital)
  )

(simula_um_dia)