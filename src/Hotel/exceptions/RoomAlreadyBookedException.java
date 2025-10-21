package Hotel.exceptions;

// 8.кастомная непроверяемая ошибка
public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}