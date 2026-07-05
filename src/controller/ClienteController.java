// =============================
// Paradigma Orientado a Objetos
// =============================
// ClienteController extiende SalaController sin agregar métodos
// adicionales. Su propósito es limitar el acceso del cliente a
// solo las operaciones de reserva, ocupación y cancelación,
// manteniendo la separación de responsabilidades.

package controller;

public class ClienteController extends SalaController {
    // El cliente solo puede usar los métodos heredados:
    // reservarButaca, ocuparButaca, cancelarReserva
}
