class CarritoDeCompras {
    // oid del producto como clave en el mapa
    private val productosEnCarrito = mutableMapOf<Int, Pair<Producto, Int>>()
    // funcion qgregar producto
    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (producto.cantidadDisponible < cantidad) {
            println("No hay suficiente cantidad disponible del producto: ${producto.nombre}")
            return
        }

        // si el producto ya está en el carrito, se suma la cantidad
        val cantidadActual = productosEnCarrito[producto.id]?.second ?: 0
        productosEnCarrito[producto.id] = producto to (cantidadActual + cantidad)

        producto.cantidadDisponible -= cantidad
        println("Se agregó al carrito: ${producto.nombre}, cantidad: $cantidad")

    }

    fun eliminarProducto(productoId: Int, cantidad: Int) {
        val productoEliminar = productosEnCarrito[productoId]

        if (productoEliminar != null) {
            val (producto, cantidadEnCarrito) = productoEliminar

            if (cantidadEnCarrito < cantidad) {
                println("No puedes eliminar más cantidad de la que hay en el carrito. Cantidad en el carrito: $cantidadEnCarrito")
                return
            }

            // reducir la cantidad del carrito y devolverla al inventario
            productosEnCarrito[productoId] = producto to (cantidadEnCarrito - cantidad)
            producto.cantidadDisponible += cantidad
            println("Se removió ${cantidad} de ${producto.nombre} del carrito")

            // si la cantidad llega a 0, eliminar el producto del carrito
            if (productosEnCarrito[productoId]?.second == 0) {
                productosEnCarrito.remove(productoId)
                println("El ${producto.nombre} fue eliminado del carrito.")
            }

        } else {
            println("El producto no está en el carrito.")
        }
    }

    fun mostrarCarrito() {
        println("\nProductos en el carrito:")
        if (productosEnCarrito.isEmpty()) {
            println("El carrito está vacío.")
        } else {
            productosEnCarrito.forEach { (_, productoInfo) ->
                val (producto, cantidad) = productoInfo
                println("${producto.nombre}, Cantidad: $cantidad, Precio Unidad: ${producto.precio}")



            }
        }
    }

    fun calcularTotal(): Double {
        return productosEnCarrito.entries.sumOf { (_, productoInfo) ->
            val (producto, cantidad) = productoInfo
            producto.precio * cantidad
        }
    }

}
