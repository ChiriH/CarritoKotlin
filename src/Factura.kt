class Factura(private val carrito: CarritoDeCompras) {
    fun generarFactura() {
        println("\n------------ FACTURA ------------")
        carrito.mostrarCarrito()
        val total = carrito.calcularTotal()
        println("\nTOTAL A PAGAR: $$total")
    }
}



