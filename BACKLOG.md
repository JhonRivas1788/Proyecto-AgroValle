# Product Backlog - AgroValle Connect

## Resumen de Priorización (MoSCoW) y Estimación (Story Points - Fibonacci)

Estimado por el equipo mediante Planning Poker. Escala: 1, 2, 3, 5, 8, 13.
Leyenda MoSCoW: **M** = Must Have, **S** = Should Have, **C** = Could Have, **W** = Won't Have (este ciclo).

```
--------------------------------------------------------------------
ID     Historia de Usuario                    MoSCoW  Story Points
--------------------------------------------------------------------
HU-01  Registro de Agricultores               M       5
HU-02  Publicación de Productos               M       5
HU-03  Visualización de Precios Regionales    S       5
HU-04  Filtro de Categorías y Municipios      M       3
HU-05  Contacto Directo con el Agricultor     S       3
HU-06  Actualización de Perfil de Agricultor  C       3
HU-07  Consulta de Perfil de Agricultor       M       2
HU-08  Carrito de Compras                     M       8
HU-09  Emisión de Orden de Compra             M       8
HU-10  Notificación de Estado de Pedido       S       5
HU-11  Confirmación de Alistamiento y Ruta    S       5
HU-12  Seguimiento del Pedido                 S       3
HU-13  Historial de Ventas                    C       3
HU-14  Calificación del Agricultor            C       3
HU-15  Gestión de Usuarios (Administrador)    W       5
HU-16  Autenticación e Inicio de Sesión       M       8
HU-17  Registro de Compradores                M       3
--------------------------------------------------------------------
Total                                                 77 pts
--------------------------------------------------------------------
Sprint 1 (capacidad 10 pts): HU-01 (5) + HU-04 (3) + HU-07 (2).
--------------------------------------------------------------------
```

```
--------------------------------------------------------------------
REGLAS DEL DOMINIO
--------------------------------------------------------------------
- Municipio: debe pertenecer al Valle del Cauca (Dagua, Palmira,
  Buga, Tuluá, Caicedonia, Jamundí, entre otros).
- Cédula válida: solo dígitos, entre 6 y 10 caracteres.
- Transacciones recientes: las registradas en las últimas 24 horas.
- Contraseña: mínimo 8 caracteres; se guarda protegida, nunca en
  texto legible.
- Estados de cuenta: Activo, Desactivado. Estados de producto:
  Disponible, Agotado.
- Estados de pedido: Confirmado (el comprador lo ve como "En
  preparación"), En camino, Entregado.
```

```
--------------------------------------------------------------------
HU-01: Registro de Agricultores
--------------------------------------------------------------------
Historia: Como Agricultor, quiero registrarme en la plataforma para
ofrecer mis productos.

Given: el agricultor no tiene una cuenta creada en la plataforma.
When:  el agricultor completa el formulario con su nombre, una
       cédula válida y un municipio del Valle del Cauca, y lo envía.
Then:  el sistema crea la cuenta en estado "Activo" y confirma que
       el registro fue exitoso.

Caso sin éxito 1 (dato faltante):
Given: el agricultor no tiene una cuenta creada en la plataforma.
When:  envía el formulario sin nombre, cédula o municipio.
Then:  el sistema indica el dato que falta y no crea la cuenta.

Caso sin éxito 2 (cédula repetida):
Given: ya existe un agricultor registrado con una cédula
       determinada.
When:  otra persona intenta registrarse con esa misma cédula.
Then:  el sistema informa que la cédula ya está registrada y no crea
       la cuenta.

Caso sin éxito 3 (cédula inválida):
Given: el agricultor no tiene una cuenta creada en la plataforma.
When:  ingresa una cédula con letras o con una longitud fuera de 6 a
       10 dígitos.
Then:  el sistema indica que la cédula no es válida y no crea la
       cuenta.

Caso sin éxito 4 (municipio fuera del Valle):
Given: el agricultor no tiene una cuenta creada en la plataforma.
When:  ingresa un municipio que no pertenece al Valle del Cauca.
Then:  el sistema indica que la ubicación no es válida y no crea la
       cuenta.

Nota: el correo y la contraseña del agricultor se incorporan al
refinar HU-16.
```

