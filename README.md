Proyecto Spring de API para encontrar mejores tiempos de viaje entre dos puntos, utilizando el algoritmo de Dijkstra.

####################################################################################################

Listado de endpoints útiles:

-     /api/route-legs/import-csv

Request HTTP de tipo POST, donde el body debe ser de tipo form-data e incluir el key "file" con un archivo CSV en el formato especificado en la prueba técnica para las rutas, separadas por punto y coma (;).
Se incluye constraint en BD para que no se puedan agregar segmentos (RouteLegs) que compartan la ubicación de inicio y de término, la función para importar csv dejará de funcionar al detectar una línea con datos para RouteLeg duplicado.

-     /api/route-legs/get-all

Request de tipo GET que nos entrega todos los RouteLeg en la base de datos del sistema.

-     api/routes/best-path

Request de tipo GET con los parámetros to y from, que representan los puntos de inicio y término del cálculo. Un ejemplo de este endpoint es el siguiente:

/api/routes/best-path?from=CP1&to=R20
