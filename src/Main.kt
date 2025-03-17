fun main() {
    val inventario = obtenerInventario()
    val carrito = CarritoDeCompras()
    var opcion = 0

    do {
        println("\n--- MENÚ ---")
        println("1. Mostrar inventario")
        println("2. Agregar al carrito")
        println("3. Eliminar del carrito")
        println("4. Ver carrito")
        println("5. Generar factura")
        println("6. Salir")

        try {
            opcion = readln().toInt()
            if (opcion < 1 || opcion > 6) {
                println("Opción no válida. Elige un número entre 1 y 6.")
                continue
            }
        } catch (e: Exception) {
            println("Opción no válida. Ingrese un número válido.")
            continue
        }

        when (opcion) {
            1 -> mostrarInventario(inventario)
            2 -> {
                println("Ingrese el ID del producto a agregar al carrito:")
                val id = readln().toInt()
                println("Ingrese la cantidad:")
                val cantidad = readln().toInt()
                val producto = inventario.find { it.id == id }
                if (producto != null) {
                    carrito.agregarProducto(producto, cantidad)
                } else {
                    println("Producto no encontrado.")
                }
            }
            3 -> {
                println("Ingrese el ID del producto a eliminar del carrito:")
                val id = readln().toInt()
                println("Ingrese la cantidad a eliminar:")
                val cantidad = readln().toInt()
                carrito.eliminarProducto(id, cantidad)
            }
            4 -> carrito.mostrarCarrito()
            5 -> {
                val factura = Factura(carrito)
                factura.generarFactura()
            }

            6 -> println("¡Hasta luego!")

        }
    } while (opcion != 6)
}

fun mostrarInventario(inventario: List<Producto>) {
    println("\n--- Inventario ---")
    for (producto in inventario) {
        println("ID: ${producto.id}, Nombre: ${producto.nombre}, Precio: ${producto.precio}, Cantidad Disponible: ${producto.cantidadDisponible}")
    }
}