```
--------------------------------------------------------------------
HU-02: Publicación de Productos
--------------------------------------------------------------------
Historia: Como Agricultor, quiero publicar mis cosechas para que
sean visibles a los compradores.

Given: el agricultor tiene una cuenta activa en la plataforma.
When:  el agricultor ingresa el tipo de producto, la cantidad y la
       fecha de cosecha, y publica la oferta.
Then:  el sistema valida que la fecha no sea anterior a hoy y que la
       cantidad sea mayor a cero, asigna un identificador único al
       producto y lo publica como "Disponible" con el municipio del
       agricultor.

Caso sin éxito 1 (fecha anterior a hoy):
Given: el agricultor tiene una cuenta activa en la plataforma.
When:  ingresa una fecha de cosecha anterior a hoy.
Then:  el sistema no publica el producto y explica el motivo.

Caso sin éxito 2 (cantidad inválida):
Given: el agricultor tiene una cuenta activa en la plataforma.
When:  ingresa una cantidad igual o menor a cero.
Then:  el sistema no publica el producto e indica que la cantidad
       debe ser mayor a cero.
```

```
--------------------------------------------------------------------
HU-03: Visualización de Precios Regionales
--------------------------------------------------------------------
Historia: Como Usuario, quiero ver el precio promedio de un producto
en el Valle, para negociar mejor.

Given: existen transacciones de un producto agrícola registradas en
       las últimas 24 horas.
When:  el usuario selecciona el producto y solicita ver su precio
       promedio.
Then:  el sistema calcula la media aritmética de esas transacciones
       y la muestra en pesos colombianos.

Caso sin éxito:
Given: no existen transacciones del producto en las últimas 24
       horas.
When:  el usuario solicita ver su precio promedio.
Then:  el sistema indica que no hay información suficiente.
```

```
--------------------------------------------------------------------
HU-04: Filtro de Categorías y Municipios
--------------------------------------------------------------------
Historia: Como Comprador, quiero filtrar las ofertas por categoría y
municipio, para encontrar productos locales de mi interés
rápidamente.

Given: existen productos publicados con su categoría y municipio
       definidos.
When:  el comprador selecciona una categoría y un municipio en los
       filtros de búsqueda.
Then:  el sistema muestra únicamente los productos disponibles que
       coinciden con esa categoría y ese municipio.

Caso sin éxito 1 (sin resultados):
Given: ningún producto publicado coincide con la categoría y el
       municipio elegidos.
When:  el comprador aplica el filtro.
Then:  el sistema muestra un mensaje indicando que no hay resultados
       para esa búsqueda.

Caso sin éxito 2 (productos agotados):
Given: existen productos en estado "Agotado" que coinciden con la
       categoría y el municipio elegidos.
When:  el comprador aplica el filtro.
Then:  el sistema no incluye esos productos en los resultados.
```

```
--------------------------------------------------------------------
HU-05: Contacto Directo con el Agricultor
--------------------------------------------------------------------
Historia: Como Comprador, quiero enviar un mensaje directo al
agricultor sobre un producto, para acordar las condiciones de
compra.

Given: el comprador está viendo un producto publicado por un
       agricultor.
When:  el comprador escribe un mensaje y lo envía al agricultor
       responsable del producto.
Then:  el sistema entrega el mensaje al agricultor y le notifica que
       tiene un nuevo contacto.

Caso sin éxito:
Given: el comprador está viendo un producto publicado por un
       agricultor.
When:  el comprador intenta enviar un mensaje vacío o formado solo
       por espacios.
Then:  el sistema no lo envía y le pide escribir un mensaje.
```

```
--------------------------------------------------------------------
HU-06: Actualización de Perfil de Agricultor
--------------------------------------------------------------------
Historia: Como Agricultor, quiero actualizar los datos de mi finca y
mi contacto, para mantener mi información visible y correcta para
los compradores.

Given: el agricultor tiene una cuenta registrada con datos de finca
       y contacto.
When:  el agricultor edita su teléfono o la dirección de su finca
       con datos válidos y guarda los cambios.
Then:  el sistema actualiza la información y confirma que los datos
       quedaron guardados.

Caso sin éxito:
Given: el agricultor tiene una cuenta registrada con datos de finca
       y contacto.
When:  ingresa un teléfono con formato inválido o deja la dirección
       de la finca vacía.
Then:  el sistema indica qué dato corregir y no guarda los cambios.
```

```
--------------------------------------------------------------------
HU-07: Consulta de Perfil de Agricultor
--------------------------------------------------------------------
Historia: Como Comprador, quiero consultar el perfil de un
agricultor, para conocer su ubicación y confiar en quien me vende.

Given: existe un agricultor registrado en la plataforma.
When:  el comprador solicita ver el perfil de ese agricultor.
Then:  el sistema muestra su nombre y su ubicación en el Valle del
       Cauca, sin exponer datos sensibles como la cédula.

Caso sin éxito:
Given: no existe un agricultor con el identificador solicitado.
When:  el comprador solicita ver ese perfil.
Then:  el sistema indica que no se encontró el perfil.
```

