(ns hospital-clojure.logic)

(defn cheio?
  [hospital departamento]
  (let [fila (get hospital departamento)
        tamanho-atual (count fila)
        cabe? (< tamanho-atual 5)]))

(defn chega-em
  [hospital departamento pessoa]
  (if (cheio? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))