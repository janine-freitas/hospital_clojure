(ns hospital-clojure.core
  (:use [clojure pprint])
  (:require [hospital-clojure.model :as h.model]))

(let [hospital-janine (h.model/novo-hospital)]
  (pprint hospital-janine))

(pprint h.model/empty_queue)