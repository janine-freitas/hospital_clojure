(ns hospital-clojure.model
  (:import (clojure.lang PersistentQueue)))

(def empty_queue PersistentQueue/EMPTY)
(defn novo-hospital []
  {:espera empty_queue
   :laboratorio1 empty_queue
   :laboratorio2 empty_queue
   :laboratorio3 empty_queue})