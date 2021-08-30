(ns hospital-clojure.logic)

;(defn cheio?
;  [hospital departamento]
;  (let [fila (get hospital departamento)
;        tamanho-atual (count fila)
;        cabe? (< tamanho-atual 5)]))

(defn cabe-na-fila?
  [hospital departamento]
  (-> hospital
  (get ,, departamento)
  count,,,
  (< ,,, 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))

(defn chega-em-pausado
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (do
      (Thread/sleep 1000)
      (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))

(defn proxima
  [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
      (atende de)
      (chega-em para pessoa))))

;retorna duas coisas em um vetor
(defn atende-completo
  [hospital departamento]
  [(update hospital departamento peek) (update hospital departamento pop)])

;retorna duas coisas em um mapa
(defn atende-completo
  [hospital departamento]
  {:paciente  (update hospital departamento peek)
   :hospital      (update hospital departamento pop)})

;retorna duas coisas, chama ao mesmo tempo duas funcoes
(defn atende-completo-que-chama-ambos
  [hospital departamento]
  (let [fila (get hospital departamento)
    peek-pop (juxt peek pop)
    [pessoa fila-atualizada] (peek-pop fila)
    hospital-atualizado (update hospital assoc departamento fila-atualizada)]
  {:paciente  pessoa
   :hospital  hospital-atualizado}))