```
--------------------------------------------------------------------
HU-08: Carrito de Compras
--------------------------------------------------------------------
Historia: Como Comprador, quiero agregar productos a un carrito de
compras, para reunir varias ofertas antes de confirmar mi pedido.

Given: existen productos disponibles publicados por agricultores.
When:  el comprador selecciona un producto y una cantidad, y lo
       agrega al carrito.
Then:  el sistema reserva temporalmente esa cantidad y muestra el
       producto dentro del carrito.

Caso sin éxito 1 (cantidad mayor al stock):
Given: un producto tiene un stock disponible determinado.
When:  el comprador pide una cantidad mayor a ese stock.
Then:  el sistema no lo agrega e indica la cantidad máxima
       disponible.

Caso sin éxito 2 (cantidad inválida):
Given: existe un producto disponible publicado por un agricultor.
When:  el comprador ingresa una cantidad igual o menor a cero.
Then:  el sistema no lo agrega e indica que la cantidad debe ser
       mayor a cero.

Nota: la reserva se libera si el comprador quita el producto o si el
carrito expira. El plazo se define al refinar el sprint de esta
historia.
```

```
--------------------------------------------------------------------
HU-09: Emisión de Orden de Compra
--------------------------------------------------------------------
Historia: Como Comprador, quiero confirmar mi carrito como un
pedido, para que el agricultor prepare el envío de los productos.

Given: el comprador tiene al menos un producto reservado en su
       carrito.
When:  el comprador confirma el pedido.
Then:  el sistema convierte la reserva en descuento definitivo de
       stock, crea el pedido en estado "Confirmado" y muestra el
       número de orden.

Caso sin éxito 1 (carrito vacío):
Given: el comprador tiene el carrito vacío.
When:  el comprador intenta confirmar el pedido.
Then:  el sistema no permite confirmar y pide agregar productos
       primero.

Caso sin éxito 2 (reserva expirada):
Given: el carrito contiene un producto cuya reserva ya expiró.
When:  el comprador intenta confirmar el pedido.
Then:  el sistema no confirma ese producto e informa que debe volver
       a agregarlo.
```

```
--------------------------------------------------------------------
HU-10: Notificación de Estado de Pedido
--------------------------------------------------------------------
Historia: Como Agricultor, quiero recibir un aviso cuando un
comprador confirme un pedido, para preparar el alistamiento a
tiempo.

Given: un comprador acaba de confirmar un pedido sobre un producto
       del agricultor.
When:  el pedido cambia a estado "Confirmado".
Then:  el sistema envía una notificación al agricultor con el
       producto y la cantidad solicitada.

Caso sin éxito:
Given: un pedido está en estado "Confirmado" y su notificación no
       pudo entregarse.
When:  el sistema detecta el fallo de envío.
Then:  el sistema deja la notificación pendiente para reintentar el
       envío.
```

```
--------------------------------------------------------------------
HU-11: Confirmación de Alistamiento y Ruta
--------------------------------------------------------------------
Historia: Como Agricultor, quiero confirmar que un pedido está listo
y definir la fecha de envío, para coordinar la entrega con el
comprador.

Given: existe un pedido en estado "Confirmado" asignado al
       agricultor.
When:  el agricultor marca el pedido como "Listo para enviar" e
       indica una fecha de despacho igual o posterior a hoy.
Then:  el sistema actualiza el pedido a "En camino" y avisa al
       comprador la fecha estimada de llegada.

Caso sin éxito 1 (sin fecha):
Given: existe un pedido en estado "Confirmado" asignado al
       agricultor.
When:  lo marca como "Listo para enviar" sin indicar la fecha de
       despacho.
Then:  el sistema no permite continuar y solicita la fecha.

Caso sin éxito 2 (fecha anterior a hoy):
Given: existe un pedido en estado "Confirmado" asignado al
       agricultor.
When:  indica una fecha de despacho anterior a hoy.
Then:  el sistema no actualiza el pedido e indica que la fecha no es
       válida.
```

```
--------------------------------------------------------------------
HU-12: Seguimiento del Pedido
--------------------------------------------------------------------
Historia: Como Comprador, quiero ver el estado de mi pedido, para
saber cuándo va a llegar.

Given: el comprador tiene un pedido en estado "En camino".
When:  el comprador consulta el estado de su pedido.
Then:  el sistema muestra el estado actual y la fecha estimada de
       llegada.

Caso sin éxito:
Given: el comprador tiene un pedido en estado "Confirmado".
When:  el comprador consulta el estado de su pedido.
Then:  el sistema indica que el pedido todavía está en preparación.

Nota: quién marca el pedido como "Entregado" (el comprador o el
agricultor) se define al refinar esta historia.
```

