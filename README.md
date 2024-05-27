INSTRUCCIONS
=============

0. Crea un repo en github e compárteo en **privado** comigo. Realiza un commit coma mínimo ao pasar cada caso test proposto ou **non correxiré** o teu exame.
Realiza un `push` ao repo remoto en GitHub **só cando remates o proxecto**.

1. Crea un proxecto REST Quarkus con Maven. Instala as dependencias do proxecto segundo as necesites.

2. Situa os arquivos proporcionados: [`schema.sql`](./schema.sql) y [`application.properties`](./application.properties) nos seus directorios correspondentes do proyecto. **Non modifiques o contido destes dous arquivos**. A base de datos ten que ser **H2 executándose en memoria**.

3. Comeza implementando os casos test do arquivo [`RepoTest.java`](./RepoTest.java). **Non** modifiques o seu código. Implementa as entidades e a capa correspondente ao repositorio ou acceso a datos co **patrón que prefiras (_Active Record_ o DAO)**.

4. Os casos test do repositorio involucran os contidos mínimos do módulo precisos  para superar o módulo.

5. Unha vez codificado o repositorio, continua cos casos test do arquivo [`ResourceTest.java`](./ResourceTest.java) para implementar o servicio e o controlador da app. Todas as peticións ao controlador deben pasar polo servizo antes de dirixilas ao método correspondente do repositorio.
   
```bash
            ___
	. -^    `--,
       /# =========`-_
      /# (--===___====\
     /#   .- --.  . --.|
    /##   |  * ) (   * ),
    |###  \    /\ \    /|
    |###   ----  \  --- |
    |####      ___)    #|
     \####            ##|
      \### ----------  /
       \###           (
        '\###         |
          \##         |
           \###.    .)
            '======/
       
       SHOW ME WHAT YOU'VE GOT! 
```