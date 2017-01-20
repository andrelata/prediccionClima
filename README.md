# prediccion Clima

## Algunos detalles del problema:
- Ferengi - 360 dias en dar la vuelta
- Vulcano - 72 dias en dar la vuelta
- Betasoide - 120 dias en dar la vuelta
- Un ciclo dura 360 dias, a partir del dia 361 todo se repite

## Consideraciones:
- Los años tienen 365 dias
- Si la alinacion de planetas no cumple ninguno de los periodos definidos, el clima se considera indefinido.

## Correr la aplicacion:

```sh
./mvnw spring-boot:run
```
o
```sh
./mvnw clean package
java -jar target/prediccionPlanetas-0.0.1-SNAPSHOT.jar
```

## Ejemplos de uso de la API

1. La condición climática del día consultado.

	http://localhost:8080/clima?dia=0
	{"dia":0,"clima":"sequia","perimetro":0.0}

2. La cantidad de dias de cierto clima dentro de los años. si no especifica años se considera q es un periodo de 10 años.

	http://localhost:8080/periodo?clima=lluvia&&años=1
	{"cantidad":87,"periodo":"lluvia"}

    http://localhost:8080/periodo?clima=sequia
    {"cantidad":11,"periodo":"sequia"}

3. Lista de los periodos de clima y la cantidad de dias que se repiten en n años. si no especifica años se considera q es un periodo de 10 años.

	http://localhost:8080/periodos?años=1
	[{"cantidad":276,"periodo":"indefinido"},{"cantidad":87,"periodo":"lluvia"},{"cantidad":2,"periodo":"sequia"}]

    http://localhost:8080/periodos
    [{"cantidad":2754,"periodo":"indefinido"},{"cantidad":885,"periodo":"lluvia"},{"cantidad":11,"periodo":"sequia"}]

4. Lista de los dias con pico de lluvia dentro de n años. Por default 10 años

	http://localhost:8080/picoLluvia?años=1
	[{"dia":203,"perimetro":6320.739227965827}]

    http://localhost:8080/picoLluvia
    [{"dia":203,"perimetro":6320.739227965827},{"dia":2030,"perimetro":6320.739227965827},{"dia":1827,"perimetro":6320.739227965827},{"dia":1624,"perimetro":6320.739227965827},{"dia":1421,"perimetro":6320.739227965827},{"dia":1218,"perimetro":6320.739227965827},{"dia":1015,"perimetro":6320.739227965827},{"dia":812,"perimetro":6320.739227965827},{"dia":609,"perimetro":6320.739227965827},{"dia":406,"perimetro":6320.739227965827}]

## Links utilizados
	https://spring.io/guides/gs/rest-service/
	http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html
	http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.limit-query-result
	http://www.universoformulas.com/fisica/cinematica/posicion-movimiento-circular/
	http://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html