```
--------------------------------------------------------------------
HU-13: Historial de Ventas
--------------------------------------------------------------------
Historia: Como Agricultor, quiero ver el historial de mis ventas
realizadas, para llevar control de mis ingresos y cosechas vendidas.

Given: el agricultor tiene pedidos en estado "Entregado".
When:  el agricultor consulta su historial de ventas.
Then:  el sistema muestra la lista de esos pedidos ordenados por
       fecha, del más reciente al más antiguo.

Caso sin éxito:
Given: el agricultor no tiene pedidos en estado "Entregado".
When:  el agricultor consulta su historial de ventas.
Then:  el sistema indica que no hay historial disponible.
```

```
--------------------------------------------------------------------
HU-14: Calificación del Agricultor
--------------------------------------------------------------------
Historia: Como Comprador, quiero calificar al agricultor después de
recibir mi pedido, para ayudar a otros compradores a elegir
proveedores confiables.

Given: el comprador tiene un pedido en estado "Entregado".
When:  el comprador asigna una calificación de 1 a 5 estrellas y
       escribe un comentario.
Then:  el sistema guarda la calificación y la muestra en el perfil
       del agricultor.

Caso sin éxito 1 (pedido no entregado):
Given: el pedido del comprador no está en estado "Entregado".
When:  el comprador intenta calificar al agricultor.
Then:  el sistema no permite calificar y explica el motivo.

Caso sin éxito 2 (calificación fuera de rango):
Given: el comprador tiene un pedido en estado "Entregado".
When:  asigna una calificación menor a 1 o mayor a 5.
Then:  el sistema no la guarda e indica que debe estar entre 1 y 5.
```

```
--------------------------------------------------------------------
HU-15: Gestión de Usuarios (Administrador)
--------------------------------------------------------------------
Historia: Como Administrador de la plataforma, quiero ver y
desactivar cuentas que incumplan las normas de uso, para mantener la
confianza de los usuarios en la plataforma.

Given: el administrador identificó una cuenta activa que incumple
       las políticas de uso.
When:  el administrador selecciona la cuenta y elige la opción
       "Desactivar".
Then:  el sistema cambia la cuenta a estado "Desactivado" y el
       usuario ya no puede iniciar sesión.

Caso sin éxito:
Given: la cuenta ya estaba en estado "Desactivado".
When:  el administrador intenta desactivarla de nuevo.
Then:  el sistema indica que no hay ninguna acción pendiente.
```

```
--------------------------------------------------------------------
HU-16: Autenticación e Inicio de Sesión
--------------------------------------------------------------------
Historia: Como Usuario registrado (Agricultor o Comprador), quiero
iniciar sesión con mi correo y contraseña, para acceder a las
funciones de la plataforma.

Given: el usuario ya está registrado con correo y contraseña y su
       cuenta está activa.
When:  el usuario ingresa su correo y contraseña y presiona "Iniciar
       sesión".
Then:  el sistema valida los datos y le da acceso a las funciones de
       su rol.

Caso sin éxito 1 (credenciales incorrectas):
Given: el usuario está registrado y su cuenta está activa.
When:  ingresa un correo o una contraseña incorrectos.
Then:  el sistema muestra un mensaje de error y no permite el
       ingreso.

Caso sin éxito 2 (cuenta desactivada):
Given: la cuenta del usuario está en estado "Desactivado".
When:  ingresa su correo y contraseña correctos.
Then:  el sistema no permite el ingreso e informa que la cuenta no
       está activa.

Nota: depende de HU-01 (credenciales del agricultor, a refinar) y de
HU-17.
```

```
--------------------------------------------------------------------
HU-17: Registro de Compradores
--------------------------------------------------------------------
Historia: Como Comprador (comerciante urbano o restaurante), quiero
registrarme en la plataforma, para adquirir productos directamente
de los agricultores.

Given: el comprador no tiene una cuenta creada en la plataforma.
When:  el comprador completa el formulario con su nombre, un correo
       no registrado, una contraseña de al menos 8 caracteres y su
       municipio, y lo envía.
Then:  el sistema crea la cuenta en estado "Activo", guarda la
       contraseña de forma protegida y confirma que el registro fue
       exitoso.

Caso sin éxito 1 (dato faltante):
Given: el comprador no tiene una cuenta creada en la plataforma.
When:  envía el formulario sin nombre, correo, contraseña o
       municipio.
Then:  el sistema indica el dato que falta y no crea la cuenta.

Caso sin éxito 2 (correo repetido):
Given: ya existe un comprador registrado con un correo determinado.
When:  otra persona intenta registrarse con ese mismo correo.
Then:  el sistema informa que el correo ya está registrado y no crea
       la cuenta.

Caso sin éxito 3 (contraseña corta):
Given: el comprador no tiene una cuenta creada en la plataforma.
When:  ingresa una contraseña de menos de 8 caracteres.
Then:  el sistema indica la longitud mínima requerida y no crea la
       cuenta.
```
