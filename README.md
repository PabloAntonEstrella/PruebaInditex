# Pricing Service

## Descripción
Este proyecto es un servicio RESTful desarrollado en Spring Boot que permite consultar precios aplicables para productos en función de la fecha de consulta y la marca. Utiliza una base de datos en memoria H2 para almacenar los datos de precios y proveer una respuesta basada en los datos de ejemplo proporcionados.

## Estructura del Proyecto

1. PriceController: Controlador REST que expone el endpoint para consultar los precios.

2. PriceService: Servicio que maneja la lógica de negocio para obtener los precios aplicables.

3. PriceRepository: Repositorio que interactúa con la base de datos para recuperar los datos de precios.

4. Price: Entidad que representa un registro de precio en la base de datos.

5. PriceRequest: DTO que define los parámetros de entrada para la consulta de precios.

6. PriceResponse: DTO que define la respuesta del endpoint con los datos del precio aplicable.

7. DataLoader: Clase de configuración para inicializar la base de datos con datos de ejemplo.

## Tecnologías Utilizadas

1. Spring Boot: Framework principal para construir la aplicación.

2. H2 Database: Base de datos en memoria para pruebas.

3. JUnit 5: Framework para pruebas unitarias.

4. Mockito: Biblioteca para simulación y pruebas de integración.

5. MockMvc: Herramienta para realizar pruebas de controladores web en Spring Boot.

## Configuración del Proyecto

1. Clonar el repositorio:

```java
	git clone https://github.com/tu_usuario/pricing-service.git
	cd pricing-service
```

2. Configurar el archivo application-test.yml: 
	
	El archivo src/test/resources/application-test.yml está preconfigurado para utilizar H2 como base de datos en memoria para pruebas.

3. Inicializar la base de datos:

	La base de datos H2 se inicializa con datos de ejemplo mediante la clase DataLoader.

## Ejecutar la Aplicación

Para ejecutar la aplicación, usa el siguiente comando:

```java
	./mvnw spring-boot:run
```

Esto iniciará el servidor de la aplicación en http://localhost:8080.

## Probar el Endpoint

Puedes probar el endpoint REST utilizando herramientas como curl, Postman, o directamente desde los tests unitarios proporcionados.

## Ejemplo de Solicitud

Para hacer una solicitud POST al endpoint /v1/inditex/price, utiliza el siguiente formato JSON:

```java
	{
	  "brandId": 1,
	  "productId": 35455,
	  "applicationDate": "2020-06-14T10:00:00"
	}
```

## Respuesta Esperada

La respuesta del endpoint:

```java
	{
	  "productId": 35455,
	  "brandId": 1,
	  "priceList": "1",
	  "startDate": "2020-06-14T00:00:00",
	  "endDate": "2020-12-31T23:59:00",
	  "price": 35.50,
	  "curr": "EUR"
	}
```

## Ejecución de Tests

Para ejecutar los tests unitarios, usa el siguiente comando:

```java
	./mvnw test
```

Esto ejecutará los tests y validará que la lógica del controlador y el servicio se comporta como se espera.

## Descripción de los Tests

1. testGetPrice_10AM_14thJune: Verifica que la respuesta es correcta para una solicitud a las 10:00 del 14 de junio de 2020.

2. testGetPrice_4PM_14thJune: Verifica que la respuesta es correcta para una solicitud a las 16:00 del 14 de junio de 2020.

3. testGetPrice_9PM_14thJune: Verifica que la respuesta es correcta para una solicitud a las 21:00 del 14 de junio de 2020.

4. testGetPrice_10AM_15thJune: Verifica que la respuesta es correcta para una solicitud a las 10:00 del 15 de junio de 2020.

5. testGetPrice_9PM_16thJune: Verifica que la respuesta es correcta para una solicitud a las 21:00 del 16 de junio de 2020.

## Contribuir

Si deseas contribuir a este proyecto, por favor realiza un fork del repositorio, haz tus cambios y envía un pull request.

## License
MIT
