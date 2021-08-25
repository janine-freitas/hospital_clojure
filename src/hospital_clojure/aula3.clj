(ns hospital-clojure.aula3
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]
            [hospital-clojure.logic :as h.logic]))


(defn testa-atomao []
  (let [hospital-freitas (atom {:espera h.model/empty_queue})]
       (println hospital-freitas)
       (pprint hospital-freitas)
       (pprint (deref hospital-freitas))
       (pprint @hospital-freitas)

       ; NÃO É ASSIM QUE ALTERA O CONTEUDO DO ATOMO
       (pprint (assoc @hospital-freitas :laboratorio1 h.model/empty_queue))
       (pprint @hospital-freitas)

       ; FORMA DE ALTERAR O CONTEUDO DO ATOMO
       (swap! hospital-freitas assoc :laboratorio1 h.model/empty_queue)
       (swap! hospital-freitas assoc :laboratorio2 h.model/empty_queue)
       (pprint @hospital-freitas)

       ; ATUALIZAR UMA VARIAVEL IMUTAVEL
       (update @hospital-freitas :laboratorio1 conj "111")

       ;ATUALIZAR PARA SWAP
       (swap! hospital-freitas update :laboratorio1 conj "111")
       (pprint @hospital-freitas)
       ))

;(testa-atomao)

(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado :espera pessoa)
  (println "Apos inserir" pessoa))

(defn simula-um-dia-em-paralero
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa)
  (println "Apos inserir" pessoa))

(defn simula-um-dia-em-paralero
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralero)