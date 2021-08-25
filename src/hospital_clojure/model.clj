(ns hospital-clojure.model)

(def empty_queue clojure.lang.PersistentQueue/EMPTY)
(defn novo-hospital []
  {:espera empty_queue
   :laboratorio1 empty_queue
   :laboratorio2 empty_queue
   :laboratorio3 empty_queue})