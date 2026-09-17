--------------------------------------------------------------------
HU-01: Registro de Agricultores
--------------------------------------------------------------------
Historia: Como Agricultor, quiero registrarme en la plataforma para
ofrecer mis productos.
 
Given: el agricultor no tiene una cuenta creada en la plataforma.
When:  el agricultor completa el formulario de registro con su
       nombre, cedula y ubicacion en el Valle, y lo envia.
Then:  el sistema crea la cuenta y confirma que el registro fue
       exitoso.
 
Caso sin exito: si algun dato obligatorio falta o la cedula ya esta
registrada, el sistema indica el error y no crea la cuenta.
 
--------------------------------------------------------------------
HU-02: Publicacion de Productos
--------------------------------------------------------------------
Historia: Como Agricultor, quiero publicar mis cosechas para que
sean visibles a los compradores.
 
Given: el agricultor tiene una cuenta activa en la plataforma.
When:  el agricultor ingresa el tipo de producto, la cantidad y la
       fecha de cosecha, y publica la oferta.
Then:  el sistema valida que la fecha no sea anterior a hoy y
       publica el producto como disponible.
 
Caso sin exito: si la fecha de cosecha ya paso, el sistema no
permite publicar y explica el motivo.
 
--------------------------------------------------------------------
HU-03: Visualizacion de Precios Regionales
--------------------------------------------------------------------
Historia: Como Usuario, quiero ver el precio promedio de un
producto en el Valle, para negociar mejor.
 
Given: existen transacciones recientes registradas de un producto
       agricola.
When:  el usuario selecciona el producto y solicita ver su precio
       promedio.
Then:  el sistema calcula el promedio con las transacciones mas
       recientes y lo muestra en pesos colombianos.
 
Caso sin exito: si no hay transacciones recientes de ese producto,
el sistema indica que no hay informacion suficiente.
 
--------------------------------------------------------------------
HU-04: Filtro de Categorias y Municipios
--------------------------------------------------------------------
Historia: Como Comprador, quiero filtrar las ofertas por categoria
y municipio, para encontrar productos locales de mi interes
rapidamente.
 
Given: existen productos publicados con su categoria y municipio
       definidos.
When:  el comprador selecciona una categoria y un municipio en los
       filtros de busqueda.
Then:  el sistema muestra unicamente los productos disponibles que
       coinciden con esa categoria y ese municipio.
 
Caso sin exito: si no hay productos que coincidan, el sistema
muestra un mensaje indicando que no hay resultados para esa
busqueda.
 
--------------------------------------------------------------------
HU-05: Contacto Directo con el Agricultor
--------------------------------------------------------------------
Historia: Como Comprador, quiero enviar un mensaje directo al
agricultor sobre un producto, para acordar las condiciones de
compra.
 
Given: el comprador esta viendo un producto publicado por un
       agricultor.
When:  el comprador escribe un mensaje y lo envia al agricultor
       responsable del producto.
Then:  el sistema entrega el mensaje al agricultor y le notifica
       que tiene un nuevo contacto.
 
Caso sin exito: si el comprador intenta enviar el mensaje vacio, el
sistema no lo envia y pide escribir un mensaje.
 
--------------------------------------------------------------------
HU-06: Actualizacion de Perfil de Agricultor
--------------------------------------------------------------------
Historia: Como Agricultor, quiero actualizar los datos de mi finca
y mi contacto, para mantener mi informacion visible y correcta
para los compradores.
 
Given: el agricultor tiene una cuenta registrada con datos de finca
       y contacto.
When:  el agricultor edita su telefono o la direccion de su finca y
       guarda los cambios.
Then:  el sistema actualiza la informacion y confirma que los datos
       quedaron guardados.
 
Caso sin exito: si algun dato es invalido, el sistema indica que
corregir y no guarda los cambios.
 
--------------------------------------------------------------------
HU-07: Autenticacion e Inicio de Sesion
--------------------------------------------------------------------
Historia: Como Usuario registrado (Agricultor o Comprador), quiero
iniciar sesion con mi correo y contrasena, para acceder a las
funciones de la plataforma.
 
Given: el usuario ya esta registrado con correo y contrasena.
When:  el usuario ingresa su correo y contrasena y presiona
       "Iniciar sesion".
Then:  el sistema valida los datos y le da acceso a su cuenta.
 
Caso sin exito: si el correo o la contrasena son incorrectos, el
sistema muestra un mensaje de error y no permite el ingreso.
 
--------------------------------------------------------------------
HU-08: Carrito de Compras
--------------------------------------------------------------------
Historia: Como Comprador, quiero agregar productos a un carrito de
compras, para reunir varias ofertas antes de confirmar mi pedido.
 
