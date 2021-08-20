(ns hospital-clojure.colecoes)

(defn testa-vetor
  []
  (let [espera [111 222]]
    (println "Vetor: " espera)
    (println "Vetor: " (conj espera 333)) ;ADICIONAR NO FINAL
    (println "Vetor: " (conj espera 444))
    (println "Vetor: " (pop espera))))    ;REMOVE NO FINAL

(testa-vetor)

(defn testa-lista
  []
  (let [espera '(111 222)]
    (println "lista: " espera)
    (println "lista: "(conj espera 333)) ;ADICIONAR NO COMEÇO
    (println "lista: "(pop espera))))    ;REMOVE NO COMEÇO

(testa-lista)

(defn testa-conjuto
  []
  (let [espera #{111 222}]
    (println "Conjuto: " espera)
    (println "Conjuto: " (conj espera 333)) ;ADICIONAR NO FINAL
    ;(println "Conjuto: "(pop espera))    ;NAO PERMITE USO DO POP
    ))
(testa-conjuto)

(defn testa-fila
  []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY "111" "222")]
    (println "Fila: " (seq espera))
    (println "Fila: " (seq (conj espera "333"))) ;ADICIONAR NO FINAL
    (println "Fila: " (seq (pop espera)))      ;REMOVE NO COMEÇO
    (println "Fila: " (peek espera))            ;PEGAR O PRIMEIRO ELEMENTO
    ))
(testa-fila)