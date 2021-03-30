package xyz.mxue.assistant.exception;

/**
 * @author mxuexxmy
 * @date 3/31/2021$ 12:50 AM$
 */
public class FileStorageException extends RuntimeException{

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
