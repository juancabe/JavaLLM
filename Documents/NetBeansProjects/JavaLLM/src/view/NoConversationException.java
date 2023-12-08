/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Usuario
 */
public class NoConversationException extends Exception {

    public NoConversationException() {
    }

    public NoConversationException(String message) {
        super(message);
    }

    public NoConversationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConversationException(Throwable cause) {
        super(cause);
    }

    public NoConversationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