Given: existen productos disponibles publicados por agricultores.
When:  el comprador selecciona un producto y una cantidad, y lo
       agrega al carrito.
Then:  el sistema reserva esa cantidad y muestra el producto dentro
       del carrito.
 
Caso sin exito: si la cantidad pedida supera el stock disponible,
el sistema indica la cantidad maxima disponible.
 
--------------------------------------------------------------------
HU-09: Emision de Orden de Compra
--------------------------------------------------------------------
Historia: Como Comprador, quiero confirmar mi carrito como un
pedido, para que el agricultor prepare el envio de los productos.
 
Given: el comprador tiene al menos un producto en su carrito.
When:  el comprador confirma el pedido.
Then:  el sistema descuenta el stock, genera el pedido y muestra el
       numero de orden.
 
Caso sin exito: si el carrito esta vacio, el sistema no permite
confirmar y pide agregar productos primero.
 
--------------------------------------------------------------------
HU-10: Notificacion de Estado de Pedido
--------------------------------------------------------------------
Historia: Como Agricultor, quiero recibir un aviso cuando un
comprador confirme un pedido, para preparar el alistamiento a
tiempo.
 
Given: un comprador acaba de confirmar un pedido sobre un producto
       del agricultor.
When:  el pedido cambia a estado "Confirmado".
Then:  el sistema envia una notificacion al agricultor con el
       producto y la cantidad solicitada.
 
Caso sin exito: si la notificacion no puede entregarse, el sistema
la deja pendiente para reintentar el envio.
 
--------------------------------------------------------------------
HU-11: Confirmacion de Alistamiento y Ruta
--------------------------------------------------------------------
Historia: Como Agricultor, quiero confirmar que un pedido esta
listo y definir la fecha de envio, para coordinar la entrega con el
comprador.
 
Given: existe un pedido confirmado asignado al agricultor.
When:  el agricultor marca el pedido como "Listo para enviar" e
       indica la fecha de despacho.
Then:  el sistema actualiza el pedido a "En camino" y avisa al
       comprador la fecha estimada.
 
Caso sin exito: si el agricultor no indica una fecha, el sistema no
permite continuar y la solicita.
 
--------------------------------------------------------------------
HU-12: Seguimiento del Pedido
--------------------------------------------------------------------
Historia: Como Comprador, quiero ver el estado de mi pedido en
camino, para saber cuando va a llegar.
 
Given: el comprador tiene un pedido en estado "En camino".
When:  el comprador consulta el estado de su pedido.
Then:  el sistema muestra el estado actual y el tiempo estimado de
       llegada.
 
Caso sin exito: si el pedido aun no ha sido despachado, el sistema
indica que todavia esta en preparacion.
 
--------------------------------------------------------------------
HU-13: Historial de Ventas
--------------------------------------------------------------------
Historia: Como Agricultor, quiero ver el historial de mis ventas
realizadas, para llevar control de mis ingresos y cosechas
vendidas.
 
Given: el agricultor tiene pedidos completados anteriormente.
When:  el agricultor consulta su historial de ventas.
Then:  el sistema muestra la lista de pedidos completados ordenados
       por fecha.
 
Caso sin exito: si el agricultor aun no tiene ventas, el sistema
indica que no hay historial disponible.
 
--------------------------------------------------------------------
HU-14: Calificacion del Agricultor
--------------------------------------------------------------------
Historia: Como Comprador, quiero calificar al agricultor despues de
recibir mi pedido, para ayudar a otros compradores a elegir
proveedores confiables.
 
Given: el comprador tiene un pedido marcado como "Entregado".
When:  el comprador asigna una calificacion de 1 a 5 estrellas y
       escribe un comentario.
Then:  el sistema guarda la calificacion y la muestra en el perfil
       del agricultor.
 
Caso sin exito: si el pedido aun no ha sido entregado, el sistema
no permite calificar y explica por que.
 
--------------------------------------------------------------------
HU-15: Gestion de Usuarios (Administrador)
--------------------------------------------------------------------
Historia: Como Administrador de la plataforma, quiero ver y
desactivar cuentas que incumplan las normas de uso, para mantener
la confianza de los usuarios en la plataforma.
 
Given: el administrador ha identificado una cuenta que incumple las
       politicas de uso.
When:  el administrador selecciona la cuenta y elige la opcion
       "Desactivar".
Then:  el sistema desactiva la cuenta y el usuario ya no puede
       iniciar sesion.
 
Caso sin exito: si la cuenta ya estaba desactivada, el sistema
indica que no hay ninguna accion pendiente.
